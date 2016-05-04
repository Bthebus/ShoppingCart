package shoppingcart.cput.ac.za.shoppingcart.factories.impl;

import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Address;
import shoppingcart.cput.ac.za.shoppingcart.factories.AddressFactory;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class AddressFactoryImpl implements AddressFactory{
    private static AddressFactoryImpl factory = null;

    private AddressFactoryImpl()
    {}

    public static AddressFactoryImpl getInstance()
    {
        if(factory ==null)
            factory = new AddressFactoryImpl();
        return factory;
    }

    @Override
    public Address createAddress(String homeNumber, String streetName, String city, String province, String postalCode) {
        Address address = new Address.Builder().homeNumber(homeNumber).streetName(streetName).city(city).province(province).postalCode(postalCode).build();
        return address;
    }
}
