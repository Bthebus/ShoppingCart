package shoppingcart.cput.ac.za.shoppingcart.repository;

import android.test.AndroidTestCase;

import java.util.Set;

import shoppingcart.cput.ac.za.shoppingcart.domain.Contact;
import shoppingcart.cput.ac.za.shoppingcart.repository.impl.ContactRepositoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email $       : bthebus2@gmail.com
 * Date created : 2016-04-24
 */
public class ContactRepositoryTest extends AndroidTestCase{

    private static final String TAG = "CONTACT TEST";
    Long id;
    private ContactRepository repository;

    @Override
    public void setUp() throws Exception
    {
        repository = new ContactRepositoryImpl(this.getContext());
    }

    @Override
    public void tearDown() throws Exception{
        repository = null;
    }

    public void testCRUD() throws Exception
    {
        //CREATE
        Contact createEntity = new Contact.Builder().email("bthebus2@gmail.com").telephone("0211234567").cellphone("0841234567").build();
        Contact insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Contact> contacts = repository.findAll();
        assertTrue(TAG+ " READ ALL", contacts.size()>0);

        //READ ENTITY
        Contact entity = repository.findById(id);
        assertNotNull(TAG + " READ ENTITY", entity);

        //UPDATE ENTITY
        Contact updateEntity = new Contact.Builder().copy(entity).email("bthebus@gmail.com").build();
        repository.update(updateEntity);
        Contact newEntity = repository.findById(id);
        assertEquals(TAG+" UPDATE ENTTIY", "bthebus@gmail.com", newEntity.getEmail());

        //DELETE ENTITY
        repository.delete(updateEntity);
        Contact deletedEntity = repository.findById(id);
        assertNull(TAG + " DELETE", deletedEntity);
    }
}
