package com.example.mrbarberadmin.clients;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mrbarberadmin.R;
import com.example.mrbarberadmin.db.DBHelper;
import com.example.mrbarberadmin.model.Client;
import com.example.mrbarberadmin.model.Service;
import com.example.mrbarberadmin.services.AddServicesActivity;

public class AddClientsActivity extends AppCompatActivity {
    EditText firstNameText;
    EditText lastNameText;
    EditText phoneText;

    CheckBox distinguishedCheck;

    Button addClientBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clients);

        firstNameText = findViewById(R.id.first_name_text);
        lastNameText = findViewById(R.id.last_name_text);
        phoneText = findViewById(R.id.phone_text);

        distinguishedCheck = findViewById(R.id.distinguished_check);

        addClientBtn = findViewById(R.id.add_client_btn);

        addClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstNameText.length() != 0 && lastNameText.length() != 0 && phoneText.length() != 0){
                    DBHelper DBHelper =
                            new DBHelper(AddClientsActivity.this);

                    int phone = (int) Float.parseFloat(phoneText.getText().toString().trim());
                    int distinguishedValue;

                    if (distinguishedCheck.isChecked()){
                        distinguishedValue = 1;
                    }else{
                        distinguishedValue = 0;
                    }

                    Client client = new Client(
                            firstNameText.getText().toString().trim(),
                            lastNameText.getText().toString().trim(),
                            phone,
                            distinguishedValue
                    );

                    DBHelper.addClient(client);

//                    AddClientsActivity.this.finish();

                } else {
                    Toast.makeText(AddClientsActivity.this,
                            "Debe agregar todos los campos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}