package shoppingcart.cput.ac.za.shoppingcart.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class Supplier implements Serializable {

    private Long id;
    private String supplierName;
    private List<Item> item;
    private Supplier(){}

    public Supplier(Builder builder){
        this.id = builder.id;
        this.supplierName = builder.supplierName;
        this.item = builder.item;
    }


    public Long getId() {
        return id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public List<Item> getItem() {
        return item;
    }

    //Builder starts here
    public static class Builder{
        private Long id;
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


        public Builder copy(Supplier supplier){
            this.id = supplier.id;
            this.supplierName = supplier.supplierName;
            this.item = supplier.item;
            return this;
        }

        public Supplier build(){
            return new Supplier(this);
        }
    }
}
