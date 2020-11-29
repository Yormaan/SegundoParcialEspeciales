package com.pucmm.segundoparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_item extends AppCompatActivity {

    EditText txt_itemName, txt_itemPrice, txt_itemCat;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        txt_itemName = findViewById(R.id.txt_itemName);
        txt_itemPrice = findViewById(R.id.txt_itemPrice);
        txt_itemCat = findViewById(R.id.txt_itemCat);
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database myDB = new Database(Add_item.this);
                myDB.addItems(txt_itemName.getText().toString().trim(),
                        txt_itemPrice.getText().toString().trim(),
                        txt_itemCat.getText().toString().trim());

                finish();
            }
        });

    }
}