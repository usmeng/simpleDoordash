package meng.com.doordash.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import meng.com.doordash.databinding.RestaurantItemViewBinding;

/**
 * Created by mengzhou on 9/05/19.
 */
class RestaurantItemView extends RecyclerView.ViewHolder {

    public RestaurantItemViewBinding binding;

    public RestaurantItemView(@NonNull RestaurantItemViewBinding restaurantItemViewBinding) {
        super(restaurantItemViewBinding.getRoot());
        this.binding = restaurantItemViewBinding;
    }

    public void bind(final RestaurantViewModel restaurantViewModel) {
        binding.setViewmodel(restaurantViewModel);
        binding.restaurantContainer.setOnClickListener(v -> EventBus.getDefault().post(new RestaurantDetailEvent(restaurantViewModel)));
        Glide.with(binding.getRoot().getContext()).load(restaurantViewModel.coverImgUrl).into(binding.restaurantImage);
    }
}
