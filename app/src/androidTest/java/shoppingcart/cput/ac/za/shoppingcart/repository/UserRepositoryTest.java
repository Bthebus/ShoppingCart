package shoppingcart.cput.ac.za.shoppingcart.repository;

import android.test.AndroidTestCase;

import java.util.Set;

import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.User;
import shoppingcart.cput.ac.za.shoppingcart.repository.impl.UserRepositoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-25
 */
public class UserRepositoryTest extends AndroidTestCase {
    private static final String TAG = "USER TEST";
    private UserRepository repository;
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        repository = new UserRepositoryImpl(this.getContext());
    }

    @Override
    public void tearDown()throws Exception
    {
        repository = null;
    }

    public void testCRUD() throws Exception
    {
        //CREATE
        User createEntity = new User.Builder()
                .username("Braedy")
                .password("123abc!")
                .build();

        User insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        assertNotNull(TAG+ " CREATE", insertedEntity);


        //READ ALL
        Set<User> users = repository.findAll();
        assertTrue(TAG + " READ ALL", users.size() > 0);

        //READ ENTITY
        User entity = repository.findById(id);
        assertNotNull(TAG + " READ ENTITY", entity);

        //UPDATE ENTITY
        User updateEntity = new User.Builder().copy(entity).password("789xyz@").build();
        repository.update(updateEntity);
        User newEntity = repository.findById(id);
        assertEquals(TAG + " UPDATE ENTITY ", "789xyz@", newEntity.getPassword());

        //DELETE ENTITY
        repository.delete(updateEntity);
        User deletedEntity = repository.findById(id);
        assertNull(TAG + " DELETE", deletedEntity);

    }
}
