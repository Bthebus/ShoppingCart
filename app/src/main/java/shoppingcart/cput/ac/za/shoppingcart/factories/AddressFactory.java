package shoppingcart.cput.ac.za.shoppingcart.factories;

import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Address;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public interface AddressFactory {
    Address createAddress(String homeNumber, String streetName,String city,String province, String postalCode);
}
