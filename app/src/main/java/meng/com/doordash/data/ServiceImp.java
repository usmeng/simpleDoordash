package meng.com.doordash.data;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mengzhou on 9/05/19.
 */
public class ServiceImp {

    // https://www.doordash.com/api/v2/restaurant/?lat=37.422740&lng=-122.139956&offset=0&limit=50
    private static final String BASE_URL = "https://www.doordash.com/api/v2/";
    private static final String LAT = "lat";
    private static final String LNG = "lng";
    private static final String OFFSET = "offset";
    private static final String LIMIT = "limit";

    private static class RetrofitAPIHolder {
        static ServiceImp instance = new ServiceImp();
    }

    private Service mAPI;

    private ServiceImp() {
        mAPI = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Service.class);
    }

    public static ServiceImp getInstance() {
        return RetrofitAPIHolder.instance;
    }

    public void getNearbyRestaurants(String lat, String lng, int limit, int offset, @NonNull final Service.Callback<List<RestaurantServerModel>> call) {
        getNearbyRestaurants(lat, lng, limit, offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<RestaurantServerModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<RestaurantServerModel> movieServerModels) {
                        call.onResult(movieServerModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        call.onError(e);
                    }
                });
    }

    public Single<List<RestaurantServerModel>> getNearbyRestaurants(String lat, String lng, int limit, int offset) {
        Map<String, String> values = new HashMap<>();
        values.put(LAT, lat);
        values.put(LNG, lng);
        values.put(OFFSET, String.valueOf(offset));
        values.put(LIMIT, String.valueOf(limit));

        return mAPI.nearbyRestaurants(values).subscribeOn(Schedulers.io());
    }
}
