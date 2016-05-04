package shoppingcart.cput.ac.za.shoppingcart.factories;

import java.util.List;
import shoppingcart.cput.ac.za.shoppingcart.domain.*;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Address;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Contact;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Name;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public interface CustomerFactory {
    Customer createCustomer(Name name, Contact contactInformation, Address address, User user, List<Orders> orders);
}
