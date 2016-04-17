package shoppingcart.cput.ac.za.shoppingcart.domain;

import java.io.Serializable;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class Item implements Serializable {

    private Long id;
    private String name;
    private String imageLocation;
    private String description;
    private double price;
    private int quantity;

    private Item(){}

    public Item(Builder builder){
        this.name = builder.name;
        this.imageLocation = builder.imageLocation;
        this.description = builder.description;
        this.price = builder.price;
        this.quantity = builder.quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    //Builder starts here
    public static class Builder{
        private Long id;
        private String name;
        private String imageLocation;
        private String description;
        private double price;
        private int quantity;

        public Builder(){}

        public Builder(String name,String imageLocation,String description,double price,int quantity){
            this.name = name;
            this.imageLocation = imageLocation;
            this.description = description;
            this.price = price;
            this.quantity = quantity;
        }

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder imageLocation(String imageLocation)
        {
            this.imageLocation = imageLocation;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder price(double price){
            this.price = price;
            return this;
        }

        public Builder quantity(int quantity){
            this.quantity = quantity;
            return this;
        }

        public Builder copy(Item item){
            this.id = item.id;
            this.name = item.name;
            this.description = item.description;
            this.price = item.price;
            this.quantity = item.quantity;
            return this;
        }

        public Item build(){
            return new Item(this);
        }
    }
}
