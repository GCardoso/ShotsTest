package app.modules.shotslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guilhermecardoso.shotstest.R;

import java.util.List;

import app.modules.shotdetails.ShotDetailsActivity;
import app.modules.shotslist.adapter.ShotsAdapter;
import cn.pedant.SweetAlert.SweetAlertDialog;
import domain.entities.Shot;
import library.listeners.EndlessScrollListener;

/**
 * Created by guilhermecardoso on 11/13/17.
 */

public class ShotsListFragment extends Fragment implements ShotsListContract.View{

    private static String TAG = "ShotsListFragment";
    private ShotsListContract.Actions presenter;
    private ShotsAdapter adapter;

    public static ShotsListFragment newInstance() {
        ShotsListFragment fragment = new ShotsListFragment();
        return fragment;
    }

    public void setPresenter(ShotsListContract.Actions presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.shots_list_fragment, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.shots_list_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.fetchPage(page);
            }
        });

        adapter = new ShotsAdapter();
        adapter.setPresenter(presenter);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter != null) {
            presenter.fetchPage(1);
        }
    }

    @Override
    public void showShows(List<Shot> shots) {
        for (Shot shot : shots) {
            this.adapter.add(shot);
        }
    }

    @Override
    public void showShotDetails(long shot) {
        Intent intent = new Intent(getActivity(), ShotDetailsActivity.class);
        intent.putExtra(ShotDetailsActivity.EXTRA_SHOT_ID, shot);
        startActivity(intent);
    }

    @Override
    public void showError() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE);
            dialog.setTitleText("Some error occurred");
            dialog.setCancelable(true);
            dialog.show();
        }
    }
}
