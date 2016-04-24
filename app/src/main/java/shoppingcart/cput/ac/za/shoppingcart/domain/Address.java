package shoppingcart.cput.ac.za.shoppingcart.domain;

import java.io.Serializable;

/**
 *Author       : Braedy Thebus
 *Stud num     : 213039168
 *Email        : bthebus2@gmail.com
 *Date created : 2016-04-17
 */
public class Address implements IAddress, Serializable {

    private Long Id;
    private String homeNumber;
    private String streetName;
    private String city;
    private String province;
    private String postalCode;

    private Address() {
    }

    public Address(Builder builder){
        this.Id = builder.Id;
        this.homeNumber = builder.homeNumber;
        this.streetName = builder.streetName;
        this.city = builder.city;
        this.province = builder.province;
        this.postalCode = builder.postalCode;
    }

    public Long getId()
    {
        return Id;
    }

    @Override
    public String getHomeNumber() {
        return homeNumber;
    }

    @Override
    public String getStreetName() {
        return streetName;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getProvince() {
        return province;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    //Builder starts here

    public static class Builder{

        private Long Id;
        private String homeNumber;
        private String streetName;
        private String city;
        private String province;
        private String postalCode;

        public Builder(){}

        public Builder(String homeNumber, String streetName, String city, String province, String postalCode) {
            this.homeNumber = homeNumber;
            this.streetName = streetName;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode;
        }

        public Builder Id(Long Id)
        {
            this.Id = Id;
            return this;
        }

        public Builder homeNumber(String homeNumber){
            this.homeNumber = homeNumber;
            return this;
        }

        public Builder streetName(String streetName){
            this.streetName = streetName;
            return this;
        }

        public Builder city(String city){
            this.city = city;
            return this;
        }

        public Builder province(String province){
            this.province = province;
            return this;
        }

        public Builder postalCode(String postalCode){
            this.postalCode = postalCode;
            return this;
        }

        public Builder copy(Address address){
            this.Id = address.Id;
            this.homeNumber = address.homeNumber;
            this.streetName = address.streetName;
            this.city = address.city;
            this.province = address.province;
            this.postalCode = address.postalCode;
            return this;
        }

        public Address build(){
            return new Address(this);
        }
    }
}