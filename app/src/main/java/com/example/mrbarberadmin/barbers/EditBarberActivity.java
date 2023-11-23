package com.example.mrbarberadmin.barbers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mrbarberadmin.R;
import com.example.mrbarberadmin.db.DBHelper;
import com.example.mrbarberadmin.model.Barber;
import com.example.mrbarberadmin.services.EditServicesActivity;

public class EditBarberActivity extends AppCompatActivity {
    EditText nameText;
    Button editBarberBtn;

    String id;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barber);
        nameText = findViewById(R.id.name_text_edit);
        editBarberBtn = findViewById(R.id.edit_barber_btn);

        editBarberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameText.length() != 0){
                    name = nameText.getText().toString().trim();

                    DBHelper dbHelper = new DBHelper(EditBarberActivity.this);
                    dbHelper.editBarber(id, name);

                } else {
                    Toast.makeText(EditBarberActivity.this,
                            "Debe agregar todos los campos",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });
        getAndSetData();

    }

    void getAndSetData(){
        if (getIntent().hasExtra("id")
                && getIntent().hasExtra("name")){

            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");

            nameText.setText(name);
        }

    }
}