package com.pucmm.segundoparcial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView itemList;
    FloatingActionButton btn_add;

    Database myDB;
    ArrayList<String> item_id, item_name, item_price, item_cat;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = findViewById(R.id.itemList);
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add_item.class);
                startActivity(intent);

            }
        });

        myDB = new Database(MainActivity.this);
        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        item_price = new ArrayList<>();
        item_cat = new ArrayList<>();

        storeData();

        customAdapter = new CustomAdapter(MainActivity.this, this, item_id,  item_name, item_price, item_cat);
        itemList.setAdapter(customAdapter);
        itemList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeData(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                item_id.add(cursor.getString(0));
                item_name.add(cursor.getString(1));
                item_price.add(cursor.getString(2));
                item_cat.add(cursor.getString(3));
            }
        }
    }
}