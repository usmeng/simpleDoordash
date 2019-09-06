package meng.com.doordash.view;

import java.io.Serializable;

/**
 * Created by mengzhou on 9/05/19.
 */
public class RestaurantViewModel implements Serializable {
    public String id;
    public String name;
    public String description;
    public String coverImgUrl;
    public String status;
    public String deliveryFee;
}
