package shoppingcart.cput.ac.za.shoppingcart.factories.impl;

import java.util.List;

import shoppingcart.cput.ac.za.shoppingcart.domain.Item;
import shoppingcart.cput.ac.za.shoppingcart.domain.Orders;
import shoppingcart.cput.ac.za.shoppingcart.factories.OrdersFactory;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class OrdersFactoryImpl implements OrdersFactory {
    private static OrdersFactoryImpl factory = null;

    public static OrdersFactoryImpl getInstance(){
        if (factory == null)
            factory = new OrdersFactoryImpl();
        return factory;
    }

    @Override
    public Orders createOrders(String orderDate, List<Item> item) {
        Orders orders = new Orders.Builder().orderDate(orderDate).item(item).build();
        return orders;
    }
}
