package app.modules.shotslist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guilhermecardoso.shotstest.R;

import java.util.List;

import domain.entities.Shot;

/**
 * Created by guilhermecardoso on 11/13/17.
 */

public class ShotsListFragment extends Fragment implements ShotsListContract.View{

    private static String TAG = "ShotsListFragment";
    private ShotsListContract.Actions presenter;

    public static ShotsListFragment newInstance() {

        Bundle args = new Bundle();

        ShotsListFragment fragment = new ShotsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setPresenter(ShotsListContract.Actions presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (presenter != null) {
            presenter.loadShots();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        inflater.inflate(R.layout.shots_list_fragment, container, true);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.shots_list_recycler_view);

        return rootView;
    }

    @Override
    public void showShows(List<Shot> shots) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
