package com.example.mrbarberadmin.appointments;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mrbarberadmin.R;
import com.example.mrbarberadmin.db.DBHelper;
import com.example.mrbarberadmin.model.Appointment;
import com.example.mrbarberadmin.model.Barber;
import com.example.mrbarberadmin.model.Client;
import com.example.mrbarberadmin.model.Service;
import com.example.mrbarberadmin.services.EditServicesActivity;

import java.util.ArrayList;

public class EditAppointmentActivity extends AppCompatActivity {
    private Spinner serviceSpinner;
    private Spinner clientSpinner;
    private Spinner barberSpinner;
    private Button editAppointmentBtn;

    int id;
    int clientId;
    int serviceId;
    int barberId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);
        serviceSpinner = findViewById(R.id.service_spinner_edit);
        clientSpinner = findViewById(R.id.client_spinner_edit);
        barberSpinner = findViewById(R.id.barber_spinner_edit);
        editAppointmentBtn = findViewById(R.id.edit_appointment_btn);

        editAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                name = nameText.getText().toString().trim();
//                duration = durationText.getText().toString().trim();
//                price = costText.getText().toString().trim();
//                DBHelper dbHelper = new DBHelper(EditServicesActivity.this);
//                dbHelper.editAppointment(id, name, price, duration);

            }
        });
//        getAndSetData();

    }

//    void getAndSetData(){
//        if (getIntent().hasExtra("id")
//                && getIntent().hasExtra("serviceId")
//                && getIntent().hasExtra("barberId")
//                && getIntent().hasExtra("clientId")){
//
//            id = Integer.parseInt(getIntent().getStringExtra("id"));
//            serviceId = Integer.parseInt(getIntent().getStringExtra("serviceId"));
//            barberId = Integer.parseInt(getIntent().getStringExtra("barberId"));
//            clientId = Integer.parseInt(getIntent().getStringExtra("clientId"));
//
//            DBHelper dbHelper = new DBHelper(EditAppointmentActivity.this);
//            ArrayList<Appointment> appointments = dbHelper.getAppointment();
//            Service service = dbHelper.findServiceById(appointments.get(serviceId).getServiceId());
//            Barber barber =dbHelper.findBarberById(appointments.get(barberId).getBarberId());
//            Client client = dbHelper.findClientById(appointments.get(clientId).getClientId());
//
//            serviceSpinner.setSelection();
//            clientSpinner = findViewById(R.id.client_spinner_edit);
//            barberSpinner =
//
//        }
//
//    }
}