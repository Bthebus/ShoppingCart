package shoppingcart.cput.ac.za.shoppingcart.repository;

import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import shoppingcart.cput.ac.za.shoppingcart.domain.Customer;
import shoppingcart.cput.ac.za.shoppingcart.domain.Item;
import shoppingcart.cput.ac.za.shoppingcart.domain.Orders;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Address;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Contact;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Name;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.User;
import shoppingcart.cput.ac.za.shoppingcart.repository.impl.CustomerRepositoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-04
 */
public class CustomerRepositoryTest extends AndroidTestCase {
    private static final String TAG = "CUSTOMER TEST";
    private Long id;
    private CustomerRepository repository;

    private Name name;
    private Contact contact;
    private Address address;
    private User user;

    private Orders order;
    private Item item;

    private List<Orders> orders;
    private List<Item> items;

    private Date date;

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        repository = new CustomerRepositoryImpl(this.getContext());
        orders = new ArrayList<Orders>();
        items = new ArrayList<Item>();

        name = new Name.Builder().name("Braedy")
                .middleName("Elwyn")
                .surname("Thebus")
                .build();

        contact = new Contact.Builder()
                .email("bthebus2@gmail.com")
                .telephone("0211234567")
                .cellphone("0841234657")
                .build();

        address = new Address.Builder()
                .homeNumber("7")
                .streetName("Pool Road")
                .city("Cape town")
                .province("Western Cape")
                .postalCode("7750")
                .build();

        user = new User.Builder()
                .username("Braedy12")
                .password("1!Abc")
                .build();

        item = new Item.Builder()
                .name("Fly shoes")
                .imageLocation("/images/image1.jpg")
                .description("these are shoes")
                .price(21.00)
                .quantity(10)
                .build();

        items.add(item);
        date = new Date();

        order = new Orders.Builder()
                .orderDate(date.toString())
                .item(items)
                .build();
        orders.add(order);
    }

    @Override
    public void tearDown() throws Exception
    {
        repository = null;
        name = null;
        contact = null;
        address = null;
        user = null;
        order = null;
        orders =null;
        item = null;
        items =null;
        date = null;
    }

    public void testCRUD() throws Exception
    {

        //CREATE
        Customer createEntity = new Customer.Builder()
                .name(name)
                .contactInformation(contact)
                .address(address)
                .user(user)
                .order(orders)
                .build();
        Customer insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        assertNotNull(TAG + "CREATE", insertedEntity);



        //READ ALL

        Set<Customer> customers = repository.findAll();
        assertTrue(TAG + " READ ENTITY ", customers.size()>0);


        //READ ENTITY


    }

}
