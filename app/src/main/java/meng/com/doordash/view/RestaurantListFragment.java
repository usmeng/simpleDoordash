package meng.com.doordash.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import meng.com.doordash.data.RestaurantServerModel;
import meng.com.doordash.R;
import meng.com.doordash.data.Service;
import meng.com.doordash.data.ServiceImp;
import meng.com.doordash.databinding.RestaurantListFragmentBinding;
import meng.com.doordash.transformer.RestaurantTransformer;

/**
 * Created by mengzhou on 9/05/19.
 */
public class RestaurantListFragment extends Fragment {

    public static final String TAG = RestaurantListFragment.class.getSimpleName();
    private static final int LIMIT_COUNT = 20;
    private RestaurantListFragmentBinding restaurantListFragmentBinding;
    private RestaurantListAdapter restaurantListAdapter;
    private RestaurantTransformer restaurantListTransformer;
    private int offset = 0;
    private String locationLAT = "37.422740";
    private String locationLNG = "-122.139956";

    public static RestaurantListFragment newInstance() {
        return new RestaurantListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restaurantListTransformer = new RestaurantTransformer();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fetchData(locationLAT, locationLNG, offset);
    }

    private void fetchData(String lat, String lng, int offset) {
        ServiceImp.getInstance().getNearbyRestaurants(lat, lng, LIMIT_COUNT, offset, new Service.Callback<List<RestaurantServerModel>>() {
            @Override
            public void onResult(List<RestaurantServerModel> result) {
                restaurantListAdapter.addData(restaurantListTransformer.toRestaurantList(result));
            }

            @Override
            public void onError(Throwable error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        restaurantListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.restaurant_list_fragment, container, false);
        restaurantListFragmentBinding.gpsSearchButton.setOnClickListener(getOnSearchClickListener());

        restaurantListAdapter = new RestaurantListAdapter(getContext());
        restaurantListFragmentBinding.restaurantRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        restaurantListFragmentBinding.restaurantRecycleview.setAdapter(restaurantListAdapter);
        restaurantListFragmentBinding.restaurantRecycleview.addOnScrollListener(getOnScrollListener());
        return restaurantListFragmentBinding.getRoot();
    }

    private View.OnClickListener getOnSearchClickListener() {
        return v -> {
            locationLAT = restaurantListFragmentBinding.gpsLatEdittext.getText().toString();
            locationLNG = restaurantListFragmentBinding.gpsLngEdittext.getText().toString();
            if (locationLAT.isEmpty() || locationLNG.isEmpty()) {
                Toast.makeText(getActivity(), "LAT and LNG cannot be empty", Toast.LENGTH_LONG).show();
                return;
            }
            offset = 0;
            restaurantListAdapter.cleanData();
            fetchData(locationLAT, locationLNG, offset);
        };
    }

    @NonNull
    private RecyclerView.OnScrollListener getOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (!restaurantListFragmentBinding.restaurantRecycleview.canScrollVertically(1)) {
                        offset += LIMIT_COUNT;
                        fetchData(locationLAT, locationLNG, offset);
                    }
                }
            };
    }

}
