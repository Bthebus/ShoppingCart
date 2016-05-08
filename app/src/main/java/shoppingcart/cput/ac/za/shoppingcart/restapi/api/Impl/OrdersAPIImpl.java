package shoppingcart.cput.ac.za.shoppingcart.restapi.api.Impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Set;

import okhttp3.Request;
import okhttp3.Response;
import shoppingcart.cput.ac.za.shoppingcart.conf.util.AppUtil;
import shoppingcart.cput.ac.za.shoppingcart.restapi.api.OrdersAPI;
import shoppingcart.cput.ac.za.shoppingcart.restapi.resources.OrdersResource;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public class OrdersAPIImpl implements OrdersAPI{
    private static final String url = AppUtil.getBaserURI() + "api/shoppingcart/orders/get";

    @Override
    public Set<OrdersResource> getOrders() throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        Type orders = new TypeToken<Set<OrdersResource>>(){}.getType();
        String value = response.body().string();
        return new Gson().fromJson(value, orders);
    }
}
