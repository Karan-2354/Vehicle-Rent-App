package com.example.demo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class VehicleDetailUser extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    String vehicleName, location, admin, user;
    List<String> adminNumber;
    List<BicycleData> bicycleData,arraylist;
    List<BikeData> bikeData;
    List<CarData> carData;
    List<ScooterData> scooterData;
    RecyclerView recyclerView;
    DatabaseReference databaseReference, databaseReference1;
    ValueEventListener eventListener, eventListener1;
    boolean flag1=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail_user);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        user  = sharedPreferences.getString("mobileNumber","");

        vehicleName = getIntent().getStringExtra("vehicleName");
        location = getIntent().getStringExtra("location");

        recyclerView = findViewById(R.id.recycleView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adminNumber = new ArrayList<>();
        bicycleData = new ArrayList<>();
       arraylist = new ArrayList<>();
       bikeData=new ArrayList<>();
       carData=new ArrayList<>();
       scooterData=new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Admin");

       if(vehicleName.equals("Bicycle")) {
           ReadData(new FirebaseCall() {

               @Override
               public void onCalledBack(List<BicycleData> dataList) {
                   Log.e("data", dataList.toString());
                   if (!dataList.isEmpty()) {
                       BicycleAdapterAdmin adapter = new BicycleAdapterAdmin(VehicleDetailUser.this, dataList);
                       recyclerView.setAdapter(adapter);
                   }
//                   else if (!flag1) {
//                       goToPreviousActivity();
//                       Toast.makeText(getApplicationContext(), "No Vehicles Available at this Location", Toast.LENGTH_SHORT).show();
//                   }
               }


               @Override
               public void flagValue(boolean flag) {
//                Log.e("Flag", String.valueOf(flag));
                   flag1 = flag;
//                Log.e("Flag1", String.valueOf(flag1));

               }
           });
       }
       //2
        if(vehicleName.equals("Bike")) {
            ReadData(new FirebaseCall1() {

                @Override
                public void onCalledBack(List<BikeData> dataList) {
                    Log.e("data", dataList.toString());
                    if (!dataList.isEmpty()) {
                       BikeAdapterAdmin adapter = new BikeAdapterAdmin(VehicleDetailUser.this, dataList);
                        recyclerView.setAdapter(adapter);
                    }
//                    else if (!flag1) {
//                        goToPreviousActivity();
//                        Toast.makeText(getApplicationContext(), "No Vehicles Available at this Location", Toast.LENGTH_SHORT).show();
//                    }
                }


                @Override
                public void flagValue(boolean flag) {
//                Log.e("Flag", String.valueOf(flag));
                    flag1 = flag;
//                Log.e("Flag1", String.valueOf(flag1));

                }
            });
        }
        //3
        if(vehicleName.equals("Car")) {
            ReadData(new FirebaseCall2() {

                @Override
                public void onCalledBack(List<CarData> dataList) {
                    Log.e("data", dataList.toString());
                    if (!dataList.isEmpty()) {
                       CarAdapterAdmin adapter = new CarAdapterAdmin(VehicleDetailUser.this, dataList);
                        recyclerView.setAdapter(adapter);
                    }
//                    else if (!flag1) {
//                        goToPreviousActivity();
//                        Toast.makeText(getApplicationContext(), "No Vehicles Available at this Location", Toast.LENGTH_SHORT).show();
//                    }
                }


                @Override
                public void flagValue(boolean flag) {
//                Log.e("Flag", String.valueOf(flag));
                    flag1 = flag;
//                Log.e("Flag1", String.valueOf(flag1));

                }
            });
        }
        //4
        if(vehicleName.equals("Scooter")) {
            ReadData(new FirebaseCall3() {

                @Override
                public void onCalledBack(List<ScooterData> dataList) {
                    Log.e("data", dataList.toString());
                    if (!dataList.isEmpty()) {
                        ScooterAdapterAdmin adapter = new ScooterAdapterAdmin(VehicleDetailUser.this, dataList);
                        recyclerView.setAdapter(adapter);
                    }
//                    else if (!flag1) {
//                        goToPreviousActivity();
//                        Toast.makeText(getApplicationContext(), "No Vehicles Available at this Location", Toast.LENGTH_SHORT).show();
//                    }
                }


                @Override
                public void flagValue(boolean flag) {
//                Log.e("Flag", String.valueOf(flag));
                    flag1 = flag;
//                Log.e("Flag1", String.valueOf(flag1));

                }
            });
        }

        /*if (vehicleName.equals("Bicycle")) {
            List<BicycleData> vehicleDataList = new ArrayList<>();

//            BicycleAdapterAdmin adapter = new BicycleAdapterAdmin(VehicleDetailUser.this, vehicleDataList);
//            recyclerView.setAdapter(adapter);

            databaseReference = FirebaseDatabase.getInstance().getReference("Admin");
            //dialog.show();
            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    adminNumber.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        admin = itemSnapshot.getKey();
                        adminNumber.add(admin);
                    }

                    for(String admin : adminNumber){
                        databaseReference1 =databaseReference.child(admin).child("BICYCLE");
                        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                                    BicycleData dataclass = itemSnapshot.getValue(BicycleData.class);
                                    if (dataclass != null) {
                                        dataclass.setUserNo(user);
                                        dataclass.setAdminNo(admin);
                                        dataclass.setBicycleKey(itemSnapshot.getKey());
                                    }
                                    if(dataclass.getBicycleLocation().trim().toLowerCase().equals(location.trim().toLowerCase())) {
                                        vehicleDataList.add(dataclass);
                                    }
                                }
                                if(!vehicleDataList.isEmpty()){
                                    BicycleAdapterAdmin adapter = new BicycleAdapterAdmin(VehicleDetailUser.this, vehicleDataList);
                                    recyclerView.setAdapter(adapter);
                                }else {
//                                    Intent intent = new Intent(VehicleDetailUser.this, VehicleSearch.class);
//                                    //intent.putExtra("Vehicle",vehicleName);
//                                    startActivity(intent);
//                                    finish();
                                    goToPreviousActivity();
                                    Toast.makeText(getApplicationContext(), "No Vehicles Available at this Location", Toast.LENGTH_SHORT).show();
                                }
                                //adapter.notifyDataSetChanged();
                                //dialog.dismiss();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                //dialog.dismiss();
                            }
                        });
                    }
                    //adapter.notifyDataSetChanged();
                    //dialog.dismiss();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //dialog.dismiss();
                }
            });
        } else if (vehicleName.equals("Scooter")) {
            List<ScooterData> vehicleDataList = new ArrayList<>();

//            ScooterAdapterAdmin adapter = new ScooterAdapterAdmin(VehicleDetailUser.this, vehicleDataList);
//            recyclerView.setAdapter(adapter);

            databaseReference = FirebaseDatabase.getInstance().getReference("Admin");
            //dialog.show();
            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    adminNumber.clear();
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
                                        dataclass.setUserNo(user);
                                        dataclass.setAdminNo(admin);
                                        dataclass.setScooterKey(itemSnapshot.getKey());
                                    }
                                    if(dataclass.getScooterLocation().trim().toLowerCase().equals(location.trim().toLowerCase())) {
                                        vehicleDataList.add(dataclass);
                                    }
                                }
                                if(!vehicleDataList.isEmpty()){
                                    ScooterAdapterAdmin adapter = new ScooterAdapterAdmin(VehicleDetailUser.this, vehicleDataList);
                                    recyclerView.setAdapter(adapter);
                                }else {
//                                    Intent intent = new Intent(VehicleDetailUser.this, VehicleSearch.class);
//                                    //intent.putExtra("Vehicle",vehicleName);
//                                    startActivity(intent);
//                                    finish();
                                    goToPreviousActivity();
                                    Toast.makeText(getApplicationContext(), "No Vehicles Available at this Location", Toast.LENGTH_SHORT).show();
                                }
//                                adapter.notifyDataSetChanged();
                                //dialog.dismiss();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                //dialog.dismiss();
                            }
                        });
                    }
//                    adapter.notifyDataSetChanged();
                    //dialog.dismiss();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //dialog.dismiss();
                }
            });
        } else if (vehicleName.equals("Bike")) {

            List<BikeData> vehicleDataList = new ArrayList<>();

//            BikeAdapterAdmin adapter = new BikeAdapterAdmin(VehicleDetailUser.this, vehicleDataList);
//            recyclerView.setAdapter(adapter);

            databaseReference = FirebaseDatabase.getInstance().getReference("Admin");
            //dialog.show();
            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    adminNumber.clear();
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
                                        dataclass.setUserNo(user);
                                        dataclass.setAdminNo(admin);
                                        dataclass.setBikeKey(itemSnapshot.getKey());
                                    }
                                    if(dataclass.getBikeLocation().trim().toLowerCase().equals(location.trim().toLowerCase())) {
                                        vehicleDataList.add(dataclass);
                                    }
                                }
                                if(!vehicleDataList.isEmpty()){
                                    BikeAdapterAdmin adapter = new BikeAdapterAdmin(VehicleDetailUser.this, vehicleDataList);
                                    recyclerView.setAdapter(adapter);
                                }else {
//                                    Intent intent = new Intent(VehicleDetailUser.this, VehicleSearch.class);
//                                    //intent.putExtra("Vehicle",vehicleName);
//                                    startActivity(intent);
//                                    finish();
                                    goToPreviousActivity();
                                    Toast.makeText(getApplicationContext(), "No Vehicles Available at this Location", Toast.LENGTH_SHORT).show();
                                }
                                //adapter.notifyDataSetChanged();
                                //dialog.dismiss();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                //dialog.dismiss();
                            }
                        });
                    }
//                    adapter.notifyDataSetChanged();
                    //dialog.dismiss();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //dialog.dismiss();
                }
            });
        } else if (vehicleName.equals("Car")) {

            List<CarData> vehicleDataList = new ArrayList<>();

//            CarAdapterAdmin adapter = new CarAdapterAdmin(VehicleDetailUser.this, vehicleDataList);
//            recyclerView.setAdapter(adapter);

            databaseReference = FirebaseDatabase.getInstance().getReference("Admin");
            //dialog.show();
            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    adminNumber.clear();
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
                                        dataclass.setUserNo(user);
                                        dataclass.setAdminNo(admin);
                                        dataclass.setCarKey(itemSnapshot.getKey());
                                    }
                                    if(dataclass.getCarLocation().trim().toLowerCase().equals(location.trim().toLowerCase())) {
                                        vehicleDataList.add(dataclass);
                                    }
                                }
                                if(!vehicleDataList.isEmpty()){
                                    CarAdapterAdmin adapter = new CarAdapterAdmin(VehicleDetailUser.this, vehicleDataList);
                                    recyclerView.setAdapter(adapter);
                                }else {
//                                    Intent intent = new Intent(VehicleDetailUser.this, VehicleSearch.class);
//                                    //intent.putExtra("Vehicle",vehicleName);
//                                    startActivity(intent);
//                                    finish();
                                    goToPreviousActivity();
                                    Toast.makeText(getApplicationContext(), "No Vehicles Available at this Location", Toast.LENGTH_SHORT).show();
                                }
                                //adapter.notifyDataSetChanged();
                                //dialog.dismiss();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                //dialog.dismiss();
                            }
                        });
                    }
                    //adapter.notifyDataSetChanged();
                    //dialog.dismiss();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //dialog.dismiss();
                }
            });
        }*/
    }

    private void ReadData(FirebaseCall firebaseCall) {
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adminNumber.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        admin = itemSnapshot.getKey();
                        adminNumber.add(admin);
                    }
                    for (String admin : adminNumber) {
                        databaseReference1 = databaseReference.child(admin).child(vehicleName.toUpperCase());
                        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                                    //VehicleData dataclass = itemSnapshot.getValue(VehicleData.class);
                                    BicycleData dataclass = itemSnapshot.getValue(BicycleData.class);
                                    if (dataclass != null) {
                                        dataclass.setUserNo(user);
                                        dataclass.setAdminNo(admin);
                                        dataclass.setBicycleKey(itemSnapshot.getKey());
                                    }
                                    if(dataclass.getBicycleLocation().trim().toLowerCase().contains(location.trim().toLowerCase())) {
                                        bicycleData.add(dataclass);
                                        //Log.e("photo",dataclass.getBicycleImage());
                                    }
                                }
                                firebaseCall.onCalledBack(bicycleData);
                                firebaseCall.flagValue(false);
