package com.example.kusha_000.inventory;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by kusha_000 on 29-06-2016.
 */
public class ProductDetail extends AppCompatActivity {

    final Context context = this;
    TextView productTitle;
    TextView productQuantity;
    TextView productPrice;
    Button increaseQuantity;
    Button decreseQuantity;
    Button orderMore;
    Button deleteProduct;
    int productId = 0;
    int quantity = 0;
    long supplierId = 0;
    double price = 0;
    String title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        productTitle = (TextView) findViewById(R.id.detailProductName);
        productQuantity = (TextView) findViewById(R.id.detailProductQuantity);
        productPrice = (TextView) findViewById(R.id.detailProductPrice);
        increaseQuantity = (Button) findViewById(R.id.btnIncrease);
        decreseQuantity = (Button) findViewById(R.id.btnDecrease);
        orderMore = (Button) findViewById(R.id.btnOrderMore);
        deleteProduct = (Button) findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            productId = bundle.getInt("Id");
            title = bundle.getString("Title");
            quantity = bundle.getInt("Quantity");
            price = bundle.getDouble("Price");
            supplierId = bundle.getLong("SupplierId");
        }

        productTitle.setText(title);
        productQuantity.setText("Quantity : " + quantity);
        productPrice.setText("Price : INR" + price);

        increaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newQuantity = quantity + 1;
                ContentValues values = new ContentValues();
                values.put(InventoryContract.ProductEntry.COLUMN_NAME_QUANTITY, newQuantity);
                String selection = InventoryContract.ProductEntry._ID + " = ?";
                String[] selectionArgs = {String.valueOf(productId)};
                DataBaseUtils.getInstance(getBaseContext())
                        .updateEntry(InventoryContract.ProductEntry.TABLE_NAME, values, selection,
                                selectionArgs);
                setQuantity(newQuantity);
                MainActivity.refreshCursor();
            }
        });

        decreseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newQuantity = quantity - 1;
                ContentValues values = new ContentValues();
                values.put(InventoryContract.ProductEntry.COLUMN_NAME_QUANTITY, newQuantity);
                String selection = InventoryContract.ProductEntry._ID + " = ?";
                String[] selectionArgs = {String.valueOf(productId)};
                DataBaseUtils.getInstance(getBaseContext())
                        .updateEntry(InventoryContract.ProductEntry.TABLE_NAME, values, selection,
                                selectionArgs);
                setQuantity(newQuantity);
                MainActivity.refreshCursor();
            }
        });

        deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context).setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(getBaseContext().getResources().getString(R.string.delete_dialog_title))
                        .setMessage(
                                getBaseContext().getResources().getString(R.string.delete_dialog_message) + title)
                        .setPositiveButton(getString(R.string.dialog_answer_yes),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String selection = InventoryContract.ProductEntry._ID + " = ?";
                                        String[] selectionArgs = {String.valueOf(productId)};
                                        DataBaseUtils.getInstance(context)
                                                .deleteEntries(InventoryContract.ProductEntry.TABLE_NAME, selection,
                                                        selectionArgs);
                                        MainActivity.refreshCursor();
                                        finish();
                                    }
                                })
                        .setNegativeButton(getString(R.string.dialog_answer_no), null)
                        .show();
            }
        });

        orderMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = getSupplierNumber(supplierId);
                String url = "tel:" + number;
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                startActivity(phoneIntent);
            }
        });
    }

    private void setQuantity(int newQuantity) {
        quantity = newQuantity;
        productQuantity.setText("Quantity : " + quantity);
    }

    private int getSupplierNumber(long supplierId) {
        String selection = InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(supplierId)};
        Cursor cursor = DataBaseUtils.getInstance(getBaseContext())
                .readFromDB(InventoryContract.SupplierEntry.TABLE_NAME, null, selection, selectionArgs);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex(InventoryContract.SupplierEntry.COLUMN_NAME_PHONE));
    }
}

