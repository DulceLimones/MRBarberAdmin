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
import com.example.mrbarberadmin.services.EditServicesActivity;

import java.util.Objects;

public class EditClientsActivity extends AppCompatActivity {
    EditText firstNameText;
    EditText lastNameText;
    EditText phoneText;

    CheckBox distinguishedCheck;

    Button editClientBtn;

    String id;
    String firstName;
    String lastName;
    String phone;
    String distinguished;
    String lastVisit;
    String amountVisits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_clients);

        firstNameText = findViewById(R.id.first_name_text_edit);
        lastNameText = findViewById(R.id.last_name_text_edit);
        phoneText = findViewById(R.id.phone_text_edit);

        distinguishedCheck = findViewById(R.id.distinguished_check_edit);

        editClientBtn = findViewById(R.id.edit_client_btn);


        editClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstNameText.length() != 0 && lastNameText.length() != 0 && phoneText.length() != 0){

                    int phoneNum = (int) Float.parseFloat(phoneText.getText().toString().trim());

                    firstName = firstNameText.getText().toString().trim();
                    lastName = lastNameText.getText().toString().trim();
                    phone = String.valueOf(phoneNum);
                    distinguished =String.valueOf(isDisinguished());
                    lastVisit = getIntent().getStringExtra("lastVisit");
                    amountVisits = getIntent().getStringExtra("amountVisits");
                    DBHelper dbHelper = new DBHelper(EditClientsActivity.this);
                    dbHelper.editClient(id, firstName, lastName, phone,distinguished, lastVisit, amountVisits);

                } else {
                    Toast.makeText(EditClientsActivity.this,
                            "Debe agregar todos los campos",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        getAndSetData();

    }

    int isDisinguished(){
        if (distinguishedCheck.isChecked()){
            return 1;
        }
        return 0;
    }

    void getAndSetData(){
        if (getIntent().hasExtra("id")
                && getIntent().hasExtra("firstName")
                && getIntent().hasExtra("lastName")
                && getIntent().hasExtra("phone")
                && getIntent().hasExtra("distinguished")
                && getIntent().hasExtra("lastVisit")
                && getIntent().hasExtra("amountVisits")){

            id = getIntent().getStringExtra("id");
            firstName = getIntent().getStringExtra("firstName");
            lastName = getIntent().getStringExtra("lastName");
            phone = getIntent().getStringExtra("phone");
            distinguished = getIntent().getStringExtra("distinguished");
            lastVisit = getIntent().getStringExtra("lastVisit");
            amountVisits = getIntent().getStringExtra("amountVisits");

            firstNameText.setText(firstName);
            lastNameText.setText(lastName);
            phoneText.setText(phone);
            if(Objects.equals(distinguished, "1")){
                distinguishedCheck.setChecked(true);
            }else {
                distinguishedCheck.setChecked(false);
            }
        }

    }
}