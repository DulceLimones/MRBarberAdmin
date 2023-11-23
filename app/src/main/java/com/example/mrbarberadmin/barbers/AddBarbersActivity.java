package com.example.mrbarberadmin.barbers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mrbarberadmin.R;
import com.example.mrbarberadmin.clients.AddClientsActivity;
import com.example.mrbarberadmin.db.DBHelper;
import com.example.mrbarberadmin.model.Barber;
import com.example.mrbarberadmin.model.Client;

public class AddBarbersActivity extends AppCompatActivity {
    EditText nameText;

    Button addBarberBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barbers);

        nameText = findViewById(R.id.name_text);

        addBarberBtn = findViewById(R.id.add_barber_btn);

        addBarberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameText.length() != 0){
                    DBHelper DBHelper =
                            new DBHelper(AddBarbersActivity.this);

                    Barber barber = new Barber(
                            nameText.getText().toString().trim()
                    );

                    DBHelper.addBarber(barber);


//                    AddBarbersActivity.this.finish();

                } else {
                    Toast.makeText(AddBarbersActivity.this,
                            "Debe agregar todos los campos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}