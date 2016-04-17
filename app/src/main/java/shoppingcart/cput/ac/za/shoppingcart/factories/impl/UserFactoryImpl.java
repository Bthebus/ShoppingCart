package shoppingcart.cput.ac.za.shoppingcart.factories.impl;

import shoppingcart.cput.ac.za.shoppingcart.domain.User;
import shoppingcart.cput.ac.za.shoppingcart.factories.UserFactory;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class UserFactoryImpl implements UserFactory {
    private static UserFactoryImpl factory = null;

    public static UserFactoryImpl getInstance(){
        if (factory == null)
            factory = new UserFactoryImpl();
        return factory;
    }

    @Override
    public User createUser(String username, String password) {
        User user = new User.Builder().username(username).password(password).build();
        return user;
    }
}
