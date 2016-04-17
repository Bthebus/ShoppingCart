package shoppingcart.cput.ac.za.shoppingcart.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class Orders implements Serializable {

    private Long id;
    private String orderDate;
    private List<Item> item;

    private Orders(){}

    public Orders(Builder builder){
        this.id = builder.id;
        this.orderDate = builder.orderDate;
        this.item = builder.item;
    }

    public Long getId() {
        return id;
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

        public Builder copy(Orders orders){
            this.id = orders.id;
            this.orderDate = orders.orderDate;
            this.item = orders.item;
            return this;
        }

        public Orders build(){
            return new Orders(this);
        }
    }
}
