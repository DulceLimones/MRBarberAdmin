package com.example.mrbarberadmin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mrbarberadmin.model.Appointment;
import com.example.mrbarberadmin.model.Barber;
import com.example.mrbarberadmin.model.Client;
import com.example.mrbarberadmin.model.Service;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private final Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MrBarberDATA.db";

    public static final String TABLE_SERVICES = "services";
    public static final String COLUMN_SERVICES_ID = "_id";
    public static final String COLUMN_SERVICES_NAME = "_name";
    public static final String COLUMN_SERVICES_DURATION = "_duration";
    public static final String COLUMN_SERVICES_PRICE = "_price";

    public static final String TABLE_BARBERS = "barbers";
    public static final String COLUMN_BARBERS_ID = "_id";
    public static final String COLUMN_BARBERS_NAME = "_name";

    public static final String TABLE_BARBER_SERVICES = "barber_services";
    public static final String COLUMN_BARBER_ID = "_barberId";
    public static final String COLUMN_SERVICE_ID = "_serviceId";

    public static final String TABLE_CLIENTS = "clients";
    public static final String COLUMN_CLIENTS_ID = "_id";
    public static final String COLUMN_CLIENTS_FIRST_NAME = "_firstName";
    public static final String COLUMN_CLIENTS_LAST_NAME = "_lastName";
    public static final String COLUMN_CLIENTS_PHONE = "_phone";
    public static final String COLUMN_CLIENTS_DISTINGUISHED = "_distinguished";
    public static final String COLUMN_CLIENTS_LAST_VISIT = "_lastVisit";
    public static final String COLUMN_CLIENTS_AMOUNT_VISITS = "_amountVisits";

    public static final String TABLE_APPOINTMENTS = "appointments";
    public static final String COLUMN_AP_ID = "_id";
    public static final String COLUMN_AP_CLIENT_ID = "_clientId";
    public static final String COLUMN_AP_SERVICE_ID = "_serviceId";
    public static final String COLUMN_AP_BARBER_ID = "_barberId";
    public static final String COLUMN_AP_DATE = "_date";
    public  static final String COLUMN_AP_TIMESLOT = "_timeSlot";



    public DBHelper (@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_SERVICES = "CREATE TABLE " + TABLE_SERVICES +
                " (" +COLUMN_SERVICES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SERVICES_NAME + "  TEXT, " +
                COLUMN_SERVICES_DURATION + " INTEGER, " +
                COLUMN_SERVICES_PRICE + " DECIMAL " +
                ")";

        String CREATE_TABLE_BARBERS = "CREATE TABLE " + TABLE_BARBERS +
                " (" + COLUMN_BARBERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BARBERS_NAME + "  TEXT " +
                ")";

        String CREATE_TABLE_BARBER_SERVICES = "CREATE TABLE " + TABLE_BARBER_SERVICES +
                " (" + COLUMN_BARBER_ID + " INTEGER, " +
                COLUMN_SERVICE_ID + "  INTEGER " +
                ")";

        String CREATE_TABLE_CLIENTS = "CREATE TABLE " + TABLE_CLIENTS +
                " (" + COLUMN_CLIENTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CLIENTS_FIRST_NAME + "  TEXT, " +
                COLUMN_CLIENTS_LAST_NAME + "  TEXT, " +
                COLUMN_CLIENTS_PHONE + "  INTEGER, " +
                COLUMN_CLIENTS_DISTINGUISHED + "  INTEGER, " +
                COLUMN_CLIENTS_LAST_VISIT + "  TEXT, " +
                COLUMN_CLIENTS_AMOUNT_VISITS + "  INTEGER " +
                ")";

        String CREATE_TABLE_APPOINTMENTS = "CREATE TABLE " + TABLE_APPOINTMENTS +
                " (" +COLUMN_AP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_AP_CLIENT_ID+ "  INTEGER, " +
                COLUMN_AP_SERVICE_ID + " INTEGER, " +
                COLUMN_AP_BARBER_ID + " INTEGER, " +
                COLUMN_AP_DATE+" TEXT, "+
                COLUMN_AP_TIMESLOT+" TEXT "+
                ")";


//
        db.execSQL(CREATE_TABLE_SERVICES);
        db.execSQL(CREATE_TABLE_BARBERS);
        db.execSQL(CREATE_TABLE_BARBER_SERVICES);
        db.execSQL(CREATE_TABLE_CLIENTS);
        db.execSQL(CREATE_TABLE_APPOINTMENTS);

    }

