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
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.User;
import shoppingcart.cput.ac.za.shoppingcart.repository.UserRepository;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-04-25
 */
public class UserRepositoryImpl extends SQLiteOpenHelper implements UserRepository {
    public static final String TABLE_NAME = "user";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    //Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT NOT NULL, "
            + COLUMN_PASSWORD + " TEXT NOT NULL );";

    public UserRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public User findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_USERNAME,
                        COLUMN_PASSWORD
                },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if ( cursor.moveToFirst())
        {
            final User user = new User.Builder().id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                    .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                    .build();
            return user;
        }
        else {
            return null;
        }
    }

    @Override
    public User save(User entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_USERNAME, entity.getUsername());
        values.put(COLUMN_PASSWORD, entity.getPassword());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        User insertedEntity = new User.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public User update(User entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_USERNAME, entity.getUsername());
        values.put(COLUMN_PASSWORD, entity.getPassword());

        db.update(
                TABLE_NAME, values, COLUMN_ID + " = ? ", new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public User delete(User entity) {
        open();
        db.delete(
                TABLE_NAME, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Set<User> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<User> users = new HashSet<>();

        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst())
        {
            do{
                final User user = new User.Builder().id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                        .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                        .build();
                users.add(user);
            }
            while(cursor.moveToNext());
        }
        return users;
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
