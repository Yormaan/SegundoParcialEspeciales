package com.pucmm.segundoparcial;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText item_name, item_price, item_cat;
    Button update_button, delete_button;
    String id, name, price, cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        item_name = findViewById(R.id.txt_itemName2);
        item_price = findViewById(R.id.txt_itemPrice2);
        item_cat = findViewById(R.id.txt_itemCat2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database myDB = new Database(UpdateActivity.this);
                myDB.updateData(id,name, price, cat);
                name=item_name.getText().toString().trim();
                price=item_price.getText().toString().trim();
                cat=item_cat.getText().toString().trim();

                finish();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getIntentData(){
        if(getIntent().hasExtra("name") && getIntent().hasExtra("price") && getIntent().hasExtra("category")){
        //Getting Data from Intent
          //  id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            price = getIntent().getStringExtra("price");
            cat = getIntent().getStringExtra("category");

            //Setting Intent Data
            item_name.setText(name);
            item_price.setText(price);
            item_cat.setText(cat);
        }else {
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Database myDB = new Database(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }

}