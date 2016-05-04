package shoppingcart.cput.ac.za.shoppingcart.factories;

import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Contact;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public interface ContactFactory {
    Contact createContact(String email, String telephone,String cellphone);
}
