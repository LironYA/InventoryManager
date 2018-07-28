package com.example.android.inventorymanager;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.inventorymanager.Inventory.InventoryContract.InventoryEntry;
import com.example.android.inventorymanager.Inventory.InventoryDbHelper;

public class EditorActivity extends AppCompatActivity {

    private EditText mEditText_name;
    private EditText mEditText_price;
    private EditText mEditQuantity;
    private EditText mSupplier_name;
    private EditText mSupplier_phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        mEditText_name = (EditText) findViewById(R.id.editText);
        mEditText_price = (EditText) findViewById(R.id.editText_price);
        mEditQuantity = (EditText) findViewById(R.id.editQuantity);
        mSupplier_name = (EditText) findViewById(R.id.supplier_name);
        mSupplier_phone = (EditText) findViewById(R.id.supplier_phone);
    }

    private void InsertItem() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String supplierName = mEditText_name.getText().toString().trim();
        String priceString = mEditText_price.getText().toString().trim();
        int price = Integer.parseInt(priceString);
        String quantityString = mEditQuantity.getText().toString().trim();
        int quantity = Integer.parseInt(quantityString);
        String supplierNameString = mSupplier_name.getText().toString().trim();
        String supplierPhoneString = mSupplier_phone.getText().toString().trim();
        int supplierPhone = Integer.parseInt(supplierPhoneString);
        // Create database helper
        InventoryDbHelper mDbHelper = new InventoryDbHelper(this);
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, supplierName);
        values.put(InventoryEntry.COLUMN_PRICE, price);
        values.put(InventoryEntry.COLUMN_QUANTITY, quantity);
        values.put(InventoryEntry.COLUMN_SUPPLIER_NAME, supplierNameString);
        values.put(InventoryEntry.COLUMN_PHONE, supplierPhone);
        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(InventoryEntry.TABLE_NAME, null, values);
        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving inventory item", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Item saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            InsertItem();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
