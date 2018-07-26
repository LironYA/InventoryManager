package com.example.android.inventorymanager.Inventory;

import android.provider.BaseColumns;

//Class is final because it's just a class to providing constants and there no need to import or extend
public final class InventoryContract {

    public static abstract class InventoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "inventory";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_PHONE = "phone";

    }
}
