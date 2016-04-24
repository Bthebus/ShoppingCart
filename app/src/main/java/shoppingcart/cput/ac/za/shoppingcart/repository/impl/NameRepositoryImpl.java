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
import shoppingcart.cput.ac.za.shoppingcart.domain.Name;
import shoppingcart.cput.ac.za.shoppingcart.repository.NameRepository;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-24
 */
public class NameRepositoryImpl extends SQLiteOpenHelper implements NameRepository {

    public static final String TABLE_NAME = "name";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MIDDLENAME = "middleName";
    public static final String COLUMN_SURNAME = "surname";

    //database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_MIDDLENAME + " TEXT NOT NULL, "
            + COLUMN_SURNAME + " TEXT NOT NULL );";

    public NameRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Name findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_MIDDLENAME,
                        COLUMN_SURNAME},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if ( cursor.moveToFirst()) {
            final Name name = new Name.Builder()
                    .Id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .middleName(cursor.getString(cursor.getColumnIndex(COLUMN_MIDDLENAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .build();
            return name;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Name save(Name entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_MIDDLENAME, entity.getMiddleName());
        values.put(COLUMN_SURNAME, entity.getSurname());

        long id  = db.insert(TABLE_NAME, null,values);
        Name insertedEntity = new Name.Builder()
                .copy(entity)
                .Id(new Long(id))
                .build();
        return  insertedEntity;
    }

    @Override
    public Name update(Name entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_MIDDLENAME, entity.getMiddleName());
        values.put(COLUMN_SURNAME, entity.getSurname());

        db.update(
                TABLE_NAME, values, COLUMN_ID + " = ? ", new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Name delete(Name entity) {
        open();
        db.delete(
                TABLE_NAME, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Set<Name> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Name> names = new HashSet<>();

        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst())
        {
            do{
                final Name name = new Name.Builder()
                        .Id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .middleName(cursor.getString(cursor.getColumnIndex(COLUMN_MIDDLENAME)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                        .build();
                names.add(name);
            }
            while(cursor.moveToNext());
        }
        return names;
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