//                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    firebaseCall.flagValue(true);
                  // firebaseCall.onCalledBack(bicycleData);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //2
    private void ReadData(FirebaseCall1 firebaseCall) {
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adminNumber.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        admin = itemSnapshot.getKey();
                        adminNumber.add(admin);
                    }
                    for (String admin : adminNumber) {
                        databaseReference1 = databaseReference.child(admin).child(vehicleName.toUpperCase());
                        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                                    //VehicleData dataclass = itemSnapshot.getValue(VehicleData.class);
                                    BikeData dataclass = itemSnapshot.getValue(BikeData.class);
                                    if (dataclass != null) {
                                        dataclass.setUserNo(user);
                                        dataclass.setAdminNo(admin);
                                        dataclass.setBikeKey(itemSnapshot.getKey());
                                    }
                                    if(dataclass.getBikeLocation().trim().toLowerCase().contains(location.trim().toLowerCase())) {
                                        bikeData.add(dataclass);
                                        //Log.e("photo",dataclass.getBicycleImage());
                                    }
                                }
                                firebaseCall.onCalledBack(bikeData);
                                firebaseCall.flagValue(false);
//                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    firebaseCall.flagValue(true);
                    // firebaseCall.onCalledBack(bicycleData);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //3
    private void ReadData(FirebaseCall2 firebaseCall) {
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adminNumber.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        admin = itemSnapshot.getKey();
                        adminNumber.add(admin);
                    }
                    for (String admin : adminNumber) {
                        databaseReference1 = databaseReference.child(admin).child(vehicleName.toUpperCase());
                        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                                    //VehicleData dataclass = itemSnapshot.getValue(VehicleData.class);
                                   CarData dataclass = itemSnapshot.getValue(CarData.class);
                                    if (dataclass != null) {
                                        dataclass.setUserNo(user);
                                        dataclass.setAdminNo(admin);
                                        dataclass.setCarKey(itemSnapshot.getKey());
                                    }
                                    if(dataclass.getCarLocation().trim().toLowerCase().contains(location.trim().toLowerCase())) {
                                       carData.add(dataclass);
                                        //Log.e("photo",dataclass.getBicycleImage());
                                    }
                                }
                                firebaseCall.onCalledBack(carData);
                                firebaseCall.flagValue(false);
