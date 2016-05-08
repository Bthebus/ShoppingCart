package shoppingcart.cput.ac.za.shoppingcart.restapi.api.Impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Set;

import okhttp3.Request;
import okhttp3.Response;
import shoppingcart.cput.ac.za.shoppingcart.conf.util.AppUtil;
import shoppingcart.cput.ac.za.shoppingcart.restapi.api.CustomerAPI;
import shoppingcart.cput.ac.za.shoppingcart.restapi.resources.CustomerResource;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public class CustomerAPIImpl implements CustomerAPI{

    private static final String url = AppUtil.getBaserURI() + "api/shoppingcart/customer/get";

    @Override
    public Set<CustomerResource> getCustomer() throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = AppUtil.getConnection().newCall(request).execute();
        Type customer = new TypeToken<Set<CustomerResource>>(){}.getType();
        String value = response.body().string();
        return new Gson().fromJson(value, customer);
    }
}
