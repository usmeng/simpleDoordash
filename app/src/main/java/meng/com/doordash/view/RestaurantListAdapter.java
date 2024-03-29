package meng.com.doordash.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import meng.com.doordash.R;

/**
 * Created by mengzhou on 9/05/19.
 */
public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantItemView> {

    private List<RestaurantViewModel> data = new ArrayList<>();
    private Context context;

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
    public RestaurantItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RestaurantItemView(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.restaurant_item_view, null, false));
    }

    @Override
    public void onBindViewHolder(final RestaurantItemView holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void cleanData() {
        data.clear();
        notifyDataSetChanged();
    }
}
