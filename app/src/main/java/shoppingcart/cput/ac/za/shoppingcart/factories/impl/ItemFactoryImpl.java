package shoppingcart.cput.ac.za.shoppingcart.factories.impl;

import shoppingcart.cput.ac.za.shoppingcart.domain.Item;
import shoppingcart.cput.ac.za.shoppingcart.factories.ItemFactory;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class ItemFactoryImpl implements ItemFactory {
    private static ItemFactoryImpl factory = null;

    public static ItemFactoryImpl getInstance(){
        if (factory == null)
            factory = new ItemFactoryImpl();
        return factory;
    }

    @Override
    public Item createItem(String name, String imageLocation, String description, double price, int quantity) {
        Item item = new Item.Builder().name(name).imageLocation(imageLocation).description(description).price(price).quantity(quantity).build();
        return item;
    }
}
