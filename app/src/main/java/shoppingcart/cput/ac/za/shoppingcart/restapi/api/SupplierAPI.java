package shoppingcart.cput.ac.za.shoppingcart.restapi.api;

import java.io.IOException;
import java.util.Set;

import shoppingcart.cput.ac.za.shoppingcart.restapi.resources.SupplierResource;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public interface SupplierAPI {
    Set<SupplierResource> getSupplier() throws IOException;
}
