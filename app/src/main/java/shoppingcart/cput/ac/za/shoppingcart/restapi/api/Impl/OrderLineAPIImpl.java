package shoppingcart.cput.ac.za.shoppingcart.restapi.api.Impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Set;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import shoppingcart.cput.ac.za.shoppingcart.conf.util.AppUtil;
import shoppingcart.cput.ac.za.shoppingcart.domain.OrderLine;
import shoppingcart.cput.ac.za.shoppingcart.restapi.api.OrderLineAPI;
import shoppingcart.cput.ac.za.shoppingcart.restapi.resources.OrderLineResource;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public class OrderLineAPIImpl implements OrderLineAPI {
    private static final String url = AppUtil.getBaserURI() + "api/shoppingcart/orderline/get";
    private static final String postUrl = AppUtil.getBaserURI() + "api/shoppingcart/orderline/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/shoppingcart/orderline/update";

    @Override
    public Set<OrderLineResource> getOrderLine() throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        Type orderLine = new TypeToken<Set<OrderLineResource>>(){}.getType();
        String value = response.body().string();
        return new Gson().fromJson(value, orderLine);
    }

    @Override
    public OrderLine createOrderLine(OrderLine orderLine) throws IOException {
        String json = new Gson().toJson(orderLine);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        OrderLine ol = new Gson().fromJson(value, OrderLine.class);
        return ol;
    }

    @Override
    public OrderLine updateOrderLine(OrderLine orderLine) throws IOException {
        String json = new Gson().toJson(orderLine);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        OrderLine ol = new Gson().fromJson(value, OrderLine.class);
        return ol;
    }
}
