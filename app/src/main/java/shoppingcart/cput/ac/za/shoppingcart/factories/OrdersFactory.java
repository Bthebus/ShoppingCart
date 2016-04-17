package shoppingcart.cput.ac.za.shoppingcart.factories;

import java.util.List;

import shoppingcart.cput.ac.za.shoppingcart.domain.*;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public interface OrdersFactory {
    Orders createOrders(String orderDate,List<Item>item);
}
