package shoppingcart.cput.ac.za.shoppingcart.TestFactories;

import junit.framework.TestCase;

import org.junit.*;

import shoppingcart.cput.ac.za.shoppingcart.domain.Address;
import shoppingcart.cput.ac.za.shoppingcart.factories.AddressFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.AddressFactoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class TestAddressFactory extends TestCase{
    private AddressFactory factory;
    private Address address, addressCopy;

    @Before
    public void setUp(){
        factory = AddressFactoryImpl.getInstance();
        address = factory.createAddress("7", "Line Road", "cape town", "western cape", "7732");
        addressCopy = new Address.Builder().copy(address).homeNumber("3").streetName("Hoek").city("Joburg").province("Gauteng").postalCode("9785").build();
    }
    @After
    public void tearDown(){
        factory = null;
        address = null;
        addressCopy = null;
    }

    @Test
    public void testCreate()throws Exception{
        assertEquals("7", address.getHomeNumber());
        assertEquals("Line Road", address.getStreetName());
        assertEquals("cape town", address.getCity());
        assertEquals("western cape", address.getProvince());
        assertEquals("7732", address.getPostalCode());
    }

    @Test
    public void testUpdate() throws Exception
    {
        assertEquals("3", addressCopy.getHomeNumber());
        assertEquals("Hoek", addressCopy.getStreetName());
        assertEquals("Joburg", addressCopy.getCity());
        assertEquals("Gauteng", addressCopy.getProvince());
        assertEquals("9785", addressCopy.getPostalCode());
    }
}
