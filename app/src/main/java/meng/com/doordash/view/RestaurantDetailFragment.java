package meng.com.doordash.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import meng.com.doordash.R;
import meng.com.doordash.databinding.RestaurantItemDetailViewBinding;

/**
 * Created by mengzhou on 9/05/19.
 */
public class RestaurantDetailFragment extends Fragment {

    public static final String TAG = RestaurantDetailFragment.class.getSimpleName();
    public static final String RESTAURANT_DETAIL_KEY = "restaurant_detail";
    private RestaurantItemDetailViewBinding restaurantItemDetailViewBinding;
    private RestaurantViewModel restaurantDetailViewModel;

    public static RestaurantDetailFragment newInstance(RestaurantViewModel restaurantViewModel) {
        RestaurantDetailFragment restaurantDetailFragment = new RestaurantDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(RESTAURANT_DETAIL_KEY, restaurantViewModel);
        restaurantDetailFragment.setArguments(bundle);
        return restaurantDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restaurantDetailViewModel = (RestaurantViewModel) getArguments().getSerializable(RESTAURANT_DETAIL_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        restaurantItemDetailViewBinding = DataBindingUtil.inflate(inflater, R.layout.restaurant_item_detail_view, container, false);
        return restaurantItemDetailViewBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        restaurantItemDetailViewBinding.setViewmodel(restaurantDetailViewModel);
        Glide.with(getContext()).load(restaurantDetailViewModel.coverImgUrl).into(restaurantItemDetailViewBinding.restaurantImage);
    }
}
