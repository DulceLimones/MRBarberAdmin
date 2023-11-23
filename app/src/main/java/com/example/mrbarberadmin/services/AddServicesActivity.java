package com.example.mrbarberadmin.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mrbarberadmin.R;
import com.example.mrbarberadmin.db.DBHelper;
import com.example.mrbarberadmin.model.Service;

public class AddServicesActivity extends AppCompatActivity {

    EditText nameText;
    EditText costText;
    EditText durationText;
    Button addServicesBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_services);

        nameText = findViewById(R.id.name_text);
        costText = findViewById(R.id.cost_text);
        durationText = findViewById(R.id.duration_text);

        addServicesBtn = findViewById(R.id.add_service_btn);

        addServicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nameText.length() != 0 && costText.length() != 0 && durationText.length() != 0){
                DBHelper dbHelper =
                        new DBHelper(AddServicesActivity.this);

                int time = (int) Float.parseFloat(durationText.getText().toString().trim());

                Service service = new Service(
                        nameText.getText().toString().trim(),
                        time,
                        Float.parseFloat(costText.getText().toString().trim())
                );
                dbHelper.addService(service);
//                AddServicesActivity.this.finish();

            } else {
                    Toast.makeText(AddServicesActivity.this,
                            "Debe agregar todos los campos",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}