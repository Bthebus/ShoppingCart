package shoppingcart.cput.ac.za.shoppingcart.factories;

import shoppingcart.cput.ac.za.shoppingcart.domain.*;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Name;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.User;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public interface EmployeeFactory {
    Employee createEmployee(Name name, User user);
}
