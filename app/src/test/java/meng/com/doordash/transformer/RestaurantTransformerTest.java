package meng.com.doordash.transformer;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import meng.com.doordash.data.RestaurantServerModel;
import meng.com.doordash.view.RestaurantViewModel;

import static org.junit.Assert.*;

public class RestaurantTransformerTest {

    RestaurantTransformer app;

    @Before
    public void setup() {
        app = new RestaurantTransformer();
    }

    @Test
    public void toRestaurantList() {
        List<RestaurantServerModel> modelList = new ArrayList<>();
        modelList.add(createServerModel(10002, "Chilli garden", "Chinese", "Done"));
        modelList.add(createServerModel(10003, "Macdonald", "American", "30 mins"));

        List<RestaurantViewModel> restaurantViewModels = app.toRestaurantList(modelList);
        assertEquals(2, restaurantViewModels.size());
    }

    @Test
    public void toMovieViewModel() {
        RestaurantServerModel serverModel = createServerModel(100001, "Tsing Tao", "Chinese, soup", "20 mins");
        RestaurantViewModel restaurantViewModel = app.toMovieViewModel(serverModel);
        assertEquals("100001", restaurantViewModel.id);
        assertEquals("Chinese, soup", restaurantViewModel.description);
    }

    private RestaurantServerModel createServerModel(int id, String name,
                                                    String description,
                                                    String status) {
        RestaurantServerModel restaurantServerModel = new RestaurantServerModel();
        restaurantServerModel.id = id;
        restaurantServerModel.name = name;
        restaurantServerModel.description = description;
        restaurantServerModel.status = status;

        return restaurantServerModel;
    }
}