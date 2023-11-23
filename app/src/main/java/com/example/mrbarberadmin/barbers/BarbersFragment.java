package com.example.mrbarberadmin.barbers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mrbarberadmin.MainActivity;
import com.example.mrbarberadmin.R;
import com.example.mrbarberadmin.clients.ClientsCustomAdapter;
import com.example.mrbarberadmin.db.DBHelper;
import com.example.mrbarberadmin.model.Barber;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class BarbersFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton add_barber_btn;
    MainActivity mainActivity;

    BarbersCustomAdapter barbersCustomAdapter;

    DBHelper dbHelper;
    ArrayList<Barber> barbers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_barbers, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_barbers);
        add_barber_btn = view.findViewById(R.id.add_barber_btn);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        mainActivity = (MainActivity)getActivity();

        reloadData();

        add_barber_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddBarbersActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    void reloadData(){
        dbHelper = new DBHelper(mainActivity);

        barbers = dbHelper.getBarbers();

        barbersCustomAdapter = new BarbersCustomAdapter(mainActivity,barbers);
        recyclerView.setAdapter(barbersCustomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL,false));
    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            RecyclerView.Adapter adapter= recyclerView.getAdapter();

            new AlertDialog.Builder(mainActivity)
                    .setTitle("Eliminar barbero")
                    .setMessage("¿Estás seguro que deseas eliminar este barbero?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int id = barbers.get(position).getId();

                            dbHelper.deleteBarber(id);

                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            adapter.notifyDataSetChanged();
                        }
                    }).show();
        }
    };
}