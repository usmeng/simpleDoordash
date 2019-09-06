package meng.com.doordash.data;

import org.junit.Test;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by mengzhou on 9/05/19.
 */
public class RetrofitAPITest {

    @Test
    public void getRestaurants() throws Exception {
        System.out.println("---- getNearbyRestaurants ----");
        ServiceImp api = ServiceImp.getInstance();
        api.getNearbyRestaurants("37.422740", "-122.139956", 20).subscribe(new SingleObserver<List<RestaurantServerModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("Disposable: " + d.toString());
            }

            @Override
            public void onSuccess(List<RestaurantServerModel> movieServerModels) {
                System.out.println("movieServerModels size: " + movieServerModels.size());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Throwable message: " + e.getMessage());
            }
        });
        Thread.sleep(5 * 1000);
    }

}