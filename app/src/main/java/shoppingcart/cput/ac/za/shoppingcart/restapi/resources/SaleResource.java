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
public class SaleResource implements Serializable {
    private List<Orders> orders;

    private SaleResource(){}

    public SaleResource(Builder builder){
        this.orders = builder.orders;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    //Builder Starts here

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

        public Builder copy(SaleResource sale){
            this.orders = sale.orders;
            return this;
        }

        public SaleResource build(){
            return new SaleResource(this);
        }
    }
}
