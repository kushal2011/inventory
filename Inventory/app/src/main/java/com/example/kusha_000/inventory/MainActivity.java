package com.example.kusha_000.inventory;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static ProductCustomAdapter customAdapter;
    private static Context mContext;
    Cursor mCursor;
    private ListView listView;

    public static void refreshCursor() {
        Cursor cursor =
                DataBaseUtils.getInstance(mContext).readFromDB(InventoryContract.ProductEntry.TABLE_NAME, null);
        customAdapter.swapCursor(cursor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        TextView infoText = (TextView) findViewById(R.id.product_empty_view);
        listView = (ListView) findViewById(R.id.productList);
        listView.setEmptyView(infoText);
        mCursor = DataBaseUtils.getInstance(this).readFromDB(InventoryContract.ProductEntry.TABLE_NAME, null);
        if (mCursor != null) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    customAdapter = new ProductCustomAdapter(MainActivity.this, mCursor, 0);
                    listView.setAdapter(customAdapter);
                }
            });
        } else {
            infoText.setVisibility(View.VISIBLE);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addProductIntent = new Intent(getApplicationContext(), AddProductActivity.class);
                startActivity(addProductIntent);
            }
        });
    }
}
