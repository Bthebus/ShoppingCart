package shoppingcart.cput.ac.za.shoppingcart.TestFactories;

import junit.framework.TestCase;

import org.junit.*;

import java.util.*;

import shoppingcart.cput.ac.za.shoppingcart.domain.*;
import shoppingcart.cput.ac.za.shoppingcart.factories.*;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.*;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class TestOrderLineFactory extends TestCase{
    private Item item, itemCopy;
    private Orders order, orderCopy;
    private List<Item> items, itemsCopy;
    private Date date;
    private List<Orders> orders, ordersCopy;
    private OrderLine orderLine, orderLineCopy;
    private ItemFactory itemFactory;
    private OrdersFactory ordersFactory;
    private OrderLineFactory orderLineFactory;
    @Before
    public void setUp(){
        itemFactory = ItemFactoryImpl.getInstance();
        ordersFactory = OrdersFactoryImpl.getInstance();
        orderLineFactory = OrderLineFactoryImpl.getInstance();

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

        orderLine = orderLineFactory.createOrderLine(orders);
        orderLineCopy = new OrderLine.Builder().copy(orderLine).order(ordersCopy).build();
    }


    @After
    public void tearDown(){
        item = itemCopy = null;
        items = itemsCopy = null;
        date = null;
        order = orderCopy = null;
        orderLine = orderLineCopy = null;
        itemFactory = null;
        orderLineFactory = null;
        ordersFactory = null;
    }

    @Test
    public void testCreate()throws Exception{
        //testing date
        assertEquals(date.toString(), orderLine.getOrders().get(0).getOrderDate());

        //testing item
        assertEquals("Sausage", orderLine.getOrders().get(0).getItem().get(0).getName());
        assertEquals("images/image5.jpg", orderLine.getOrders().get(0).getItem().get(0).getImageLocation());
        assertEquals("this is sausage", orderLine.getOrders().get(0).getItem().get(0).getDescription());
        assertEquals(30.50, orderLine.getOrders().get(0).getItem().get(0).getPrice(), .0);
        assertEquals(1000, orderLine.getOrders().get(0).getItem().get(0).getQuantity());
    }

    @Test
    public void testUpdate() throws Exception
    {
        //testing date
        assertEquals(date.toString(), orderLineCopy.getOrders().get(0).getOrderDate());

        //testing item
        assertEquals("Sausage", orderLineCopy.getOrders().get(0).getItem().get(0).getName());
        assertEquals("images/image5.jpg", orderLineCopy.getOrders().get(0).getItem().get(0).getImageLocation());
        assertEquals("this is sausage", orderLineCopy.getOrders().get(0).getItem().get(0).getDescription());
        assertEquals(20.99, orderLineCopy.getOrders().get(0).getItem().get(0).getPrice(), .0);
        assertEquals(500, orderLineCopy.getOrders().get(0).getItem().get(0).getQuantity());
    }
}
