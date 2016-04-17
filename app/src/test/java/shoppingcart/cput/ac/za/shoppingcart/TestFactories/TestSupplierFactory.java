package shoppingcart.cput.ac.za.shoppingcart.TestFactories;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import shoppingcart.cput.ac.za.shoppingcart.domain.Item;
import shoppingcart.cput.ac.za.shoppingcart.domain.Supplier;
import shoppingcart.cput.ac.za.shoppingcart.factories.ItemFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.SupplierFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.ItemFactoryImpl;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.SupplierFactoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class TestSupplierFactory extends TestCase{
    private Supplier supplier, supplierCopy;
    private Item item, itemCopy;
    private List<Item> items, itemsCopy;
    private ItemFactory itemFactory;
    private SupplierFactory supplierFactory;

    @Before
    public void setUp(){
        itemFactory = ItemFactoryImpl.getInstance();
        supplierFactory = SupplierFactoryImpl.getInstance();

        item = itemFactory.createItem("Toast", "images/image4.jpg", "A piece of toast", 5.99, 300);
        itemCopy = new Item.Builder().copy(item).name("Dark toast").imageLocation("images/image4.jpg").description("a dark piece of toast").price(2.99).quantity(1).build();
        items = new ArrayList<Item>();
        itemsCopy = new ArrayList<Item>();

        items.add(item);
        itemsCopy.add(itemCopy);
        supplier = supplierFactory.createSupplier("The Bread guys inc.", items);
        supplierCopy = new Supplier.Builder().copy(supplier).supplierName("The bread dudes inc").item(itemsCopy).build();
    }

    @After
    public void tearDown() throws Exception{
        item = itemCopy = null;
        items = itemsCopy = null;
        supplier = supplierCopy = null;
    }

    @Test
    public void testCreate()throws Exception{
        assertEquals("The Bread guys inc.", supplier.getSupplierName());
        assertEquals("Toast", supplier.getItem().get(0).getName());
        assertEquals("images/image4.jpg", supplier.getItem().get(0).getImageLocation());
        assertEquals("A piece of toast", supplier.getItem().get(0).getDescription());
        assertEquals(5.99, supplier.getItem().get(0).getPrice(), .0);
        assertEquals(300, supplier.getItem().get(0).getQuantity());

    }

    @Test
    public void testUpdate() throws Exception
    {
        assertEquals("The bread dudes inc", supplierCopy.getSupplierName());
        assertEquals("Dark toast", supplierCopy.getItem().get(0).getName());
        assertEquals("images/image4.jpg", supplierCopy.getItem().get(0).getImageLocation());
        assertEquals("a dark piece of toast", supplierCopy.getItem().get(0).getDescription());
        assertEquals(2.99, supplierCopy.getItem().get(0).getPrice(), .0);
        assertEquals(1, supplierCopy.getItem().get(0).getQuantity());
    }
}
