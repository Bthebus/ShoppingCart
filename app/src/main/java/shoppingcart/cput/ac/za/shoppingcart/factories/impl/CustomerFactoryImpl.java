package shoppingcart.cput.ac.za.shoppingcart.factories.impl;

import java.util.List;

import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Address;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Contact;
import shoppingcart.cput.ac.za.shoppingcart.domain.Customer;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Name;
import shoppingcart.cput.ac.za.shoppingcart.domain.Orders;
import shoppingcart.cput.ac.za.shoppingcart.domain.User;
import shoppingcart.cput.ac.za.shoppingcart.factories.CustomerFactory;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class CustomerFactoryImpl implements CustomerFactory{

    private static CustomerFactoryImpl factory = null;

    public static CustomerFactoryImpl getInstance(){
        if (factory == null)
            factory = new CustomerFactoryImpl();

        return factory;
    }

    @Override
    public Customer createCustomer( Name name, Contact contactInformation, Address address, User user, List<Orders> orders) {
        Customer customer = new Customer.Builder().name(name).contactInformation(contactInformation).address(address).user(user).order(orders).build();
        return customer;
    }
}
