package shoppingcart.cput.ac.za.shoppingcart.repository;

import android.test.AndroidTestCase;

import java.util.Set;

import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Name;
import shoppingcart.cput.ac.za.shoppingcart.repository.impl.NameRepositoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-24
 */
public class NameRepositoryTest  extends AndroidTestCase{

    private static final String TAG = "NAME TEST";
    private NameRepository repository;
    private Long id;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        repository = new NameRepositoryImpl(this.getContext());
    }

    @Override
    public void tearDown()throws Exception
    {
        repository = null;
    }

    public void testCRUD() throws Exception
    {
        //CREATE
        Name createEntity = new Name.Builder()
                .name("Braedy")
                .middleName("Elwyn")
                .surname("Thebus")
                .build();

        Name insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        assertNotNull(TAG+ " CREATE", insertedEntity);


        //READ ALL
        Set<Name> names = repository.findAll();
        assertTrue(TAG + " READ ALL", names.size() > 0);

        //READ ENTITY
        Name entity = repository.findById(id);
        assertNotNull(TAG + " READ ENTITY", entity);

        //UPDATE ENTITY
        Name updateEntity = new Name.Builder().copy(entity).name("Mark").build();
        repository.update(updateEntity);
        Name newEntity = repository.findById(id);
        assertEquals(TAG + " UPDATE ENTITY ", "Mark", newEntity.getName());

        //DELETE ENTITY
        repository.delete(updateEntity);
        Name deletedEntity = repository.findById(id);
        assertNull(TAG + " DELETE", deletedEntity);

    }

}
