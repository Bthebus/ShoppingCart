package shoppingcart.cput.ac.za.shoppingcart.repository;

import android.test.AndroidTestCase;

import java.util.Set;

import shoppingcart.cput.ac.za.shoppingcart.domain.Item;
import shoppingcart.cput.ac.za.shoppingcart.repository.impl.ItemRepositoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-25
 */
public class ItemRepositoryTest extends AndroidTestCase{
    private static final String TAG = "ITEM TEST";
    private Long id;
    private ItemRepository repository;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        repository = new ItemRepositoryImpl(this.getContext());
    }

    @Override
    public void tearDown()throws Exception
    {
        repository = null;
    }

    public void testCRUD() throws Exception {

        //CREATE
        Item createEntity = new Item.Builder().name("Fly shoes").imageLocation("/images/image1.jpg").description("these are shoes").price(21.00).quantity(10).build();
        Item insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Item> items = repository.findAll();
        assertTrue(TAG + " READ ALL", items.size() > 0);

        //READ ENTITY
        Item entity = repository.findById(id);
        assertNotNull(TAG + " READ ENTITY", entity);

        //UPDATE ENTITY
        Item updateEntity = new Item.Builder().copy(entity).name("Dope shoes").build();
        repository.update(updateEntity);
        Item newEntity = repository.findById(id);
        assertEquals(TAG + " UPDATE ENTITY ", "Dope shoes", newEntity.getName());

        //DELETE ENTITY
        repository.delete(updateEntity);
        Item deletedEntity = repository.findById(id);
        assertNull(TAG + " DELETE", deletedEntity);
    }
}
