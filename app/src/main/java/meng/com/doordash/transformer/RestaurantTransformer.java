package meng.com.doordash.transformer;

import java.util.ArrayList;
import java.util.List;

import meng.com.doordash.data.RestaurantServerModel;
import meng.com.doordash.view.RestaurantViewModel;

/**
 * Created by mengzhou on 9/05/19.
 */
public class RestaurantTransformer {

    public List<RestaurantViewModel> toRestaurantList(List<RestaurantServerModel> movieServerModelList) {
        List<RestaurantViewModel> list = new ArrayList<>();
        for (RestaurantServerModel model : movieServerModelList) {
            list.add(toMovieViewModel(model));
        }
        return list;
    }

    public RestaurantViewModel toMovieViewModel(RestaurantServerModel model) {
        RestaurantViewModel restaurantViewModel = new RestaurantViewModel();
        restaurantViewModel.coverImgUrl = model.cover_img_url;
        restaurantViewModel.name = model.name;
        restaurantViewModel.id = String.valueOf(model.id);
        restaurantViewModel.deliveryFee = String.valueOf(model.delivery_fee);
        restaurantViewModel.description = model.description;
        restaurantViewModel.status = model.status;
        return restaurantViewModel;
    }

}
