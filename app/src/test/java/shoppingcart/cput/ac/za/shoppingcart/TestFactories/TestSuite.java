package shoppingcart.cput.ac.za.shoppingcart.TestFactories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestAddressFactory.class,
        TestContactFactory.class,
        TestCustomerFactory.class,
        TestEmployeeFactory.class,
        TestItemFactory.class,
        TestNameFactory.class,
        TestOrderLineFactory.class,
        TestOrdersFactory.class,
        TestSaleFactory.class,
        TestSupplierFactory.class,
        TestUserFactory.class
})
public class TestSuite {
}
