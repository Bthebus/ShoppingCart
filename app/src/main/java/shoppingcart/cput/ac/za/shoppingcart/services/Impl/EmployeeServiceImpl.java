package shoppingcart.cput.ac.za.shoppingcart.services.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;

import shoppingcart.cput.ac.za.shoppingcart.conf.util.App;
import shoppingcart.cput.ac.za.shoppingcart.domain.Employee;
import shoppingcart.cput.ac.za.shoppingcart.repository.EmployeeRepository;
import shoppingcart.cput.ac.za.shoppingcart.repository.impl.EmployeeRepositoryImpl;
import shoppingcart.cput.ac.za.shoppingcart.restapi.api.EmployeeAPI;
import shoppingcart.cput.ac.za.shoppingcart.restapi.api.Impl.EmployeeAPIImpl;
import shoppingcart.cput.ac.za.shoppingcart.services.EmployeeService;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public class EmployeeServiceImpl extends IntentService implements EmployeeService{

    private final EmployeeAPI api;
    private final EmployeeRepository repo;

    public static final String ACTION_ADD="za.ac.cput.services.Impl.action.ADD";
    public static final String ACTION_UPDATE="za.ac.cput.services.Impl.action.UPDATE";

    public static final String EXTRA_ADD="za.ac.cput.services.Impl.extra.ADD";
    public static final String EXTRA_UPDATE="za.ac.cput.services.Impl.extra.UPDATE";

    private static EmployeeServiceImpl service = null;

    public static EmployeeServiceImpl getInstance() {
        if (service == null)
            service = new EmployeeServiceImpl();
        return service;
    }

    private EmployeeServiceImpl() {
        super("EmployeeServiceImpl");
        api = new EmployeeAPIImpl();
        repo = new EmployeeRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addEmployee(Context context, Employee employee) {
        Intent intent = new Intent(context, Employee.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, employee);
        context.startService(intent);
    }

    @Override
    public void updateEmployee(Context context, Employee employee) {
        Intent intent = new Intent(context, Employee.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, employee);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent!=null)
        {
            final String action = intent.getAction();
            if(ACTION_ADD.equals(action))
            {
                final Employee employee = (Employee) intent.getSerializableExtra(EXTRA_ADD);
                postEmployee(employee);
            }
            else if( ACTION_UPDATE.equals(action))
            {
                final Employee employee = (Employee) intent.getSerializableExtra(EXTRA_UPDATE);
                updateEmployee(employee);
            }
        }
    }

    private void updateEmployee(Employee employee)
    {
        //REMOTE UPDATE AND LOCAL UPDATE
        try {
            Employee updatedEmployee = api.updateEmployee(employee);
            repo.save(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void postEmployee(Employee employee)
    {
        //POST AND LOCAL SAVE
        try {
            Employee createdEmployee = api.createEmployee(employee);
            repo.save(createdEmployee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
