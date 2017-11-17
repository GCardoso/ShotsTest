package app.modules.shotdetails;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.guilhermecardoso.shotstest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import domain.entities.Shot;

/**
 * Created by guilhermecardoso on 11/14/17.
 */

public class ShotDetailsFragment extends Fragment implements ShotDetailsContract.View {

    @BindView(R.id.shot_detail_title)
    TextView textViewTitle;
    @BindView(R.id.shot_detail_image)
    SimpleDraweeView draweeDetailImage;
    @BindView(R.id.shot_detail_description)
    TextView textViewDescription;
    @BindView(R.id.shot_detail_views_count)
    TextView textViewViewsCount;
    @BindView(R.id.shot_detail_comments_count)
    TextView textViewCommentsCount;
    @BindView(R.id.shot_detail_created_at)
    TextView textViewCreatedAt;

    private ShotDetailsContract.Actions presenter;
    private SweetAlertDialog dialog;

    public static ShotDetailsFragment newInstance(long id) {
        ShotDetailsFragment fragment = new ShotDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putLong(ShotDetailsActivity.EXTRA_SHOT_ID, id);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.shots_detail_fragment, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();

        if (presenter != null && arguments != null) {
            presenter.loadShotDetails(arguments.getLong(ShotDetailsActivity.EXTRA_SHOT_ID));
        }
    }

    public void setPresenter(ShotDetailsContract.Actions presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showShotDetails(Shot shot) {
        this.textViewTitle.setText(shot.getTitle());
        this.textViewDescription.setText(shot.getFormattedDescription());
        this.textViewViewsCount.setText(shot.getFormattedViews());
        this.textViewCreatedAt.setText(shot.getFormattedCreatedDate());
        this.textViewCommentsCount.setText(shot.getFormattedCommentsCount());

        Uri uri = Uri.parse(shot.getImages().getHidpi());
        this.draweeDetailImage.setImageURI(uri);
    }

    @Override
    public void showLoading() {
        FragmentActivity activity = getActivity();
        if (dialog == null && activity != null) {
            dialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
            dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            dialog.setTitleText("Loading");
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (dialog != null) {
            dialog.dismissWithAnimation();
            dialog = null;
        }
    }

    @Override
    public void showError() {
        FragmentActivity activity = getActivity();
        if (dialog == null && activity != null) {
            dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE);
            dialog.setTitleText("Some error occurred");
            dialog.setCancelable(true);
            dialog.show();

        }
    }

    @Override
    public void onStop() {
        presenter.dispose();
        super.onStop();
    }
}
