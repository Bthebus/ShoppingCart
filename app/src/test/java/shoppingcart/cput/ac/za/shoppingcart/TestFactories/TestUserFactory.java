package shoppingcart.cput.ac.za.shoppingcart.TestFactories;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shoppingcart.cput.ac.za.shoppingcart.domain.User;
import shoppingcart.cput.ac.za.shoppingcart.factories.UserFactory;
import shoppingcart.cput.ac.za.shoppingcart.factories.impl.UserFactoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-17
 */
public class TestUserFactory extends TestCase {
    private User user, userCopy;
    private UserFactory userFactory;

    @Before
    public void setUp(){
        userFactory = UserFactoryImpl.getInstance();
        user = userFactory.createUser("Tom12", "tom12A!");
        userCopy = new User.Builder().copy(user).username("Tommy12").password("7p8l5k!").build();
    }

    @After
    public void tearDown(){
        user = userCopy = null;
    }

    @Test
    public void testCreate()throws Exception{
        assertEquals("Tom12", user.getUsername());
        assertEquals("tom12A!", user.getPassword());
    }

    @Test
    public void testUpdate() throws Exception
    {
        assertEquals("Tommy12", userCopy.getUsername());
        assertEquals("7p8l5k!", userCopy.getPassword());
    }
}