//                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    firebaseCall.flagValue(true);
                    // firebaseCall.onCalledBack(bicycleData);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //4
    private void ReadData(FirebaseCall3 firebaseCall) {
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adminNumber.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        admin = itemSnapshot.getKey();
                        adminNumber.add(admin);
                    }
                    for (String admin : adminNumber) {
                        databaseReference1 = databaseReference.child(admin).child(vehicleName.toUpperCase());
                        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                                    //VehicleData dataclass = itemSnapshot.getValue(VehicleData.class);
                                    ScooterData dataclass = itemSnapshot.getValue(ScooterData.class);
                                    if (dataclass != null) {
                                        dataclass.setUserNo(user);
                                        dataclass.setAdminNo(admin);
                                        dataclass.setScooterKey(itemSnapshot.getKey());
                                    }
                                    if(dataclass.getScooterLocation().trim().toLowerCase().contains(location.trim().toLowerCase())) {
                                        scooterData.add(dataclass);
                                        //Log.e("photo",dataclass.getBicycleImage());
                                    }
                                }
                                firebaseCall.onCalledBack(scooterData);
                                firebaseCall.flagValue(false);
//                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    firebaseCall.flagValue(true);
                    // firebaseCall.onCalledBack(bicycleData);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void goToPreviousActivity() {
        onBackPressed(); // This will close the current activity and return to the previous one
        // finish();
    }

    private interface FirebaseCall {
        void onCalledBack(List<BicycleData> dataList);
        void flagValue(boolean flag);
    }
    private interface FirebaseCall1 {
        void onCalledBack(List<BikeData> dataList);
        void flagValue(boolean flag);
    }
    private interface FirebaseCall2 {
        void onCalledBack(List<CarData> dataList);
        void flagValue(boolean flag);
    }
    private interface FirebaseCall3 {
        void onCalledBack(List<ScooterData> dataList);
        void flagValue(boolean flag);
    }
}