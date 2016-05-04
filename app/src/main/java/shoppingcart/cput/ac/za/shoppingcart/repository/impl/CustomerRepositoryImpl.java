package shoppingcart.cput.ac.za.shoppingcart.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import shoppingcart.cput.ac.za.shoppingcart.conf.databases.DBConstants;
import shoppingcart.cput.ac.za.shoppingcart.conf.util.AppUtil;
import shoppingcart.cput.ac.za.shoppingcart.domain.Customer;
import shoppingcart.cput.ac.za.shoppingcart.domain.Item;
import shoppingcart.cput.ac.za.shoppingcart.domain.Orders;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Address;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Contact;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Name;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.User;
import shoppingcart.cput.ac.za.shoppingcart.repository.CustomerRepository;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-04
 */
public class CustomerRepositoryImpl extends SQLiteOpenHelper implements CustomerRepository{

    public static final String TABLE_NAME = "customer";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";

    //ORDER CONSTANTS
    public static final String COLUMN_ORDERS = "orders";
    public static final String COLUMN_DATE = "date";

    //ITEM CONSTANTS
    public static final String COLUMN_ITEM_NAME = "item_name";
    public static final String COLUMN_IMAGELOCATION = "imageLocation";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_QUANTITY = "quantity";

    //NAME CONSTANTS
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MIDDLENAME = "middleName";
    public static final String COLUMN_SURNAME = "surname";

    //CONTACT CONSTANTS
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_TELEPHONE = "telephone";
    public static final String COLUMN_CELLPHONE = "cellphone";

    //ADDRESS CONSTANTS
    public static final String COLUMN_HOMENUMBER = "homeNumber";
    public static final String COLUMN_STREETNAME = "streetName";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_PROVINCE = "province";
    public static final String COLUMN_POSTALCODE = "postalcode";

    //USER CONSTANTS
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD  = "password";



    //database create sql
    private static final String DATABASE_CREATE = " CREATE TABLE"
            +TABLE_NAME + "("
            +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_NAME + " TEXT NOT NULL, "
            +COLUMN_MIDDLENAME+ " TEXT, "
            +COLUMN_SURNAME + " TEXT NOT NULL "
            +COLUMN_EMAIL + " TEXT NOT NULL, "
            +COLUMN_TELEPHONE + " TEXT, "
            +COLUMN_CELLPHONE + " TEXT NOT NULL, "
            +COLUMN_HOMENUMBER + " TEXT NOT NULL, "
            +COLUMN_STREETNAME + " TEXT NOT NULL, "
            +COLUMN_CITY + " TEXT NOT NULL, "
            +COLUMN_PROVINCE + " TEXT NOT NULL, "
            +COLUMN_POSTALCODE + " TEXT NOT NULL, "
            +COLUMN_USERNAME + " TEXT NOT NULL UNIQUE, "
            +COLUMN_PASSWORD + " TEXT NOT NULL, "
            +COLUMN_ORDERS + " TEXT );";


