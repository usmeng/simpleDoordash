package meng.com.doordash.view;

/**
 * Created by mengzhou on 9/05/19.
 */
public class RestaurantDetailEvent {

    private RestaurantViewModel restaurantViewModel;

    public RestaurantDetailEvent(RestaurantViewModel restaurantViewModel) {
        this.restaurantViewModel = restaurantViewModel;
    }

    public RestaurantViewModel getRestaurantViewModel() {
        return restaurantViewModel;
    }

}
