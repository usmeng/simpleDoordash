package meng.com.doordash;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import meng.com.doordash.view.RestaurantDetailEvent;
import meng.com.doordash.view.RestaurantDetailFragment;
import meng.com.doordash.view.RestaurantListFragment;

/**
 * Created by mengzhou on 9/05/19.
 */
public class MainActivity extends AppCompatActivity {

    private RestaurantListFragment restaurantListFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);

        restaurantListFragment = (RestaurantListFragment) getSupportFragmentManager().findFragmentByTag(RestaurantListFragment.TAG);
        if (restaurantListFragment == null) {
            restaurantListFragment = RestaurantListFragment.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.restaurant_fragment, restaurantListFragment, RestaurantListFragment.TAG)
                    .commit();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRestaurantDetailEvent(RestaurantDetailEvent movieDetailEvent) {
        RestaurantDetailFragment restaurantDetailFragment = RestaurantDetailFragment.newInstance(movieDetailEvent.getRestaurantViewModel());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.restaurant_fragment, restaurantDetailFragment, RestaurantDetailFragment.TAG)
                .addToBackStack("")
                .hide(restaurantListFragment)
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
