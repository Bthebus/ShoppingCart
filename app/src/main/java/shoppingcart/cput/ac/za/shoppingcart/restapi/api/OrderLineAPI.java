package shoppingcart.cput.ac.za.shoppingcart.restapi.api;

import java.io.IOException;
import java.util.Set;

import shoppingcart.cput.ac.za.shoppingcart.domain.OrderLine;
import shoppingcart.cput.ac.za.shoppingcart.restapi.resources.OrderLineResource;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public interface OrderLineAPI {
    Set<OrderLineResource> getOrderLine() throws IOException;
    OrderLine createOrderLine(OrderLine orderLine) throws IOException;
    OrderLine updateOrderLine(OrderLine orderLine) throws IOException;
}
