package shoppingcart.cput.ac.za.shoppingcart.TestFactories;

import junit.framework.TestCase;

import org.junit.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import shoppingcart.cput.ac.za.shoppingcart.domain.Item;
import shoppingcart.cput.ac.za.shoppingcart.domain.Orders;
import shoppingcart.cput.ac.za.shoppingcart.domain.Sale;
import shoppingcart.cput.ac.za.shoppingcart.factories.ItemFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.OrdersFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.SaleFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.ItemFactoryImpl;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.OrdersFactoryImpl;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.SaleFactoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class TestSaleFactory extends TestCase{
    private Item item, itemCopy;
    private Orders order, orderCopy;
    private List<Item> items, itemsCopy;
    private Date date;
    private List<Orders> orders, ordersCopy;
    private Sale sale, saleCopy;
    private ItemFactory itemFactory;
    private OrdersFactory ordersFactory;
    private SaleFactory saleFactory;

    @Before
    public void setUp(){
        itemFactory = ItemFactoryImpl.getInstance();
        ordersFactory = OrdersFactoryImpl.getInstance();
        saleFactory = SaleFactoryImpl.getInstance();

        item = itemFactory.createItem("Sausage", "images/image5.jpg", "this is sausage", 30.50, 1000);
        itemCopy = new Item.Builder().copy(item).name("Sausage").imageLocation("images/image5.jpg").description("this is sausage").price(20.99).quantity(500).build();

        items = new ArrayList<Item>();
        itemsCopy = new ArrayList<Item>();

        items.add(item);
        itemsCopy.add(itemCopy);

        date = new Date();

        order = ordersFactory.createOrders(date.toString(), items);
        orderCopy = new Orders.Builder().copy(order).orderDate(date.toString()).item(itemsCopy).build();

        orders = new ArrayList<Orders>();
        ordersCopy = new ArrayList<Orders>();

        orders.add(order);
        ordersCopy.add(orderCopy);

        sale = saleFactory.createSale(orders);
        saleCopy = new Sale.Builder().copy(sale).order(ordersCopy).build();
    }


    @After
    public void tearDown(){
        item = itemCopy = null;
        items = itemsCopy = null;
        date = null;
        order = orderCopy = null;
        sale = saleCopy = null;
        itemFactory = null;
        ordersFactory = null;
        saleFactory = null;
    }

    @Test
    public void testCreate()throws Exception{
        //testing date
        assertEquals(date.toString(), sale.getOrders().get(0).getOrderDate());

        //testing item
        assertEquals("Sausage", sale.getOrders().get(0).getItem().get(0).getName());
        assertEquals("images/image5.jpg", sale.getOrders().get(0).getItem().get(0).getImageLocation());
        assertEquals("this is sausage", sale.getOrders().get(0).getItem().get(0).getDescription());
        assertEquals(30.50, sale.getOrders().get(0).getItem().get(0).getPrice(), .0);
        assertEquals(1000, sale.getOrders().get(0).getItem().get(0).getQuantity());
    }

    @Test
    public void testUpdate() throws Exception
    {
        //testing date
        assertEquals(date.toString(), saleCopy.getOrders().get(0).getOrderDate());

        //testing item
        assertEquals("Sausage", saleCopy.getOrders().get(0).getItem().get(0).getName());
        assertEquals("images/image5.jpg", saleCopy.getOrders().get(0).getItem().get(0).getImageLocation());
        assertEquals("this is sausage", saleCopy.getOrders().get(0).getItem().get(0).getDescription());
        assertEquals(20.99, saleCopy.getOrders().get(0).getItem().get(0).getPrice(), .0);
        assertEquals(500, saleCopy.getOrders().get(0).getItem().get(0).getQuantity());
    }
}
