package shoppingcart.cput.ac.za.shoppingcart.repository;

import android.test.AndroidTestCase;

import java.util.Set;

import shoppingcart.cput.ac.za.shoppingcart.domain.Employee;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Name;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.User;
import shoppingcart.cput.ac.za.shoppingcart.repository.impl.EmployeeRepositoryImpl;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-07
 */
public class EmployeeRepositoryTest extends AndroidTestCase{
    private static final String TAG = "EMPLOYEE TEST";
    private Long id;
    private EmployeeRepository repository;
    private Name name;
    private User user;
     Employee createEntity;

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        repository = new EmployeeRepositoryImpl(this.getContext());
       // nameRepository = new NameRepositoryImpl(this.getContext());
        name = new Name.Builder()
                .name("Braedy")
                .middleName("Middle")
                .surname("Thebus")
                .build();

        user = new User.Builder()
                .username("Bob123")
                .password("123Q!1As")
                .build();

        createEntity = new Employee.Builder()
                .name(name)
                .user(user)
                .build();
    }

    @Override
    public void tearDown() throws Exception{
        repository = null;
        name = null;
        user = null;
        createEntity = null;
    }

    public void testCRUD() throws Exception{
        //CREATE
        Employee insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        assertNotNull(TAG + " CREATE ", insertedEntity);

        //READ ALL
        Set<Employee> employees = repository.findAll();
        assertTrue(TAG + " READ ALL ", employees.size()>0);


        //READ ENTITY
        Employee entity = repository.findById(id);
        assertNotNull(TAG + " READ ENTITY ", entity);


        //UPDATE ENTITY
        Name updatedName = new Name.Builder()
                .copy(name)
                .name("Bobby")
                .build();
        Employee updateEntity = new Employee.Builder()
        .copy(entity)
        .id(id)
        .name(updatedName)
        .build();

        repository.update(updateEntity);
        Employee newEntity = repository.findById(id);
        System.out.println("NEW ENTITY: " + newEntity.getName().getName());
        assertEquals(TAG + " UPDATE ENTITY ", "Bobby", newEntity.getName().getName());

        //DELETE ENTITY
        repository.delete(updateEntity);
        Employee deletedEntity = repository.findById(id);
        AndroidTestCase.assertNull(TAG + " DELETE ", deletedEntity);

    }
}
