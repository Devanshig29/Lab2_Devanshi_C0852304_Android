package com.example.lab2_devanshi_c0852304_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "product_database";
    private static final String TABLE_NAME = "product";
    private static final String COLUMN_ID = "prodid";
    private static final String COLUMN_NAME = "prodname";
    private static final String COLUMN_DESC = "proddesc";
    private static final String COLUMN_PRICE = "prodprice";


    public DatabaseAdapter(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER NOT NULL CONSTRAINT product_pk PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " VARCHAR(20) NOT NULL, " +
                COLUMN_DESC + " VARCHAR(50) NOT NULL, " +
                COLUMN_PRICE + " DOUBLE NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop table and then create it
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }
    /**
     * Query database - select all the product name
     * @return cursor
     * */
    public Cursor findProductByName(String productName) {
        // we need a readable instance of database
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE prodname LIKE'%" + productName + "%'", null);
    }


    /**
     * Query database - select all the product description
     * @return cursor
     * */
    public Cursor findProductByDescription(String productDescription) {
        // we need a readable instance of database
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE PRODUCT_DESCRIPTION LIKE '%" + productDescription + "%'", null);
    }

    // insert product
    public boolean addProduct(String name, String description, double price) {
        // we need a writeable instance of SQLite database
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // we need to define a content values in order to insert data into our database
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_DESC, description);
        contentValues.put(COLUMN_PRICE, String.valueOf(price));

        // the insert method associated to SQLite database instance returns -1 if nothing is inserted
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues) != -1;
    }

    /**
     * Query database - select all the product
     * @return cursor
     * */
    public Cursor getAllProducts() {
        // we need a readable instance of database
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor getFirstProduct()
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME  + " LIMIT 1", null);
    }
    /**
     * Update product in database
     * @param id
     * @param name
     * @param desc
     * @param price
     * @return boolean value - true (successful)
     * */
    public boolean updateProduct(int id, String name, String desc, double price) {
        // we need a writeable instance of database
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_DESC, desc);
        contentValues.put(COLUMN_PRICE, String.valueOf(price));

        // update method associated to SQLite database instance returns number of rows affected
        return sqLiteDatabase.update(TABLE_NAME,
                contentValues,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}) > 0;
    }

    /**
     * Delete product from database table
     * @param id
     * @return true if is successful
     * */
    public boolean deleteProduct(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // the delete method associated to the SQLite database instance returns the number of rows affected
        return sqLiteDatabase.delete(TABLE_NAME,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}) > 0;
    }
}
