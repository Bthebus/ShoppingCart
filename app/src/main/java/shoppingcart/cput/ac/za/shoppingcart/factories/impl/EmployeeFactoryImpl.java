package shoppingcart.cput.ac.za.shoppingcart.factories.impl;

import shoppingcart.cput.ac.za.shoppingcart.domain.Employee;
import shoppingcart.cput.ac.za.shoppingcart.domain.Name;
import shoppingcart.cput.ac.za.shoppingcart.domain.User;
import shoppingcart.cput.ac.za.shoppingcart.factories.EmployeeFactory;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class EmployeeFactoryImpl implements EmployeeFactory {
    private static EmployeeFactoryImpl factory = null;

    public static EmployeeFactoryImpl getInstance(){
        if (factory == null)
            factory = new EmployeeFactoryImpl();
        return factory;
    }

    @Override
    public Employee createEmployee(Name name, User user) {
        Employee employee = new Employee.Builder().name(name).user(user).build();
        return employee;
    }
}
