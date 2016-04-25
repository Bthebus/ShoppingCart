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
import shoppingcart.cput.ac.za.shoppingcart.domain.Item;
import shoppingcart.cput.ac.za.shoppingcart.repository.ItemRepository;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-24
 */
public class ItemRepositoryImpl extends SQLiteOpenHelper implements ItemRepository{

    public static final String TABLE_NAME = "item";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGELOCATION = "imageLocation";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_QUANTITY = "quantity";

    //database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_IMAGELOCATION + " TEXT NOT NULL, "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL, "
            + COLUMN_PRICE + " TEXT NOT NULL, "
            + COLUMN_QUANTITY + " TEXT NOT NULL );";

    public ItemRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Item findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_IMAGELOCATION,
                        COLUMN_DESCRIPTION,
                        COLUMN_PRICE,
                        COLUMN_QUANTITY},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if ( cursor.moveToFirst()) {
            final Item item = new Item.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .imageLocation(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGELOCATION)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .price(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)))
                    .quantity(cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)))
                    .build();
            return item;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Item save(Item entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_IMAGELOCATION, entity.getImageLocation());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_PRICE, entity.getPrice());
        values.put(COLUMN_QUANTITY, entity.getQuantity());

        long id  = db.insert(TABLE_NAME, null,values);
        Item insertedEntity = new Item.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return  insertedEntity;
    }

    @Override
    public Item update(Item entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_IMAGELOCATION, entity.getImageLocation());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_PRICE, entity.getPrice());
        values.put(COLUMN_QUANTITY, entity.getQuantity());

        db.update(
                TABLE_NAME, values, COLUMN_ID + " = ? ", new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Item delete(Item entity) {
        open();
        db.delete(
                TABLE_NAME, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Set<Item> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Item> items = new HashSet<>();

        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst())
        {
            do{
                Item item = new Item.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .imageLocation(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGELOCATION)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .price(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)))
                        .quantity(cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)))
                        .build();
                items.add(item);
            }
            while(cursor.moveToNext());
        }
        return items;
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
