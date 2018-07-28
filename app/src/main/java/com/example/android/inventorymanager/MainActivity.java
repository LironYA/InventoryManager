package com.example.android.inventorymanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.inventorymanager.Inventory.InventoryContract.InventoryEntry;
import com.example.android.inventorymanager.Inventory.InventoryDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton floatingActionButton =
                (FloatingActionButton) findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        InventoryDbHelper mDbHelper = new InventoryDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        InventoryDbHelper mDbHelper = new InventoryDbHelper(this);
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryEntry.COLUMN_PRICE,
                InventoryEntry.COLUMN_QUANTITY,
                InventoryEntry.COLUMN_SUPPLIER_NAME,
                InventoryEntry.COLUMN_PHONE};
        Cursor cursor = db.query(InventoryEntry.TABLE_NAME, projection,
                null, null,
                null, null, null);
        TextView displayView = (TextView) findViewById(R.id.items);
        try {
            displayView.setText(getString(R.string.table_contains) + cursor.getCount() + getString(R.string.items));
            displayView.append(InventoryEntry._ID + " - " +
                    InventoryEntry.COLUMN_PRODUCT_NAME + "-" + InventoryEntry.COLUMN_PRICE + "-"
                    + InventoryEntry.COLUMN_QUANTITY + "-" + InventoryEntry.COLUMN_SUPPLIER_NAME
                    + InventoryEntry.COLUMN_PHONE);
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(InventoryEntry._ID);
            int productNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME);
            int columnPrice = cursor.getColumnIndex(InventoryEntry.COLUMN_PRICE);
            int columnQuantity = cursor.getColumnIndex(InventoryEntry.COLUMN_QUANTITY);
            int columnSupplier = cursor.getColumnIndex(InventoryEntry.COLUMN_SUPPLIER_NAME);
            int columnPhone = cursor.getColumnIndex(InventoryEntry.COLUMN_PHONE);
            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentProductName = cursor.getString(productNameColumnIndex);
                int currentPrice = cursor.getInt(columnPrice);
                int currentQuantity = cursor.getInt(columnQuantity);
                String currentSupplier = cursor.getString(columnSupplier);
                int currentPhone = cursor.getInt(columnPhone);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " | " +
                        currentProductName + " | " + currentPrice + " | " + currentQuantity + " | "
                        + currentSupplier + " | " + currentPhone));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
