package shoppingcart.cput.ac.za.shoppingcart.factories.impl;

import java.util.List;

import shoppingcart.cput.ac.za.shoppingcart.domain.Item;
import shoppingcart.cput.ac.za.shoppingcart.domain.Supplier;
import shoppingcart.cput.ac.za.shoppingcart.factories.SupplierFactory;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class SupplierFactoryImpl implements SupplierFactory {
    private static SupplierFactoryImpl factory = null;

    public static SupplierFactoryImpl getInstance(){
        if (factory == null)
            factory = new SupplierFactoryImpl();
        return factory;
    }

    @Override
    public Supplier createSupplier(String supplierName, List<Item> item) {
        Supplier supplier = new Supplier.Builder().supplierName(supplierName).item(item).build();
        return supplier;
    }
}
