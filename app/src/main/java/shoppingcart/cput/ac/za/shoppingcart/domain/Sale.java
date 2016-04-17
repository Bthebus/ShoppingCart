package shoppingcart.cput.ac.za.shoppingcart.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class Sale implements Serializable {

    private Long id;
    private List<Orders> orders;

    private Sale(){}

    public Sale(Builder builder){
        this.id = builder.id;
        this.orders = builder.orders;
    }

    public Long getId() {
        return id;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    //Builder Starts here

    public static class Builder{
        private Long id;
        private List<Orders> orders;

        public Builder(){}

        public Builder(List<Orders> orders){
            this.orders = orders;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder order(List<Orders> orders){
            this.orders = orders;
            return this;
        }

        public Builder copy(Sale sale){
            this.id = sale.id;
            this.orders = sale.orders;
            return this;
        }

        public Sale build(){
            return new Sale(this);
        }
    }
}
