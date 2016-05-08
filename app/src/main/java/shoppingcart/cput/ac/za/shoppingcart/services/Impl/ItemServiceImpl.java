package shoppingcart.cput.ac.za.shoppingcart.services.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;

import shoppingcart.cput.ac.za.shoppingcart.conf.util.App;
import shoppingcart.cput.ac.za.shoppingcart.domain.Item;
import shoppingcart.cput.ac.za.shoppingcart.repository.ItemRepository;
import shoppingcart.cput.ac.za.shoppingcart.repository.impl.ItemRepositoryImpl;
import shoppingcart.cput.ac.za.shoppingcart.restapi.api.Impl.ItemAPIImpl;
import shoppingcart.cput.ac.za.shoppingcart.restapi.api.ItemAPI;
import shoppingcart.cput.ac.za.shoppingcart.services.ItemService;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public class ItemServiceImpl extends IntentService implements ItemService {

    private final ItemAPI api;
    private final ItemRepository repo;

    public static final String ACTION_ADD = "za.ac.cput.services.Impl.action.ADD";
    public static final String ACTION_UPDATE = "za.ac.cput.services.Impl.action.UPDATE";

    public static final String EXTRA_ADD = "za.ac.cput.services.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "za.ac.cput.services.Impl.extra.UPDATE";

    private static ItemServiceImpl service = null;

    public static ItemServiceImpl getInstance()
    {
        if (service == null)
            service = new ItemServiceImpl();
        return service;
    }

    private ItemServiceImpl()
    {
        super("ItemServiceImpl");
        api = new ItemAPIImpl();
        repo = new ItemRepositoryImpl(App.getAppContext());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent!=null)
        {
            final String action = intent.getAction();
            if(ACTION_ADD.equals(action))
            {
                final Item item = (Item) intent.getSerializableExtra(EXTRA_ADD);
                postItem(item);
            }
            else if( ACTION_UPDATE.equals(action))
            {
                final Item item = (Item) intent.getSerializableExtra(EXTRA_UPDATE);
                updateItem(item);
            }
        }
    }

    @Override
    public void addItem(Context context, Item item) {
        Intent intent = new Intent(context, Item.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, item);
        context.startService(intent);
    }

    @Override
    public void updateItem(Context context, Item item) {
        Intent intent = new Intent(context, Item.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, item);
        context.startService(intent);
    }

    private void updateItem(Item item)
    {
        //REMOTE UPDATE AND LOCAL UPDATE
        try {
            Item updatedItem = api.updateItem(item);
            repo.save(item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void postItem(Item item)
    {
        //POST AND LOCAL SAVE
        try {
            Item createdItem = api.createItem(item);
            repo.save(createdItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
