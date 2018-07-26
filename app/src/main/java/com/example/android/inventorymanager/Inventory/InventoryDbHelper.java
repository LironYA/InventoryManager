package com.example.android.inventorymanager.Inventory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.android.inventorymanager.Inventory.InventoryContract.InventoryEntry;

public class InventoryDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "supplier.db";
    public static final int DATABASE_VERSION = 1;

    private final String SQL_CREATE_ENTRIES = "CREATE TABLE " + InventoryEntry.TABLE_NAME + "("
            + InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + InventoryEntry.COLUMN_PRODUCT_NAME + " TEXT, "
            + InventoryEntry.COLUMN_PRICE + " INTEGER, "
            + InventoryEntry.COLUMN_QUANTITY +  " INTEGER, "
            + InventoryEntry.COLUMN_SUPPLIER_NAME + " TEXT, "
            + InventoryEntry.COLUMN_PHONE + " INTEGER);";
    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
