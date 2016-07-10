package com.example.kusha_000.inventory;

import android.app.Application;
import android.content.ContentValues;

/**
 * Created by kusha_000 on 29-06-2016.
 */
public class Inventory extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        createSupplierRecord();
        createProductRecord();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private void createSupplierRecord() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_NAME,
                getString(R.string.reliance_digital));
        contentValues.put(InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_ID, 101);
        contentValues.put(InventoryContract.SupplierEntry.COLUMN_NAME_PHONE, 554314567);
        DataBaseUtils.getInstance(this)
                .insertIntoDB(InventoryContract.SupplierEntry.TABLE_NAME, contentValues);
        contentValues = new ContentValues();
        contentValues.put(InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_NAME, getString(R.string.chroma));
        contentValues.put(InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_ID, 102);
        contentValues.put(InventoryContract.SupplierEntry.COLUMN_NAME_PHONE, 152120523);
        DataBaseUtils.getInstance(this)
                .insertIntoDB(InventoryContract.SupplierEntry.TABLE_NAME, contentValues);
    }

    private void createProductRecord() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME, getString(R.string.alianware));
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_QUANTITY, 20);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_PRICE, 100000);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_SUPPLIER_ID, 102);
        DataBaseUtils.getInstance(this)
                .insertIntoDB(InventoryContract.ProductEntry.TABLE_NAME, contentValues);

        contentValues = new ContentValues();
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME, getString(R.string.nexux_six));
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_QUANTITY, 10);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_PRICE, 23467.65);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_SUPPLIER_ID, 101);
        DataBaseUtils.getInstance(this)
                .insertIntoDB(InventoryContract.ProductEntry.TABLE_NAME, contentValues);

        contentValues = new ContentValues();
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME, getString(R.string.zenwatch_two));
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_QUANTITY, 40);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_PRICE, 98000.00);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_SUPPLIER_ID, 101);
        DataBaseUtils.getInstance(this)
                .insertIntoDB(InventoryContract.ProductEntry.TABLE_NAME, contentValues);
    }
}

