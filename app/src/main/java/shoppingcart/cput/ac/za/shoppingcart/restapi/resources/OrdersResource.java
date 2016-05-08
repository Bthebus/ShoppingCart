package shoppingcart.cput.ac.za.shoppingcart.restapi.resources;

import java.io.Serializable;
import java.util.List;

import shoppingcart.cput.ac.za.shoppingcart.domain.Item;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public class OrdersResource implements Serializable{
        private String orderDate;
        private List<Item> item;

        private OrdersResource(){}

        public OrdersResource(Builder builder){
            this.orderDate = builder.orderDate;
            this.item = builder.item;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public List<Item> getItem(){
            return item;
        }

    // Builder starts here
    public static class Builder{
        private Long id;
        private String orderDate;
        private List<Item> item;

        public Builder(){}

        public Builder(String orderDate, List<Item> item){
            this.orderDate = orderDate;
            this.item = item;
        }

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder orderDate(String orderDate){
            this.orderDate = orderDate;
            return this;
        }

        public Builder item (List<Item> item)
        {
            this.item = item;
            return this;
        }

        public Builder copy(OrdersResource orders){
            this.orderDate = orders.orderDate;
            this.item = orders.item;
            return this;
        }

        public OrdersResource build(){
            return new OrdersResource(this);
        }
    }
}

