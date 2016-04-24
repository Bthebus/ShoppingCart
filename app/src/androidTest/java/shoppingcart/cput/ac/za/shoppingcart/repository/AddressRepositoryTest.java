package shoppingcart.cput.ac.za.shoppingcart.repository;

import android.test.AndroidTestCase;

import java.util.Set;

import shoppingcart.cput.ac.za.shoppingcart.domain.Address;
import shoppingcart.cput.ac.za.shoppingcart.repository.impl.AddressRepositoryImpl;


/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-24
 */
public class AddressRepositoryTest extends AndroidTestCase {
    private static final String TAG = "ADDRESS TEST";
    private Long id;
    private AddressRepository repository;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        repository = new AddressRepositoryImpl(this.getContext());
    }

    public void testCreateReadUpdateDelete() throws Exception {

        //CREATE
        Address createEntity = new Address.Builder().homeNumber("7").streetName("Hoek street").city("Cape town").province("Western Cape").postalCode("7780").build();
        Address insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Address> addresses = repository.findAll();
        assertTrue(TAG + " READ ALL", addresses.size() > 0);

        //READ ENTITY
        Address entity = repository.findById(id);
        assertNotNull(TAG + " READ ENTITY", entity);

        //UPDATE ENTITY
        Address updateEntity = new Address.Builder().copy(entity).homeNumber("15").streetName("Joaane street").build();
        repository.update(updateEntity);
        Address newEntity = repository.findById(id);
        assertEquals(TAG + " UPDATE ENTITY ", "15", newEntity.getHomeNumber());
        assertEquals(TAG+" UPDATE ENTITY", "Joaane street", newEntity.getStreetName());

        //DELETE ENTITY
        repository.delete(updateEntity);
        Address deletedEntity = repository.findById(id);
        assertNull(TAG + " DELETE", deletedEntity);
    }

}
