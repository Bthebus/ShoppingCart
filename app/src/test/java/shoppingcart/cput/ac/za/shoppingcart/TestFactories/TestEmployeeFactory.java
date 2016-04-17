package shoppingcart.cput.ac.za.shoppingcart.TestFactories;

import junit.framework.TestCase;

import org.junit.*;

import shoppingcart.cput.ac.za.shoppingcart.domain.Employee;
import shoppingcart.cput.ac.za.shoppingcart.domain.Name;
import shoppingcart.cput.ac.za.shoppingcart.domain.User;
import shoppingcart.cput.ac.za.shoppingcart.factories.EmployeeFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.NameFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.UserFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.EmployeeFactoryImpl;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.NameFactoryImpl;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.UserFactoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class TestEmployeeFactory extends TestCase{
    private User user, userCopy;
    private Name name, nameCopy;
    private Employee employee, employeeCopy;
    private NameFactory nameFactory;
    private UserFactory userFactory;
    private EmployeeFactory employeeFactory;

    @Before
    public void setUp(){
        nameFactory = NameFactoryImpl.getInstance();
        userFactory = UserFactoryImpl.getInstance();
        employeeFactory = EmployeeFactoryImpl.getInstance();
        //creating employee
        user = userFactory.createUser("Bob122", "789ppl4");
        name = nameFactory.createName("Sue", "Jean", "White");
        employee = employeeFactory.createEmployee(name, user);

        //creating employeeCopy
        //employee only wants password to be changed
        userCopy = new User.Builder().copy(user).password("787pp14").build();
        //Employee Surname changes
        nameCopy = new Name.Builder().copy(name).surname("Frittz").build();
        employeeCopy = new Employee.Builder().copy(employee).name(nameCopy).user(userCopy).build();
    }

    @After
    public void tearDown(){
        user = userCopy = null;
        name = nameCopy = null;
        employee = employeeCopy = null;
        nameFactory = null;
        userFactory = null;
        employeeFactory = null;
    }

    @Test
    public void testCreate()throws Exception{
        //testing name
        assertEquals("Sue", employee.getName().getName());
        assertEquals("Jean", employee.getName().getMiddleName());
        assertEquals("White", employee.getName().getSurname());

        //testing user info
        assertEquals("Bob122", employee.getUser().getUsername());
        assertEquals("789ppl4", employee.getUser().getPassword());
    }

    @Test
    public void testUpdate() throws Exception
    {
        //testing name update
        assertEquals("Sue", employeeCopy.getName().getName());
        assertEquals("Jean", employeeCopy.getName().getMiddleName());
        assertEquals("Frittz", employeeCopy.getName().getSurname());

        //testing user info update
        assertEquals("Bob122", employeeCopy.getUser().getUsername());
        assertEquals("787pp14", employeeCopy.getUser().getPassword());
    }
}
