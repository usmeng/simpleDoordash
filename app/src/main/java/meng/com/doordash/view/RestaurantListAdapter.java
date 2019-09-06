package meng.com.doordash.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import meng.com.doordash.R;

/**
 * Created by mengzhou on 9/05/19.
 */
public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListViewHolder> {

    private List<RestaurantViewModel> data = new ArrayList<>();
    private Context context;
    private RestaurantListFragment.RestaurantOnItemClickListener onItemClickListener;

    public RestaurantListAdapter(@NonNull Context context) {
        this.context = context;
    }

    public List<RestaurantViewModel> getData() {
        return data;
    }

    public void addData(List<RestaurantViewModel> data) {
        this.data.addAll(data);
        notifyItemRangeChanged(this.data.size() - data.size(), data.size());
    }

    @Override
    public RestaurantListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RestaurantListViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.restaurant_item_view, null, false));
    }

    @Override
    public void onBindViewHolder(final RestaurantListViewHolder holder, int position) {
        RestaurantViewModel restaurantViewModel = data.get(position);
        Glide.with(context).load(restaurantViewModel.coverImgUrl).into(holder.binding.restaurantImage);
        holder.binding.setViewmodel(restaurantViewModel);
        holder.binding.restaurantContainer.setOnClickListener(v -> {
            if (onItemClickListener == null) return;
            onItemClickListener.onItemClick(restaurantViewModel);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(RestaurantListFragment.RestaurantOnItemClickListener clickListener) {
        this.onItemClickListener = clickListener;
    }

    public void cleanData() {
        data.clear();
        notifyDataSetChanged();
    }
}
