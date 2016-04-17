package shoppingcart.cput.ac.za.shoppingcart.factories.impl;

import java.util.List;

import shoppingcart.cput.ac.za.shoppingcart.domain.OrderLine;
import shoppingcart.cput.ac.za.shoppingcart.domain.Orders;
import shoppingcart.cput.ac.za.shoppingcart.factories.OrderLineFactory;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class OrderLineFactoryImpl implements OrderLineFactory {
    private static OrderLineFactoryImpl factory = null;

    public static OrderLineFactoryImpl getInstance(){
        if (factory == null)
            factory = new OrderLineFactoryImpl();
        return factory;
    }

    @Override
    public OrderLine createOrderLine(List<Orders> orders) {
        OrderLine orderLine = new OrderLine.Builder().order(orders).build();
        return orderLine;
    }
}
