package meng.com.doordash.data;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by mengzhou on 9/05/19.
 */
public interface Service {

    @GET("restaurant")
    Single<List<RestaurantServerModel>> nearbyRestaurants(@QueryMap @NonNull Map<String, String> values);

    interface Callback<T> {
        void onResult(T result);
        void onError(Throwable error);
    }
}
