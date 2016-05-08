package shoppingcart.cput.ac.za.shoppingcart.repository;

import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import shoppingcart.cput.ac.za.shoppingcart.domain.Item;
import shoppingcart.cput.ac.za.shoppingcart.domain.Supplier;
import shoppingcart.cput.ac.za.shoppingcart.repository.impl.SupplierRepositoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-07
 */
public class SupplierRepositoryTest extends AndroidTestCase {

    private static final String TAG = "SUPPLIER TEST";
    private Long id;
    private SupplierRepository repository;
    private Item item;
    private Supplier createEntity;

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        repository = new SupplierRepositoryImpl(this.getContext());

        List<Item> items = new ArrayList<>();
        items.add(item);

        createEntity = new Supplier.Builder()
                .supplierName("TheChineseShop")
                .item(items)
                .build();
    }

    @Override
    public void tearDown()throws Exception{
        repository = null;
        item = null;
        createEntity = null;
    }

    public void testCRUD()throws Exception{
        //CREATE
        Supplier insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        assertNotNull(TAG + " CREATE ", insertedEntity);

        //READ ALL
        Set<Supplier> suppliers = repository.findAll();
        assertTrue(TAG + " READ ENTITY ", suppliers.size()>0);

        //READ ENTITY
        Supplier entity = repository.findById(id);
        System.out.println("SUPPLIER ID: "+id);
        assertNotNull(TAG+ " READ ENTITY ", entity);

        //UPDATE ENTITY
        Supplier updateEntity = new Supplier.Builder()
                .copy(entity)
                .id(id)
                .supplierName("FreeStuff")
                .build();
        repository.update(updateEntity);
        Supplier newEntity = repository.findById(id);
        assertEquals(TAG+ " UPDATE ENTITY ", "FreeStuff", newEntity.getSupplierName());

        //DELETE ENTITY
        repository.delete(updateEntity);
        Supplier deletedEntity = repository.findById(id);
        assertNull(TAG + " DELETE ", deletedEntity);
    }
}
