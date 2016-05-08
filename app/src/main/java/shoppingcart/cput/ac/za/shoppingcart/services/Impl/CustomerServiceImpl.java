package shoppingcart.cput.ac.za.shoppingcart.services.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;

import shoppingcart.cput.ac.za.shoppingcart.conf.util.App;
import shoppingcart.cput.ac.za.shoppingcart.domain.Customer;
import shoppingcart.cput.ac.za.shoppingcart.repository.CustomerRepository;
import shoppingcart.cput.ac.za.shoppingcart.repository.impl.CustomerRepositoryImpl;
import shoppingcart.cput.ac.za.shoppingcart.restapi.api.CustomerAPI;
import shoppingcart.cput.ac.za.shoppingcart.restapi.api.Impl.CustomerAPIImpl;
import shoppingcart.cput.ac.za.shoppingcart.services.CustomerService;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public class CustomerServiceImpl extends IntentService implements CustomerService{

    private final CustomerAPI api;
    private final CustomerRepository repo;

    public static final String ACTION_ADD = "za.ac.cput.shoppingcart.services.Impl.action.ADD";
    public static final String ACTION_UPDATE = "za.ac.cput.shoppingcart.services.Impl.action.UPDATE";

    public static final String EXTRA_ADD = "za.ac.cput.shoppingcart.services.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "za.ac.cput.shoppingcart.services.Impl.extra.UPDATE";

    private static CustomerServiceImpl service = null;

    public static CustomerServiceImpl getInstance()
    {
        if(service ==null)
            service = new CustomerServiceImpl();
        return service;
    }

    private CustomerServiceImpl()
    {
        super("CustomerServiceImpl");
        api = new CustomerAPIImpl();
        repo = new CustomerRepositoryImpl(App.getAppContext());
    }
    @Override
    public void addCustomer(Context context, Customer customer) {
        Intent intent = new Intent(context, CustomerServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, customer);
        context.startService(intent);
    }

    @Override
    public void updateCustomer(Context context, Customer customer) {
        Intent intent = new Intent(context, CustomerServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, customer);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Customer customer = (Customer) intent.getSerializableExtra(EXTRA_ADD);
                postCustomer(customer);
            } else if (ACTION_UPDATE.equals(action)) {
                final Customer customer = (Customer) intent.getSerializableExtra(EXTRA_UPDATE);
                updateCustomer(customer);
            }
        }
    }

        private void updateCustomer(Customer customer) {
            //REMOTE UPDATE AND LOCAL UPDATE
            try {
                Customer updatedContact = api.updateCustomer(customer);
                repo.save(updatedContact);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void postCustomer(Customer customer) {
            //POST AND LOCAL SAVE
            try {
                Customer createdCustomer = api.createCustomer(customer);
                repo.save(createdCustomer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
