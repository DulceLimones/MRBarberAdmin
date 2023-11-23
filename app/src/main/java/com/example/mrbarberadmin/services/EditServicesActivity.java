package com.example.mrbarberadmin.services;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mrbarberadmin.R;
import com.example.mrbarberadmin.db.DBHelper;
import com.example.mrbarberadmin.model.Service;

public class EditServicesActivity extends AppCompatActivity {

    EditText nameText;
    EditText costText;
    EditText durationText;
    Button editServicesBtn;

    String id;
    String name;
    String price;
    String duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_services);
        nameText = findViewById(R.id.name_text_edit);
        costText = findViewById(R.id.cost_text_edit);
        durationText = findViewById(R.id.duration_text_edit);
        editServicesBtn = findViewById(R.id.edit_service_btn);

        editServicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameText.length() != 0 && costText.length() != 0 && durationText.length() != 0){

                    int time = (int) Float.parseFloat(durationText.getText().toString().trim());

                    name = nameText.getText().toString().trim();
                    duration = String.valueOf(time);
                    price = costText.getText().toString().trim();

                    DBHelper dbHelper = new DBHelper(EditServicesActivity.this);
                    dbHelper.editService(id, name, price, duration);

                } else {
                    Toast.makeText(EditServicesActivity.this,
                            "Debe agregar todos los campos",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
         getAndSetData();

    }

    void getAndSetData(){
        if (getIntent().hasExtra("id")
                && getIntent().hasExtra("name")
                && getIntent().hasExtra("cost")
                && getIntent().hasExtra("duration")){

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        price = getIntent().getStringExtra("cost");
        duration = getIntent().getStringExtra("duration");

        nameText.setText(name);
        costText.setText(price);
        durationText.setText(duration);

        }

    }
}