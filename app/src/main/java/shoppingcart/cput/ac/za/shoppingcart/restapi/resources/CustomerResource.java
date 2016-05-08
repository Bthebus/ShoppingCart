package shoppingcart.cput.ac.za.shoppingcart.restapi.resources;

import java.io.Serializable;
import java.util.List;

import shoppingcart.cput.ac.za.shoppingcart.domain.Orders;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Address;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Contact;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Name;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.User;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public class CustomerResource implements Serializable{
    private Name name;
    private Contact contactInformation;
    private Address address;
    private User user;

    private List<Orders> orders;


    private CustomerResource(){}

    public CustomerResource(Builder builder){
        this.name = builder.name;
        this.contactInformation = builder.contactInformation;
        this.address = builder.address;
        this.user = builder.user;
        this.orders = builder.orders;
    }

    public Name getName() {
        return name;
    }

    public Contact getContactInformation() {
        return contactInformation;
    }

    public Address getAddress() {
        return address;
    }

    public User getUser() {
        return user;
    }

    public List<Orders> getOrders(){
        return orders;
    }
    //Builder starts here
    public static class Builder{
        private Name name;
        private Contact contactInformation;
        private Address address;
        private User user;
        private List<Orders> orders;

        public Builder() {
        }

        public Builder(Name name, Contact contactInformation, Address address, User user, List<Orders> orders) {
            this.name = name;
            this.contactInformation = contactInformation;
            this.address = address;
            this.user = user;
            this.orders = orders;
        }

        public Builder name(Name name){
            this.name = name;
            return this;
        }

        public Builder contactInformation(Contact contactInformation){
            this.contactInformation = contactInformation;
            return this;
        }

        public Builder address(Address address){
            this.address = address;
            return this;
        }

        public Builder user(User user)
        {
            this.user = user;
            return this;
        }

        public Builder order(List<Orders> orders)
        {
            this.orders = orders;
            return this;
        }


        public Builder copy(CustomerResource customer){
            this.name = customer.name;
            this.contactInformation = customer.contactInformation;
            this.address = customer.address;
            this.user = customer.user;
            this.orders = customer.orders;
            return this;
        }

        public CustomerResource build(){
            return new CustomerResource(this);
        }
    }
}
