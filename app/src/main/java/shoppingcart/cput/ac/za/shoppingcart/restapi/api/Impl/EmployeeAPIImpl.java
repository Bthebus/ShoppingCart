package shoppingcart.cput.ac.za.shoppingcart.restapi.api.Impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Set;

import okhttp3.Request;
import okhttp3.Response;
import shoppingcart.cput.ac.za.shoppingcart.conf.util.AppUtil;
import shoppingcart.cput.ac.za.shoppingcart.restapi.api.EmployeeAPI;
import shoppingcart.cput.ac.za.shoppingcart.restapi.resources.EmployeeResource;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public class EmployeeAPIImpl implements EmployeeAPI {
    private static final String url = AppUtil.getBaserURI() + "api/shoppingcart/employee/get";

    @Override
    public Set<EmployeeResource> getEmployee() throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = AppUtil.getConnection().newCall(request).execute();
        Type employee = new TypeToken<Set<EmployeeResource>>(){}.getType();
        String value = response.body().string();
        return new Gson().fromJson(value, employee);
    }
}
