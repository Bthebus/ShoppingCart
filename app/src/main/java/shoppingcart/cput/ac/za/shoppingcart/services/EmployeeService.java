package shoppingcart.cput.ac.za.shoppingcart.services;

import android.content.Context;

import shoppingcart.cput.ac.za.shoppingcart.domain.Employee;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-08
 */
public interface EmployeeService {
    void addCustomer(Context context, Employee employee);
    void updateCustomer(Context context, Employee employee);
}
