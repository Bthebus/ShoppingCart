package shoppingcart.cput.ac.za.shoppingcart.TestFactories;

import junit.framework.TestCase;

import org.junit.*;

import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Contact;
import shoppingcart.cput.ac.za.shoppingcart.factories.ContactFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.ContactFactoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class TestContactFactory extends TestCase{
    private ContactFactory factory;
    private Contact contact, contactCopy;

    @Before
    public void setUp(){
        factory = ContactFactoryImpl.getInstance();
        contact = factory.createContact("bthebus2@gmail.com","0213456789", "0831234567");
        contactCopy = new Contact.Builder().copy(contact).email("213039168@mycput.ac.za").telephone("0213135555").cellphone("0823123132").build();
    }

    @After
    public void tearDown() throws Exception{
        factory = null;
        contact = null;
        contactCopy = null;
    }

    @Test
    public void testCreate()throws Exception{
        assertEquals("bthebus2@gmail.com", contact.getEmail());
        assertEquals("0213456789", contact.getTelephone());
        assertEquals("0831234567", contact.getCellPhone());
    }

    @Test
    public void testUpdate() throws Exception
    {
        assertEquals("213039168@mycput.ac.za", contactCopy.getEmail());
        assertEquals("0213135555", contactCopy.getTelephone());
        assertEquals("0823123132", contactCopy.getCellPhone());
    }
}
