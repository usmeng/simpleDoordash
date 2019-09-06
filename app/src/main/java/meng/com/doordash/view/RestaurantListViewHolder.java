package meng.com.doordash.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import meng.com.doordash.databinding.RestaurantItemViewBinding;

/**
 * Created by mengzhou on 9/05/19.
 */
class RestaurantListViewHolder extends RecyclerView.ViewHolder {

    public RestaurantItemViewBinding binding;

    public RestaurantListViewHolder(@NonNull RestaurantItemViewBinding restaurantItemViewBinding) {
        super(restaurantItemViewBinding.getRoot());
        this.binding = restaurantItemViewBinding;
    }

}
