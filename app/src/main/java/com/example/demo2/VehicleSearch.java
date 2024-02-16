package com.example.demo2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VehicleSearch extends AppCompatActivity {

    String Vehicle;
    ArrayList<String> Location;
    AutoCompleteTextView simpleAutoCompleteTextView;
    Button b1;
    DatabaseReference databaseReference, databaseReference1;
    ValueEventListener eventListener, eventListener1;
    List<String> adminNumber;
    String admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        simpleAutoCompleteTextView = findViewById(R.id.text1);
        b1=findViewById(R.id.searchButton1);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Vehicle = getIntent().getStringExtra("Vehicle");
        TextView txtVehicleName = findViewById(R.id.txtVehicleName);
        txtVehicleName.setText(Vehicle);

        adminNumber = new ArrayList<>();
        Location = new ArrayList<>();

        if (Vehicle.equals("Bicycle")) {
            databaseReference = FirebaseDatabase.getInstance().getReference("Admin");
            //dialog.show();
            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        admin = itemSnapshot.getKey();
                        adminNumber.add(admin);
                    }

                    for (String admin : adminNumber) {
                        databaseReference1 = databaseReference.child(admin).child("BICYCLE");
                        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                                    BicycleData dataclass = itemSnapshot.getValue(BicycleData.class);
                                    if (dataclass != null) {
                                        dataclass.setBicycleKey(itemSnapshot.getKey());
                                    }
                                    if(!Location.contains(dataclass.getBicycleLocation())) {
                                        Location.add(dataclass.getBicycleLocation());
                                    }
                                    //Log.e("Location",dataclass.getBicycleLocation());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //dialog.dismiss();
                }
            });
        } else if (Vehicle.equals("Scooter")) {
            List<ScooterData> vehicleDataList = new ArrayList<>();

            databaseReference = FirebaseDatabase.getInstance().getReference("Admin");
            //dialog.show();
            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        admin = itemSnapshot.getKey();
                        adminNumber.add(admin);
                    }

                    for(String admin : adminNumber){
                        databaseReference1 =databaseReference.child(admin).child("SCOOTER");
                        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                                    ScooterData dataclass = itemSnapshot.getValue(ScooterData.class);
                                    if (dataclass != null) {
                                        dataclass.setScooterKey(itemSnapshot.getKey());
                                    }
                                    if (!Location.contains(dataclass.getScooterLocation())) {
                                        Location.add(dataclass.getScooterLocation());
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        } else if (Vehicle.equals("Bike")) {

            List<BikeData> vehicleDataList = new ArrayList<>();

            databaseReference = FirebaseDatabase.getInstance().getReference("Admin");
            //dialog.show();
            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        admin = itemSnapshot.getKey();
                        adminNumber.add(admin);
                    }

                    for(String admin : adminNumber){
                        databaseReference1 =databaseReference.child(admin).child("BIKE");
                        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                                    BikeData dataclass = itemSnapshot.getValue(BikeData.class);
                                    if (dataclass != null) {
                                        dataclass.setBikeKey(itemSnapshot.getKey());
                                    }
                                    if (!Location.contains(dataclass.getBikeLocation())) {
                                        Location.add(dataclass.getBikeLocation());
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        } else if (Vehicle.equals("Car")) {

            List<CarData> vehicleDataList = new ArrayList<>();

            databaseReference = FirebaseDatabase.getInstance().getReference("Admin");
            //dialog.show();
            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        admin = itemSnapshot.getKey();
                        adminNumber.add(admin);
                    }

                    for(String admin : adminNumber){
                        databaseReference1 =databaseReference.child(admin).child("CAR");
                        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                                    CarData dataclass = itemSnapshot.getValue(CarData.class);
                                    if (dataclass != null) {
                                        dataclass.setCarKey(itemSnapshot.getKey());
                                    }
                                    if (!Location.contains(dataclass.getCarLocation())) {
                                        Location.add(dataclass.getCarLocation());
                                    }
                                }

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                    Log.d("CARS location",Location.toString());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //dialog.dismiss();
                }
            });
        }

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,Location);
        simpleAutoCompleteTextView.setAdapter(adapter);
        simpleAutoCompleteTextView.setThreshold(1);
        simpleAutoCompleteTextView.setAdapter(adapter);

        b1.setOnClickListener(v -> {
            String query = simpleAutoCompleteTextView.getText().toString();
            Intent intent = new Intent(VehicleSearch.this, VehicleDetailUser.class);
            intent.putExtra("location", query);
            intent.putExtra("vehicleName", Vehicle);
           startActivity(intent);
        });
    }
}