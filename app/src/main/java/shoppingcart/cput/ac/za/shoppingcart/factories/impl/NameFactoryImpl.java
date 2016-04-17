package shoppingcart.cput.ac.za.shoppingcart.factories.impl;

import shoppingcart.cput.ac.za.shoppingcart.domain.Name;
import shoppingcart.cput.ac.za.shoppingcart.factories.NameFactory;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class NameFactoryImpl implements NameFactory {

    private static NameFactoryImpl factory = null;

    public static NameFactoryImpl getInstance(){
        if (factory == null)
            factory = new NameFactoryImpl();
        return factory;
    }

    @Override
    public Name createName(String name, String middleName, String surname) {
        Name nme = new Name.Builder().name(name).middleName(middleName).surname(surname).build();
        return nme;
    }
}
