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
import shoppingcart.cput.ac.za.shoppingcart.domain.Employee;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.Impl.Name;
import shoppingcart.cput.ac.za.shoppingcart.domain.Personal.User;
import shoppingcart.cput.ac.za.shoppingcart.repository.EmployeeRepository;

/**
 * Author       : Braedy Thebus
 * Stud num     : 213039168
 * Email        : bthebus2@gmail.com
 * Date created : 2016-05-07
 */
public class EmployeeRepositoryImpl extends SQLiteOpenHelper implements EmployeeRepository{

    public static final String TABLE_NAME = "employee";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME= "name";
    public static final String COLUMN_MIDDLENAME= "middleName";
    public static final String COLUMN_SURNAME= "surname";
    public static final String COLUMN_USERNAME= "username";
    public static final String COLUMN_PASSWORD= "password";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL,"
            + COLUMN_MIDDLENAME+ " TEXT, "
            + COLUMN_SURNAME+ " TEXT NOT NULL, "
            + COLUMN_USERNAME + " TEXT NOT NULL, "
            + COLUMN_PASSWORD + " TEXT NOT NULL); ";

    public EmployeeRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Employee findById(Long id) {
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_MIDDLENAME,
                        COLUMN_SURNAME,
                        COLUMN_USERNAME,
                        COLUMN_PASSWORD
                },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor.moveToFirst())
        {
            //Name object
            Name name = new Name.Builder()
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .middleName(cursor.getString(cursor.getColumnIndex(COLUMN_MIDDLENAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .build();


            //User object
            User user = new User.Builder()
                    .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                    .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                    .build();

            //Employee object
            Employee employee = new Employee.Builder()
                    .name(name)
                    .user(user)
                    .build();
            return employee;
        }
        else
        {
            return null;
        }

    }

    @Override
    public Employee save(Employee entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName().getName());
        values.put(COLUMN_MIDDLENAME, entity.getName().getMiddleName());
        values.put(COLUMN_SURNAME, entity.getName().getSurname());
        values.put(COLUMN_USERNAME, entity.getUser().getUsername());
        values.put(COLUMN_PASSWORD, entity.getUser().getPassword());

        long id = db.insertOrThrow(TABLE_NAME, null,values);
        Employee insertedEntity = new Employee.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Employee update(Employee entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName().getName());
        values.put(COLUMN_MIDDLENAME, entity.getName().getMiddleName());
        values.put(COLUMN_SURNAME, entity.getName().getSurname());
        values.put(COLUMN_USERNAME, entity.getUser().getUsername());
        values.put(COLUMN_PASSWORD, entity.getUser().getPassword());

        db.update(
                TABLE_NAME, values, COLUMN_ID + " = ? ", new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Employee delete(Employee entity) {
        open();
        db.delete(
                TABLE_NAME, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Set<Employee> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Employee> employees  = new HashSet<>();

        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do{
                //Name object
                Name name = new Name.Builder()
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .middleName(cursor.getString(cursor.getColumnIndex(COLUMN_MIDDLENAME)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                        .build();


                //User object
                User user = new User.Builder()
                        .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                        .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                        .build();

                //Employee object
                Employee employee = new Employee.Builder()
                        .name(name)
                        .user(user)
                        .build();
                employees.add(employee);
            }while(cursor.moveToNext());
        }
        return employees;
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
