package shoppingcart.cput.ac.za.shoppingcart.factories.impl;

import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Contact;
import shoppingcart.cput.ac.za.shoppingcart.factories.ContactFactory;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class ContactFactoryImpl implements ContactFactory{
    private static ContactFactoryImpl factory = null;

    private ContactFactoryImpl() {
    }

    public static ContactFactoryImpl getInstance(){
        if (factory == null)
            factory = new ContactFactoryImpl();

        return factory;
    }

    @Override
    public Contact createContact(String email, String telephone, String cellphone) {
        Contact contact = new Contact.Builder().email(email).telephone(telephone).cellphone(cellphone).build();
        return contact;
    }
}
