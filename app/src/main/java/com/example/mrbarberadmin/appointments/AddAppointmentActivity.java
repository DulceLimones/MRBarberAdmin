package com.example.mrbarberadmin.appointments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mrbarberadmin.R;
import com.example.mrbarberadmin.db.DBHelper;
import com.example.mrbarberadmin.model.Appointment;
import com.example.mrbarberadmin.model.Barber;
import com.example.mrbarberadmin.model.Client;
import com.example.mrbarberadmin.model.Service;
import com.example.mrbarberadmin.services.AddServicesActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class AddAppointmentActivity extends AppCompatActivity {
    private Spinner serviceSpinner;
    private Spinner clientSpinner;
    private Spinner barberSpinner;
    private Spinner timeSpinner;
    private Button openCalendarBtn;
    private TextView dateSelected;
    private Button addAppointmentBtn;

    ArrayList<Service> services ;
    ArrayList<Client> clients ;
    ArrayList<Barber> barbers;
    ArrayList<String> timeSlots;

    int clientId;
    int serviceId;
    int barberId;
    String date;
    String time;

    int day;
    int month;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        serviceSpinner = findViewById(R.id.service_spinner);
        clientSpinner = findViewById(R.id.client_spinner);
        barberSpinner = findViewById(R.id.barber_spinner);
        timeSpinner = findViewById(R.id.time_spinner);
        addAppointmentBtn = findViewById(R.id.add_appointment_btn);
        dateSelected = findViewById(R.id.dateSelected);
        openCalendarBtn = findViewById(R.id.view_calendar_btn);

        openCalendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                day = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddAppointmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        String formatedDay;
                        String formatedMonth;

                        if (selectedDay<10){
                            formatedDay = "0"+String.valueOf(selectedDay);
                        }
                        else{
                            formatedDay = String.valueOf(selectedDay);
                        }

                        int months = selectedMonth+1;

                        if (months<10){
                            formatedMonth = "0"+String.valueOf(months);
                        }
                        else{
                            formatedMonth = String.valueOf(months);
                        }
                        date = formatedDay+"/"+formatedMonth+"/"+selectedYear;
                        dateSelected.setText(date);


                    }
                },year, month,day);

                datePickerDialog.show();
            }
        });

        addAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper =
                        new DBHelper(AddAppointmentActivity.this);

                Appointment appointment = new Appointment(clientId,serviceId,barberId,date,time);
                dbHelper.addAppointment(appointment);
//                AddAppointmentActivity.this.finish();
            }
        });

       getData();


        ArrayAdapter<Service> servicesAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,services);
        serviceSpinner.setAdapter(servicesAdapter);

        serviceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                serviceId = services.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AddAppointmentActivity .this,
                        "Debe agregar todos los campos",
                        Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<Client> clientsAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,clients);
        clientSpinner.setAdapter(clientsAdapter);

        clientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                clientId = clients.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AddAppointmentActivity.this,
                        "Debe agregar todos los campos",
                        Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<Barber> barbersAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,barbers);
        barberSpinner.setAdapter(barbersAdapter);

        barberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                barberId = barbers.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AddAppointmentActivity.this,
                        "Debe agregar todos los campos",
                        Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,timeSlots);
        timeSpinner.setAdapter(timeAdapter);

        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                time = timeSlots.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AddAppointmentActivity.this,
                        "Debe agregar todos los campos",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    void getData(){
        DBHelper dbHelper = new DBHelper(AddAppointmentActivity.this);
        services = dbHelper.getServices();
        clients = dbHelper.getClients();
        barbers = dbHelper.getBarbers();

        timeSlots = new ArrayList<>();
        timeSlots.add("10:00 am");
        timeSlots.add("10:30 am");
        timeSlots.add("11:00 am");
        timeSlots.add("11:30 am");
        timeSlots.add("12:00 pm");
        timeSlots.add("12:30 pm");
        timeSlots.add("1:00 pm");
        timeSlots.add("1:30 pm");
        timeSlots.add("2:00 pm");
        timeSlots.add("2:30 pm");
        timeSlots.add("3:00 pm");
        timeSlots.add("3:30 pm");
        timeSlots.add("4:00 pm");
        timeSlots.add("4:30 pm");
        timeSlots.add("5:00 pm");
        timeSlots.add("5:30 pm");
        timeSlots.add("6:00 pm");
        timeSlots.add("6:30 pm");
        timeSlots.add("7:00 pm");
        timeSlots.add("7:30 pm");
        timeSlots.add("8:00 pm");
        timeSlots.add("8:30 pm");
        timeSlots.add("9:00 pm");
        timeSlots.add("9:30 pm");

    }

}