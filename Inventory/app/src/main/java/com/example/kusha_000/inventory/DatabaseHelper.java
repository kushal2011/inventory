package com.example.kusha_000.inventory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kusha_000 on 29-06-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_INVENTORY_PRODUCT_TABLE =
            "CREATE TABLE " + InventoryContract.ProductEntry.TABLE_NAME + " (" +
                    InventoryContract.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    InventoryContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME + " TEXT NOT NULL," +
                    InventoryContract.ProductEntry.COLUMN_NAME_QUANTITY + " INT NOT NULL," +
                    InventoryContract.ProductEntry.COLUMN_NAME_PRICE + " DOUBLE NOT NULL," +
                    InventoryContract.ProductEntry.COLUMN_NAME_SUPPLIER_ID + " LONG NOT NULL);";

    private static final String DELETE_INVENTORY_PRODUCT_TABLE =
            "DROP TABLE IF EXISTS" + InventoryContract.ProductEntry.TABLE_NAME;

    private static final String CREATE_INVENTORY_SUPPLIER_TABLE =
            "CREATE TABLE " + InventoryContract.SupplierEntry.TABLE_NAME + " (" +
                    InventoryContract.SupplierEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_NAME + " TEXT NOT NULL," +
                    InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_ID + " INT NOT NULL," +
                    InventoryContract.SupplierEntry.COLUMN_NAME_PHONE + " INT NOT NULL);";

    private static final String DELETE_INVENTORY_SUPPLIER_TABLE =
            "DROP TABLE IF EXISTS" + InventoryContract.SupplierEntry.TABLE_NAME;

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_INVENTORY_SUPPLIER_TABLE);
        sqLiteDatabase.execSQL(CREATE_INVENTORY_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DELETE_INVENTORY_SUPPLIER_TABLE);
        sqLiteDatabase.execSQL(DELETE_INVENTORY_PRODUCT_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void deleteDatabase() {
        context.deleteDatabase(DATABASE_NAME);
    }
}



