package shoppingcart.cput.ac.za.shoppingcart.restapi.resources;

import java.io.Serializable;
import java.util.List;

import shoppingcart.cput.ac.za.shoppingcart.domain.Orders;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public class OrderLineResource implements Serializable {

    private List<Orders> orders;

    private OrderLineResource(){}

    public OrderLineResource(Builder builder){
        this.orders = builder.orders;
    }


    public List<Orders> getOrders() {
        return orders;
    }

    //Builder starts here

    public static class Builder{

        private List<Orders> orders;

        public Builder(){}

        public Builder(List<Orders> orders){
            this.orders = orders;
        }

        public Builder order(List<Orders> orders){
            this.orders = orders;
            return this;
        }

        public Builder copy(OrderLineResource orderLine){
            this.orders = orderLine.orders;
            return this;
        }

        public OrderLineResource build() {
            return new OrderLineResource(this);
        }
    }
}

