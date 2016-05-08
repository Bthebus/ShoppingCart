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
public class SupplierResource implements Serializable{

    private String supplierName;
    private List<Item> item;

    private SupplierResource(){}

    public SupplierResource(Builder builder){
        this.supplierName = builder.supplierName;
        this.item = builder.item;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public List<Item> getItem() {
        return item;
    }

    //Builder starts here
    public static class Builder{
        private String supplierName;
        private List<Item> item;

        public Builder(){}

        public Builder(String supplierName,List<Item> item){
            this.supplierName = supplierName;
            this.item = item;
        }

        public Builder supplierName(String supplierName){
            this.supplierName = supplierName;
            return this;
        }

        public Builder item(List<Item> item){
            this.item = item;
            return this;
        }


        public Builder copy(SupplierResource supplier){
            this.supplierName = supplier.supplierName;
            this.item = supplier.item;
            return this;
        }

        public SupplierResource build(){
            return new SupplierResource(this);
        }
    }
}
