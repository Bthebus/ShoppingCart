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
import shoppingcart.cput.ac.za.shoppingcart.domain.Item;
import shoppingcart.cput.ac.za.shoppingcart.domain.Supplier;
import shoppingcart.cput.ac.za.shoppingcart.repository.SupplierRepository;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-07
 */
public class SupplierRepositoryImpl extends SQLiteOpenHelper implements SupplierRepository{

    public static final String TABLE_NAME = "supplier";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SUPPLIER_NAME = "supplierName";
    public static final String COLUMN_ITEMS_LIST = "items";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
            + COLUMN_ITEMS_LIST + " TEXT NOT NULL);";



    public SupplierRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Supplier findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_SUPPLIER_NAME,
                        COLUMN_ITEMS_LIST
                }, COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null, null
        );
        if(cursor.moveToFirst())
        {
            do{
                List<Item> items = new ArrayList<Item>();
                items.add(null);
                Supplier supplier = new Supplier.Builder()
                        .supplierName("TheChineseShop")
                        .item(items)
                        .build();

                return supplier;
            }while(cursor.moveToNext());
        }
        else
        {
            return null;
        }
    }

    @Override
    public Supplier save(Supplier entity) {
        open();
        ContentValues values  = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SUPPLIER_NAME, entity.getSupplierName());
        values.put(COLUMN_ITEMS_LIST, entity.getItem().toArray().toString());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Supplier insertedEntity = new Supplier.Builder()
                .copy(entity)
                .build();
        return  insertedEntity;
    }

    @Override
    public Supplier update(Supplier entity) {
        open();
        ContentValues values  = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SUPPLIER_NAME, entity.getSupplierName());
        values.put(COLUMN_ITEMS_LIST, entity.getItem().toArray().toString());

        db.update(
                TABLE_NAME, values, COLUMN_ID + " = ? ", new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Supplier delete(Supplier entity) {
        open();
        db.delete(
                TABLE_NAME, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Set<Supplier> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Supplier> suppliers = new HashSet<>();

        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do{
                List<Item> items = new ArrayList<Item>();
                items.add(null);
                Supplier supplier = new Supplier.Builder()
                        .supplierName("TheChineseShop")
                        .item(items)
                        .build();
                suppliers.add(supplier);
            }while(cursor.moveToNext());
        }

        return suppliers;
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
        Log.w(this.getClass().getName(), "Upgrading database from version "+oldVersion+" to "+newVersion+", which will destroy all old data. ");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
