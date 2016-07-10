package com.example.kusha_000.inventory;

import android.provider.BaseColumns;

/**
 * Created by kusha_000 on 29-06-2016.
 */
public class InventoryContract {
    private InventoryContract() {
    }

    public static abstract class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "productEntry";
        public static final String COLUMN_NAME_PRODUCT_NAME = "name";
        public static final String COLUMN_NAME_QUANTITY = "quantity";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_SUPPLIER_ID = "supplierId";
    }

    public static abstract class SupplierEntry implements BaseColumns {
        public static final String TABLE_NAME = "supplierEntry";
        public static final String COLUMN_NAME_SUPPLIER_NAME = "name";
        public static final String COLUMN_NAME_SUPPLIER_ID = "id";
        public static final String COLUMN_NAME_PHONE = "phone";
    }
}
