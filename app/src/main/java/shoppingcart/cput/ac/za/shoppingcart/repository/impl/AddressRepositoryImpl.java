package shoppingcart.cput.ac.za.shoppingcart.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import shoppingcart.cput.ac.za.shoppingcart.conf.databases.DBConstants;
import shoppingcart.cput.ac.za.shoppingcart.domain.Address;
import shoppingcart.cput.ac.za.shoppingcart.repository.AddressRepository;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-24
 */
public class AddressRepositoryImpl extends SQLiteOpenHelper implements AddressRepository{

    public static final String TABLE_NAME = "address";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_HOMENUMBER = "homeNumber";
    public static final String COLUMN_STREETNAME = "streetName";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_PROVINCE = "province";
    public static final String COLUMN_POSTALCODE = "postalCode";

    //Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE"
            + TABLE_NAME + "("
            + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_HOMENUMBER + "TEXT NOT NULL, "
            + COLUMN_STREETNAME + "TEXT NOT NULL, "
            + COLUMN_CITY + "TEXT NOT NULL, "
            + COLUMN_PROVINCE + "TEXT NOT NULL, "
            + COLUMN_POSTALCODE + "TEXT NOT NULL );";

    public AddressRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }


    @Override
    public Address findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_HOMENUMBER,
                        COLUMN_STREETNAME,
                        COLUMN_CITY,
                        COLUMN_PROVINCE,
                        COLUMN_POSTALCODE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if ( cursor.moveToFirst())
        {
            final Address address = new Address.Builder().Id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .homeNumber(cursor.getString(cursor.getColumnIndex(COLUMN_HOMENUMBER)))
                    .streetName(cursor.getString(cursor.getColumnIndex(COLUMN_STREETNAME)))
                    .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                    .province(cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCE)))
                    .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTALCODE)))
                    .build();
            return address;
        }
        else {
            return null;
        }
    }

    @Override
    public Address save(Address entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_HOMENUMBER, entity.getHomeNumber());
        values.put(COLUMN_STREETNAME, entity.getStreetName());
        values.put(COLUMN_CITY, entity.getCity());
        values.put(COLUMN_PROVINCE, entity.getProvince());
        values.put(COLUMN_POSTALCODE, entity.getPostalCode());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Address insertedEntity = new Address.Builder()
                .copy(entity)
                .Id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Address update(Address entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_HOMENUMBER, entity.getHomeNumber());
        values.put(COLUMN_STREETNAME, entity.getStreetName());
        values.put(COLUMN_CITY, entity.getCity());
        values.put(COLUMN_PROVINCE, entity.getProvince());
        values.put(COLUMN_POSTALCODE, entity.getPostalCode());
        db.update(
                TABLE_NAME, values, COLUMN_ID + " = ? ", new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Address delete(Address entity) {
        open();
        db.delete(
                TABLE_NAME, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Set<Address> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Address> addresses = new HashSet<>();

        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst())
        {
            do{
                final Address address = new Address.Builder().Id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .homeNumber(cursor.getString(cursor.getColumnIndex(COLUMN_HOMENUMBER)))
                        .streetName(cursor.getString(cursor.getColumnIndex(COLUMN_STREETNAME)))
                        .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                        .province(cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCE)))
                        .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTALCODE)))
                        .build();
                addresses.add(address);
            }
            while(cursor.moveToNext());
        }
        return addresses;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME, null, null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(), "Upgrading database from version "+oldVersion+" to "+newVersion+", which will destroy all old data. ");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