//    public void deleteDB (){
//        context.deleteDatabase(DATABASE_NAME);
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP_TABLE_BARBER_SERVICES = "DROP TABLE IF EXISTS " + TABLE_BARBER_SERVICES;

        String DROP_TABLE_BARBERS = "DROP TABLE IF EXISTS " + TABLE_BARBERS;

        String DROP_TABLE_SERVICES = "DROP TABLE IF EXISTS " + TABLE_SERVICES;

        String DROP_TABLE_CLIENTS = "DROP TABLE IF EXISTS " + TABLE_CLIENTS;

        String DROP_TABLE_APPOINTMENTS = "DROP TABLE IF EXISTS " + TABLE_APPOINTMENTS;


        db.execSQL(DROP_TABLE_SERVICES);
        db.execSQL(DROP_TABLE_BARBERS);
        db.execSQL(DROP_TABLE_BARBER_SERVICES);
        db.execSQL(DROP_TABLE_CLIENTS);
        db.execSQL(DROP_TABLE_APPOINTMENTS);

        onCreate(db);
    }


    ///SERVICES DB METHODS

    public void addService(@NonNull Service service) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICES_NAME, service.getName());
        values.put(COLUMN_SERVICES_DURATION, service.getDuration());
        values.put(COLUMN_SERVICES_PRICE, service.getPrice());

        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.insert(TABLE_SERVICES, null, values);

        if (result == -1){
            Toast.makeText(
                    context,
                    "No se pudo agregar el servicio. Intente de nuevo.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,
                    "El servicio se ha agregado correctamente",
                    Toast.LENGTH_SHORT).show();
        }
        database.close();

    }
    public void editService(String id,String name, String price, String duration) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICES_NAME, name);
        values.put(COLUMN_SERVICES_DURATION, price);
        values.put(COLUMN_SERVICES_PRICE, duration);


        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.update(TABLE_SERVICES,values,"_id=?",new String[]{id});

        if (result == -1){
            Toast.makeText(
                    context,
                    "No se pudo editar el servicio. Intente de nuevo.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,
                    "El servicio se ha editado correctamente",
                    Toast.LENGTH_SHORT).show();
        }
        database.close();

    }

    public ArrayList<Service> getServices () {
        String query = "SELECT * FROM " + TABLE_SERVICES ;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        ArrayList<Service> services = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()){
                Service service = new Service();

                service.setId(Integer.parseInt(cursor.getString(0)));
                service.setName(cursor.getString(1));
                int duration =(int)Float.parseFloat(cursor.getString(2));
                service.setDuration(duration);
                service.setPrice(Float.parseFloat(cursor.getString(3)));

                services.add(service);
            }

            cursor.close();
        } else {
            services = null;
        }

        database.close();

        return services;
    }

    public Service findServiceById (int id) {
        String query = "SELECT * FROM " + TABLE_SERVICES +
                " WHERE " + COLUMN_SERVICES_ID + " = " + "\"" + id + "\"";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        Service service = new Service();

        if (cursor.moveToFirst()) {
            service.setId(Integer.parseInt(cursor.getString(0)));
            service.setName(cursor.getString(1));
            int duration =(int)Float.parseFloat(cursor.getString(2));
            service.setDuration(duration);
            service.setPrice(Float.parseFloat(cursor.getString(3)));

            cursor.close();
        } else {
            service = null;

        }

        database.close();

        return service;
    }

    public boolean deleteService(int id) {
        boolean result = false;

        Service service = findServiceById(id);

        if (service != null) {


            String query = "DELETE FROM " + TABLE_SERVICES +
                    " WHERE " + COLUMN_SERVICES_ID + " = " +
                    "\"" + id + "\"";
            SQLiteDatabase database = this.getWritableDatabase();
            database.acquireReference();

            database.execSQL(query);
            database.close();

            result = true;
        }

        return result;
    }


    /// BARBERS DB METHODS

    public void addBarber(Barber barber) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_BARBERS_NAME, barber.getName());

        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.insert(TABLE_BARBERS, null, values);

        if (result == -1){
            Toast.makeText(
                    context,
                    "No se pudo agregar el barbero. Intente de nuevo.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,
                    "El barbero se ha agregado correctamente",
                    Toast.LENGTH_SHORT).show();
        }
        database.close();

    }

    public void editBarber(String id,String name) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_BARBERS_NAME, name);

        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.update(TABLE_BARBERS,values,"_id=?",new String[]{id});

        if (result == -1){
            Toast.makeText(
                    context,
                    "No se pudo editar los datos del barbero. Intente de nuevo.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,
                    "El barbero se ha editado correctamente",
                    Toast.LENGTH_SHORT).show();
        }
        database.close();

    }

    public ArrayList<Barber> getBarbers () {
        String query = "SELECT * FROM " + TABLE_BARBERS;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        ArrayList<Barber> barbers = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()){
                Barber barber = new Barber();

                barber.setId(Integer.parseInt(cursor.getString(0)));
                barber.setName(cursor.getString(1));

                barbers.add(barber);
            }

            cursor.close();
        } else {
            barbers = null;
        }

        database.close();

        return barbers;
    }

    public Barber findBarberById (int id) {
        String query = "SELECT * FROM " + TABLE_BARBERS +
                " WHERE " + COLUMN_BARBERS_ID + " = " + "\"" + id + "\"";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        Barber barber = new Barber();

        if (cursor.moveToFirst()) {
            barber.setId(Integer.parseInt(cursor.getString(0)));
            barber.setName(cursor.getString(1));

            cursor.close();
        } else {
            barber = null;
        }

        database.close();

        return barber;
    }

    public boolean deleteBarber(int id) {
        boolean result = false;

        Barber barber = findBarberById(id);

        if (barber != null) {

            String query = "DELETE FROM " + TABLE_BARBERS +
                    " WHERE " + COLUMN_BARBERS_ID + " = " +
                    "\"" + id + "\"";
            SQLiteDatabase database = this.getWritableDatabase();
            database.acquireReference();

            database.execSQL(query);
            database.close();

            result = true;
        }

        return result;
    }




    /// CLIENTS DB METHODS

    public void addClient(Client client) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLIENTS_FIRST_NAME, client.getFirstName());
        values.put(COLUMN_CLIENTS_LAST_NAME, client.getLastName());
        values.put(COLUMN_CLIENTS_PHONE, client.getPhone());
        values.put(COLUMN_CLIENTS_DISTINGUISHED, client.isDistiguished());
        values.put(COLUMN_CLIENTS_LAST_VISIT, client.getLastVisit());
        values.put(COLUMN_CLIENTS_AMOUNT_VISITS, client.getAmountVisits());

        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.insert(TABLE_CLIENTS, null, values);

        if (result == -1){
            Toast.makeText(
                    context,
                    "No se pudo agregar el cliente. Intente de nuevo.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,
                    "El cliente se ha agregado correctamente",
                    Toast.LENGTH_SHORT).show();
        }
        database.close();

    }

    public void editClient(String id,String firstName,String lastName,String phone,String distinguished, String lastVisit, String amountVisits) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLIENTS_FIRST_NAME, firstName);
        values.put(COLUMN_CLIENTS_LAST_NAME, lastName);
        values.put(COLUMN_CLIENTS_PHONE, phone);
        values.put(COLUMN_CLIENTS_DISTINGUISHED, distinguished);
        values.put(COLUMN_CLIENTS_LAST_VISIT, lastVisit);
        values.put(COLUMN_CLIENTS_AMOUNT_VISITS, amountVisits);

        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.update(TABLE_CLIENTS,values,"_id=?",new String[]{id});

        if (result == -1){
            Toast.makeText(
                    context,
                    "No se pudo editar el cliente. Intente de nuevo.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,
                    "El cliente se ha editado correctamente",
                    Toast.LENGTH_SHORT).show();
        }
        database.close();

    }

    public ArrayList<Client> getClients () {
        String query = "SELECT * FROM " + TABLE_CLIENTS;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        ArrayList<Client> clients = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()){
                Client client = new Client();

                client.setId(Integer.parseInt(cursor.getString(0)));
                client.setFirstName(cursor.getString(1));
                client.setLastName(cursor.getString(2));
                int phone =(int) Float.parseFloat(cursor.getString(3));
                client.setPhone(phone);
                client.setDistiguished(Integer.parseInt(cursor.getString(4)));
                client.setLastVisit(cursor.getString(5));
                client.setAmountVisits(Integer.parseInt(cursor.getString(6)));

                clients.add(client);
            }

            cursor.close();
        } else {
            clients = null;
        }

        database.close();

        return clients;
    }

    public Client findClientById (int id) {
        String query = "SELECT * FROM " + TABLE_CLIENTS +
                " WHERE " + COLUMN_CLIENTS_ID + " = " + "\"" + id + "\"";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        Client client = new Client();

        if (cursor.moveToFirst()) {
            client.setId(Integer.parseInt(cursor.getString(0)));
            client.setFirstName(cursor.getString(1));
            client.setLastName(cursor.getString(2));
            int phone =(int) Float.parseFloat(cursor.getString(3));
            client.setPhone(phone);
            client.setDistiguished(Integer.parseInt(cursor.getString(4)));
            client.setLastVisit(cursor.getString(5));
            client.setAmountVisits(Integer.parseInt(cursor.getString(6)));

            cursor.close();
        } else {
            client = null;
        }

        database.close();

        return client;
    }

    public boolean deleteClient(int id) {
        boolean result = false;

        Client client = findClientById(id);

        if (client != null) {

            String query = "DELETE FROM " + TABLE_CLIENTS +
                    " WHERE " + COLUMN_CLIENTS_ID + " = " +
                    "\"" + id + "\"";
            SQLiteDatabase database = this.getWritableDatabase();
            database.acquireReference();

            database.execSQL(query);
            database.close();

            result = true;
        }

        return result;
    }

    // APPOINTMENTS METHODS

    public void addAppointment(@NonNull Appointment appointment) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_AP_CLIENT_ID, appointment.getClientId());
        values.put(COLUMN_AP_SERVICE_ID, appointment.getServiceId());
        values.put(COLUMN_AP_BARBER_ID, appointment.getBarberId());
        values.put(COLUMN_AP_DATE, appointment.getDate());
        values.put(COLUMN_AP_TIMESLOT,appointment.getTimeSlot());

        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.insert(TABLE_APPOINTMENTS, null, values);

        if (result == -1){
            Toast.makeText(
                    context,
                    "No se pudo agregar la cita. Intente de nuevo.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,
                    "La cita se ha agregado correctamente",
                    Toast.LENGTH_SHORT).show();
        }
        database.close();

    }
    public void editAppointment(String id,String clientId, String serviceId, String barberId) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_AP_CLIENT_ID, clientId);
        values.put(COLUMN_AP_SERVICE_ID, serviceId);
        values.put(COLUMN_AP_BARBER_ID, barberId);


        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.update(TABLE_APPOINTMENTS,values,"_id=?",new String[]{id});

        if (result == -1){
            Toast.makeText(
                    context,
                    "No se pudo editar el servicio. Intente de nuevo.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,
                    "El servicio se ha editado correctamente",
                    Toast.LENGTH_SHORT).show();
        }
        database.close();

    }

    public ArrayList<Appointment> getAppointment () {
        String query = "SELECT * FROM " + TABLE_APPOINTMENTS ;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        ArrayList<Appointment> appointments = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()){
                Appointment appointment = new Appointment();

                appointment.setId(Integer.parseInt(cursor.getString(0)));
                appointment.setClientId(Integer.parseInt(cursor.getString(1)));
                appointment.setServiceId(Integer.parseInt(cursor.getString(2)));
                appointment.setBarberId(Integer.parseInt(cursor.getString(3)));
                appointment.setDate(cursor.getString(4));
                appointment.setTimeSlot(cursor.getString(5));

                appointments.add(appointment);
            }

            cursor.close();
        } else {
            appointments = null;
        }

        database.close();

        return appointments;
    }

    public Appointment findAppointmentById (int id) {
        String query = "SELECT * FROM " + TABLE_APPOINTMENTS +
                " WHERE " + COLUMN_AP_ID + " = " + "\"" + id + "\"";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        Appointment appointment = new Appointment();

        if (cursor.moveToFirst()) {
            appointment.setId(Integer.parseInt(cursor.getString(0)));
            appointment.setClientId(Integer.parseInt(cursor.getString(1)));
            appointment.setServiceId(Integer.parseInt(cursor.getString(2)));
            appointment.setBarberId(Integer.parseInt(cursor.getString(3)));
            appointment.setDate(cursor.getString(4));
            appointment.setTimeSlot(cursor.getString(5));

            cursor.close();
        } else {
            appointment = null;

        }

        database.close();

        return appointment;
    }

    public boolean deleteAppointment(int id) {
        boolean result = false;

        Appointment appointment = findAppointmentById(id);

        if (appointment != null) {


            String query = "DELETE FROM " + TABLE_APPOINTMENTS +
                    " WHERE " + COLUMN_AP_ID + " = " +
                    "\"" + id + "\"";
            SQLiteDatabase database = this.getWritableDatabase();
            database.acquireReference();

            database.execSQL(query);
            database.close();

            result = true;
        }

        return result;
    }


}
