package shoppingcart.cput.ac.za.shoppingcart.repository.impl;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Set;

import shoppingcart.cput.ac.za.shoppingcart.conf.databases.DBConstants;
import shoppingcart.cput.ac.za.shoppingcart.domain.Customer;
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

    //NAME CONSTANTS
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ = "";
    public static final String COLUMN_ = "";
    public static final String COLUMN_ = "";
    public static final String COLUMN_ = "";
    public static final String COLUMN_ = "";
    public static final String COLUMN_ = "";
    public static final String COLUMN_ = "";
    public static final String COLUMN_ = "";
    public static final String COLUMN_ = "";
    public static final String COLUMN_ = "";

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
    public Customer findById(Long aLong) {
        return null;
    }

    @Override
    public Customer save(Customer entity) {
        return null;
    }

    @Override
    public Customer update(Customer entity) {
        return null;
    }

    @Override
    public Customer delete(Customer entity) {
        return null;
    }

    @Override
    public Set<Customer> findAll() {
        return null;
    }

    @Override
    public int deleteAll() {
        return 0;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