    public CustomerRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Customer findById(Long id) {
       SQLiteDatabase db = this.getReadableDatabase();

       Cursor cursor = db.query(
               TABLE_NAME,
               new String[]{
                       COLUMN_ID,
                       COLUMN_NAME,
                       COLUMN_MIDDLENAME,
                       COLUMN_SURNAME,
                       COLUMN_EMAIL,
                       COLUMN_TELEPHONE,
                       COLUMN_CELLPHONE,
                       COLUMN_HOMENUMBER,
                       COLUMN_STREETNAME,
                       COLUMN_CITY,
                       COLUMN_PROVINCE,
                       COLUMN_POSTALCODE,
                       COLUMN_USERNAME,
                       COLUMN_PASSWORD,
                       COLUMN_ORDERS
               },
               COLUMN_ID + " =? ",
               new String[]{String.valueOf(id)},
               null,
               null,
               null,
               null
       );

        if(cursor.moveToFirst())
        {
            //Item object for item List for Orders object
            Item item = new Item.Builder()
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME)))
                    .imageLocation(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGELOCATION)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .price(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)))
                    .quantity(cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_NAME)))
                    .build();

            List<Item> items = new ArrayList<Item>();
            items.add(item);

            //Orders object for orders List
            Orders order = new Orders.Builder()
                    .orderDate(String.valueOf(AppUtil.date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))))
                    .item(items)
                    .build();

            List<Orders> orders = new ArrayList<Orders>();
            orders.add(order);

            //Name object
            Name name = new Name.Builder()
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .middleName(cursor.getString(cursor.getColumnIndex(COLUMN_MIDDLENAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .build();

            //Contact object
            Contact contact = new Contact.Builder()
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .telephone(cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE)))
                    .cellphone(cursor.getString(cursor.getColumnIndex(COLUMN_CELLPHONE)))
                    .build();

            //Address object
            Address address = new Address.Builder()
                    .homeNumber(cursor.getString(cursor.getColumnIndex(COLUMN_HOMENUMBER)))
                    .streetName(cursor.getString(cursor.getColumnIndex(COLUMN_STREETNAME)))
                    .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                    .province(cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCE)))
                    .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTALCODE)))
                    .build();
            //User object
            User user = new User.Builder()
                    .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                    .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                    .build();

            //Customer object
            final Customer customer = new Customer.Builder()
                    .name(name)
                    .contactInformation(contact)
                    .address(address)
                    .user(user)
                    .order(orders)
                    .build();

            return customer;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Customer save(Customer entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, entity.getName().getName());
        values.put(COLUMN_MIDDLENAME, entity.getName().getMiddleName());
        values.put(COLUMN_SURNAME, entity.getName().getSurname());

        values.put(COLUMN_EMAIL, entity.getContactInformation().getEmail());
        values.put(COLUMN_TELEPHONE, entity.getContactInformation().getTelephone());
        values.put(COLUMN_CELLPHONE, entity.getContactInformation().getCellPhone());

        values.put(COLUMN_HOMENUMBER, entity.getAddress().getHomeNumber());
        values.put(COLUMN_STREETNAME, entity.getAddress().getStreetName());
        values.put(COLUMN_CITY, entity.getAddress().getCity());
        values.put(COLUMN_PROVINCE, entity.getAddress().getProvince());
        values.put(COLUMN_POSTALCODE, entity.getAddress().getPostalCode());

        values.put(COLUMN_USERNAME, entity.getUser().getUsername());
        values.put(COLUMN_PASSWORD, entity.getUser().getPassword());

        values.put(COLUMN_ORDERS, entity.getOrders().toString());

        long id = db.insertOrThrow(TABLE_NAME, null, values);

        Customer insertedEntity = new Customer.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Customer update(Customer entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, entity.getName().getName());
        values.put(COLUMN_MIDDLENAME, entity.getName().getMiddleName());
        values.put(COLUMN_SURNAME, entity.getName().getSurname());

        values.put(COLUMN_EMAIL, entity.getContactInformation().getEmail());
        values.put(COLUMN_TELEPHONE, entity.getContactInformation().getTelephone());
        values.put(COLUMN_CELLPHONE, entity.getContactInformation().getCellPhone());

        values.put(COLUMN_HOMENUMBER, entity.getAddress().getHomeNumber());
        values.put(COLUMN_STREETNAME, entity.getAddress().getStreetName());
        values.put(COLUMN_CITY, entity.getAddress().getCity());
        values.put(COLUMN_PROVINCE, entity.getAddress().getProvince());
        values.put(COLUMN_POSTALCODE, entity.getAddress().getPostalCode());

        values.put(COLUMN_USERNAME, entity.getUser().getUsername());
        values.put(COLUMN_PASSWORD, entity.getUser().getPassword());

        values.put(COLUMN_ORDERS, entity.getOrders().toString());

        try
        {
            db.update(
                    TABLE_NAME,
                    values,
                    COLUMN_ID + " =? ",
                    new String[]{String.valueOf(entity.getId())}
            );
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Customer delete(Customer entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Set<Customer> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Customer> customers = new HashSet<>();

        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
         do {
             //Item object for item List for Orders object
             Item item = new Item.Builder()
                     .name(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME)))
                     .imageLocation(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGELOCATION)))
                     .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                     .price(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)))
                     .quantity(cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_NAME)))
                     .build();

             List<Item> items = new ArrayList<Item>();
             items.add(item);

             //Orders object for orders List
             Orders order = new Orders.Builder()
                     .orderDate(String.valueOf(AppUtil.date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))))
                     .item(items)
                     .build();

             List<Orders> orders = new ArrayList<Orders>();
             orders.add(order);

             //Name object
             Name name = new Name.Builder()
                     .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                     .middleName(cursor.getString(cursor.getColumnIndex(COLUMN_MIDDLENAME)))
                     .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                     .build();

             //Contact object
             Contact contact = new Contact.Builder()
                     .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                     .telephone(cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE)))
                     .cellphone(cursor.getString(cursor.getColumnIndex(COLUMN_CELLPHONE)))
                     .build();

             //Address object
             Address address = new Address.Builder()
                     .homeNumber(cursor.getString(cursor.getColumnIndex(COLUMN_HOMENUMBER)))
                     .streetName(cursor.getString(cursor.getColumnIndex(COLUMN_STREETNAME)))
                     .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                     .province(cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCE)))
                     .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTALCODE)))
                     .build();
             //User object
             User user = new User.Builder()
                     .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                     .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                     .build();

             //Customer object
             final Customer customer = new Customer.Builder()
                     .name(name)
                     .contactInformation(contact)
                     .address(address)
                     .user(user)
                     .order(orders)
                     .build();
             customers.add(customer);
         }while(cursor.moveToNext());
        }
        return customers;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME, null, null);
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
