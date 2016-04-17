package shoppingcart.cput.ac.za.shoppingcart.TestFactories;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import shoppingcart.cput.ac.za.shoppingcart.domain.Address;
import shoppingcart.cput.ac.za.shoppingcart.domain.Contact;
import shoppingcart.cput.ac.za.shoppingcart.domain.Customer;
import shoppingcart.cput.ac.za.shoppingcart.domain.Item;
import shoppingcart.cput.ac.za.shoppingcart.domain.Name;
import shoppingcart.cput.ac.za.shoppingcart.domain.Orders;
import shoppingcart.cput.ac.za.shoppingcart.domain.User;
import shoppingcart.cput.ac.za.shoppingcart.factories.AddressFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.ContactFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.CustomerFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.ItemFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.NameFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.OrdersFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.UserFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.AddressFactoryImpl;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.ContactFactoryImpl;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.CustomerFactoryImpl;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.ItemFactoryImpl;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.NameFactoryImpl;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.OrdersFactoryImpl;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.UserFactoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class TestCustomerFactory extends TestCase{
    private Customer customer, customerCopy;
    private Name name, nameCopy;
    private Contact contact, contactCopy;
    private Address address, addressCopy;
    private User user, userCopy;
    private Date date;
    private Item item, itemCopy;
    private List<Item> items, itemsCopy;
    private Orders order, orderCopy;
    private List<Orders> orders, ordersCopy;
    private NameFactory nameFactory;
    private ContactFactory contactFactory;
    private AddressFactory addressFactory;
    private UserFactory userFactory;
    private ItemFactory itemFactory;
    private OrdersFactory ordersFactory;
    private CustomerFactory customerFactory;



    @Before
    public void setUp(){

        nameFactory = NameFactoryImpl.getInstance();
        contactFactory = ContactFactoryImpl.getInstance();
        addressFactory = AddressFactoryImpl.getInstance();
        userFactory = UserFactoryImpl.getInstance();
        itemFactory = ItemFactoryImpl.getInstance();
        ordersFactory = OrdersFactoryImpl.getInstance();
        customerFactory = CustomerFactoryImpl.getInstance();

        items = new ArrayList<Item>();
        orders = new ArrayList<Orders>();
        itemsCopy = new ArrayList<Item>();
        ordersCopy = new ArrayList<Orders>();

        //customer
        name = nameFactory.createName("Braedy", "Elwyn", "Thebus");
        contact = contactFactory.createContact("bthebus2@gmail.com", "0213456789", "0831234567");
        address = addressFactory.createAddress("7", "Line Road", "cape town", "western cape", "7732");
        user = userFactory.createUser("Braedy123", "A1B2C3");
        item = itemFactory.createItem("Something", "/images/image1.jpg", "This is something", 19.99, 200);
        items.add(item);
        date = new Date();
        order = ordersFactory.createOrders(date.toString(), items);
        orders.add(order);
        customer = customerFactory.createCustomer(name, contact, address, user, orders);

        //customerCopy
        nameCopy = new Name.Builder().copy(name).name("Jeff").middleName("Elwyn").surname("Thebus").build();
        contactCopy = new Contact.Builder().copy(contact).email("213039168@mycput.ac.za").telephone("0213456789").cellphone("0831234567").build();
        addressCopy = new Address.Builder().copy(address).homeNumber("45").streetName("Qwerty Street").city("Joburg").province("Gauteng").postalCode("8896").build();
        userCopy = new User.Builder().copy(user).username("Braedy123").password("A1b2C3D4").build();
        itemCopy = new Item.Builder().copy(item).name("Something else").imageLocation("/images/image1.jpg").description("this is something else").price(59.99).quantity(150).build();
        itemsCopy.add(itemCopy);
        orderCopy = new Orders.Builder().copy(order).orderDate(date.toString()).item(itemsCopy).build();
        ordersCopy.add(orderCopy);
        customerCopy = new Customer.Builder().copy(customer).name(nameCopy).contactInformation(contactCopy).address(addressCopy).user(userCopy).order(ordersCopy).build();
    }

    @After
    public void tearDown() throws Exception{
        name = nameCopy = null;
        contact = contactCopy = null;
        address = addressCopy = null;
        user = userCopy = null;
        item = itemCopy = null;
        items = itemsCopy = null;
        order = orderCopy = null;
        orders = ordersCopy = null;
        customer = customerCopy = null;
        nameFactory = null;
        contactFactory = null;
        addressFactory = null;
        userFactory = null;
        itemFactory = null;
        ordersFactory = null;
        customerFactory = null;
    }

    @Test
    public void testCreate()throws Exception{
        //testing name
        assertEquals("Braedy", customer.getName().getName());
        assertEquals("Elwyn", customer.getName().getMiddleName());
        assertEquals("Thebus", customer.getName().getSurname());

        //testing contact
        assertEquals("bthebus2@gmail.com", customer.getContactInformation().getEmail());
        assertEquals("0213456789", customer.getContactInformation().getTelephone());
        assertEquals("0831234567", customer.getContactInformation().getCellPhone());

        //testing address
        assertEquals("7", customer.getAddress().getHomeNumber());
        assertEquals("Line Road", customer.getAddress().getStreetName());
        assertEquals("cape town", customer.getAddress().getCity());
        assertEquals("western cape", customer.getAddress().getProvince());
        assertEquals("7732", customer.getAddress().getPostalCode());

        //testing user info
        assertEquals("Braedy123", customer.getUser().getUsername());
        assertEquals("A1B2C3", customer.getUser().getPassword());

        //testing orders
        assertEquals(date.toString(),customer.getOrders().get(0).getOrderDate());

        //testing item within orders
        assertEquals("Something", customer.getOrders().get(0).getItem().get(0).getName());
        assertEquals("This is something", customer.getOrders().get(0).getItem().get(0).getDescription());
        assertEquals("/images/image1.jpg", customer.getOrders().get(0).getItem().get(0).getImageLocation());
        assertEquals(19.99, customer.getOrders().get(0).getItem().get(0).getPrice(), .0);
        assertEquals(200, customer.getOrders().get(0).getItem().get(0).getQuantity());
    }

    @Test
    public void testUpdate() throws Exception
    {
        //testing name
        assertEquals("Jeff", customerCopy.getName().getName());
        assertEquals("Elwyn", customerCopy.getName().getMiddleName());
        assertEquals("Thebus", customerCopy.getName().getSurname());

        //testing contact
        assertEquals("213039168@mycput.ac.za", customerCopy.getContactInformation().getEmail());
        assertEquals("0213456789", customerCopy.getContactInformation().getTelephone());
        assertEquals("0831234567", customerCopy.getContactInformation().getCellPhone());

        //testing address
        assertEquals("45", customerCopy.getAddress().getHomeNumber());
        assertEquals("Qwerty Street", customerCopy.getAddress().getStreetName());
        assertEquals("Joburg", customerCopy.getAddress().getCity());
        assertEquals("Gauteng", customerCopy.getAddress().getProvince());
        assertEquals("8896", customerCopy.getAddress().getPostalCode());

        //testing user info
        assertEquals("Braedy123", customerCopy.getUser().getUsername());
        assertEquals("A1b2C3D4", customerCopy.getUser().getPassword());

        //testing orders
        assertEquals(date.toString(),customerCopy.getOrders().get(0).getOrderDate());

        //testing item within orders
        assertEquals("Something else", customerCopy.getOrders().get(0).getItem().get(0).getName());
        assertEquals("this is something else", customerCopy.getOrders().get(0).getItem().get(0).getDescription());
        assertEquals("/images/image1.jpg", customerCopy.getOrders().get(0).getItem().get(0).getImageLocation());
        assertEquals(59.99, customerCopy.getOrders().get(0).getItem().get(0).getPrice(), .0);
        assertEquals(150, customerCopy.getOrders().get(0).getItem().get(0).getQuantity());
    }
}
