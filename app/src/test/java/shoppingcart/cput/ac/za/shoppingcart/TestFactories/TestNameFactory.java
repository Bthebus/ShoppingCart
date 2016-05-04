package shoppingcart.cput.ac.za.shoppingcart.TestFactories;

import junit.framework.TestCase;

import org.junit.*;

import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Name;
import shoppingcart.cput.ac.za.shoppingcart.factories.NameFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.NameFactoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class TestNameFactory extends TestCase{
    private Name name, nameCopy;
    private NameFactory nameFactory;
    @Before
    public void setUp() throws Exception{
        nameFactory = NameFactoryImpl.getInstance();
        name = nameFactory.createName("Braedy", "Elwyn", "Thebus");
        nameCopy = new Name.Builder().copy(name).name("Bob").middleName("Elwyn").surname("Thebus").build();
    }

    @After
    public void tearDown()throws Exception{
        name = null;
        nameCopy = null;
        nameFactory = null;
    }

    @Test
    public void testCreate() throws Exception
    {
        assertEquals("Braedy", name.getName());
        assertEquals("Elwyn", name.getMiddleName());
        assertEquals("Thebus", name.getSurname());
    }

    @Test
    public void testUpdate() throws Exception{
        assertEquals("Bob", nameCopy.getName());
        assertEquals("Elwyn", nameCopy.getMiddleName());
        assertEquals("Thebus", nameCopy.getSurname());
    }
}
