package shoppingcart.cput.ac.za.shoppingcart.TestFactories;

import junit.framework.TestCase;

import org.junit.*;

import shoppingcart.cput.ac.za.shoppingcart.domain.*;
import shoppingcart.cput.ac.za.shoppingcart.factories.ItemFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.ItemFactoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class TestItemFactory extends TestCase{
    private Item item, itemCopy;
    private ItemFactory itemFactory;
    @Before
    public void setUp(){
        itemFactory = ItemFactoryImpl.getInstance();
        item = itemFactory.createItem("Toast", "images/image4.jpg", "A piece of toast", 5.99, 300);
        itemCopy = new Item.Builder().copy(item).name("Dark toast").imageLocation("images/image4.jpg").description("a dark piece of toast").price(2.99).quantity(1).build();
    }

    @After
    public void tearDown(){
        item = itemCopy = null;
        itemFactory= null;
    }

    @Test
    public void testCreate()throws Exception{
        assertEquals("Toast", item.getName());
        assertEquals("images/image4.jpg", item.getImageLocation());
        assertEquals("A piece of toast", item.getDescription());
        assertEquals(5.99, item.getPrice(), .0);
        assertEquals(300, item.getQuantity());
    }

    @Test
    public void testUpdate() throws Exception
    {
        assertEquals("Dark toast", itemCopy.getName());
        assertEquals("images/image4.jpg", itemCopy.getImageLocation());
        assertEquals("a dark piece of toast", itemCopy.getDescription());
        assertEquals(2.99, itemCopy.getPrice(), .0);
        assertEquals(1, itemCopy.getQuantity());
    }
}
