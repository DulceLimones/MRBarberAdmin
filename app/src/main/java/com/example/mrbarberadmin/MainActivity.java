package com.example.mrbarberadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mrbarberadmin.appointments.AppointmentsFragment;
import com.example.mrbarberadmin.barbers.BarbersFragment;
import com.example.mrbarberadmin.clients.ClientsFragment;
import com.example.mrbarberadmin.services.ServicesFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,findViewById(R.id.toolbar),R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AppointmentsFragment()).commit();
            navigationView.setCheckedItem(R.id.menu_appointments);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_appointments:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AppointmentsFragment()).commit();
                break;
            case R.id.menu_barbers:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new BarbersFragment()).commit();
                break;
            case R.id.menu_services:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ServicesFragment()).commit();
                break;
            case R.id.menu_clients:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ClientsFragment()).commit();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return  true;
    }


}