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
import shoppingcart.cput.ac.za.shoppingcart.domain.Item;
import shoppingcart.cput.ac.za.shoppingcart.restapi.api.ItemAPI;
import shoppingcart.cput.ac.za.shoppingcart.restapi.resources.ItemResource;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public class ItemAPIImpl implements ItemAPI {

    private static final String url = AppUtil.getBaserURI() + "api/shoppingcart/item/get";
    private static final String postUrl = AppUtil.getBaserURI() + "api/shoppingcart/item/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/shoppingcart/item/update";

    @Override
    public Set<ItemResource> getItem() throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        Type item = new TypeToken<Set<ItemResource>>(){}.getType();
        String value = response.body().string();
        return new Gson().fromJson(value, item);
    }

    @Override
    public Item createItem(Item item) throws IOException {
        String json = new Gson().toJson(item);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Item itm = new Gson().fromJson(value, Item.class);
        return itm;
    }

    @Override
    public Item updateItem(Item item) throws IOException {
        String json = new Gson().toJson(item);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Item itm = new Gson().fromJson(value, Item.class);
        return itm;
    }
}
