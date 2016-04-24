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
import shoppingcart.cput.ac.za.shoppingcart.domain.Contact;
import shoppingcart.cput.ac.za.shoppingcart.repository.ContactRepository;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-24
 */
public class ContactRepositoryImpl extends SQLiteOpenHelper implements ContactRepository {

    public static final String TABLE_NAME = "contact";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL= "email";
    public static final String COLUMN_TELEPHONE= "telephone";
    public static final String COLUMN_CELLPHONE= "cellphone";

    //database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT NOT NULL, "
            + COLUMN_TELEPHONE + " TEXT NOT NULL, "
            + COLUMN_CELLPHONE + " TEXT NOT NULL );";

    public ContactRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Contact findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_EMAIL,
                        COLUMN_TELEPHONE,
                        COLUMN_CELLPHONE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if ( cursor.moveToFirst()) {
            final Contact contact = new Contact.Builder().id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .telephone(cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE)))
                    .cellphone(cursor.getString(cursor.getColumnIndex(COLUMN_CELLPHONE)))
                    .build();
            return contact;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Contact save(Contact entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_EMAIL, entity.getEmail());
        values.put(COLUMN_TELEPHONE, entity.getTelephone());
        values.put(COLUMN_CELLPHONE, entity.getCellPhone());

        long id  = db.insertOrThrow(TABLE_NAME, null,values);
        Contact insertedEntity = new Contact.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return  insertedEntity;
    }

    @Override
    public Contact update(Contact entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_EMAIL, entity.getEmail());
        values.put(COLUMN_TELEPHONE, entity.getTelephone());
        values.put(COLUMN_CELLPHONE, entity.getCellPhone());

        db.update(
                TABLE_NAME, values, COLUMN_ID + " = ? ", new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Contact delete(Contact entity) {
        open();
        db.delete(
                TABLE_NAME, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Set<Contact> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Contact> contacts = new HashSet<>();

        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst())
        {
            do{
                final Contact contact = new Contact.Builder().id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .telephone(cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE)))
                        .cellphone(cursor.getString(cursor.getColumnIndex(COLUMN_CELLPHONE)))
                        .build();
                contacts.add(contact);
            }
            while(cursor.moveToNext());
        }
        return contacts;
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
