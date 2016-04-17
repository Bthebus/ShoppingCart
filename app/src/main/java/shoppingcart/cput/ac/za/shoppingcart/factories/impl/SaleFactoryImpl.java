package shoppingcart.cput.ac.za.shoppingcart.factories.impl;

import java.util.List;

import shoppingcart.cput.ac.za.shoppingcart.domain.Orders;
import shoppingcart.cput.ac.za.shoppingcart.domain.Sale;
import shoppingcart.cput.ac.za.shoppingcart.factories.SaleFactory;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class SaleFactoryImpl implements SaleFactory {
    private static SaleFactoryImpl factory = null;

    public static SaleFactoryImpl getInstance(){
        if (factory == null)
            factory = new SaleFactoryImpl();
        return factory;
    }
    @Override
    public Sale createSale(List<Orders> orders) {
        Sale sale = new Sale.Builder().order(orders).build();
        return sale;
    }
}
