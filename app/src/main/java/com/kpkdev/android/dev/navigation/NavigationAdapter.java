package com.kpkdev.android.dev.navigation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kpkdev.android.dev.R;
import com.kpkdev.android.dev.navigation.models.SlidingMenuItemModel;
import com.kpkdev.android.dev.utils.Logger;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by krasimir.karamazov on 7/7/2015.
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {

    private NavigationItemClickListener mListener;
    private List<SlidingMenuItemModel> mData;
    public NavigationAdapter(List<SlidingMenuItemModel> data, NavigationItemClickListener listener) {
        mData = data;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_drawer, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        SlidingMenuItemModel item = mData.get(i);
        viewHolder.txtView.setText(item.getLabel());
        if(item.getIcon() == SlidingMenuItemModel.NO_ICON) {
            viewHolder.imgIconView.setVisibility(View.GONE);
        }else{
            viewHolder.imgIconView.setImageResource(item.getIcon());
        }
        viewHolder.itemView.setOnClickListener(getOnClickListener(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_icon)
        ImageView imgIconView;

        @Bind(R.id.txt_title)
        TextView txtView;

        public ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    private View.OnClickListener getOnClickListener(final int index) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onNavigationEvent(index);
            }
        };
    }
}
