package app.modules.shotslist.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.guilhermecardoso.shotstest.R;

import java.util.ArrayList;
import java.util.List;

import app.modules.shotslist.ShotsListContract;
import butterknife.BindView;
import butterknife.ButterKnife;
import domain.entities.Shot;

/**
 * Created by guilhermecardoso on 11/13/17.
 */

public class ShotsAdapter extends RecyclerView.Adapter<ShotsAdapter.ViewHolder> {

    private List<Shot> shots;
    private ShotsListContract.Actions presenter;

    public ShotsAdapter(List<Shot> shots, ShotsListContract.Actions presenter) {
        this.shots = shots;
        this.presenter = presenter;
    }

    public ShotsAdapter() {
        this.shots = new ArrayList<Shot>();
    }

    public void setPresenter(ShotsListContract.Actions presenter) {
        this.presenter = presenter;
    }

    public void add(Shot shot) {
        this.shots.add(shot);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shot_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Shot actualShot = shots.get(position);

        Uri uri = Uri.parse(actualShot.getImages().getHidpi());
        holder.image.setImageURI(uri);

        holder.title.setText(actualShot.getTitle());
        holder.createdAt.setText(actualShot.getFormattedCreatedDate());

        String viewCount = String.valueOf(actualShot.getFormattedViews());
        holder.viewCount.setText(viewCount);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onShotClicked(actualShot.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return shots.size();
    }

    public void setData(List<Shot> data) {
        this.shots = data;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shot_row_title)
        TextView title;
        @BindView(R.id.shot_row_created_at)
        TextView createdAt;
        @BindView(R.id.shot_row_view_count)
        TextView viewCount;
        @BindView(R.id.shot_row_image)
        SimpleDraweeView image;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
