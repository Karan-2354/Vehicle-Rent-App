package com.example.demo2;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookingsFragmentUser extends Fragment {

    public static final String SHARED_PREFS = "sharedPrefs";
    RecyclerView recyclerView;
    List<BookingData> data1, data2, data;
    String mobileNumber;
    DatabaseReference databaseReference2, databaseReference0, databaseReference1, databaseReference3, databaseReference4;
    ValueEventListener eventListener2, eventListener0, eventListener1, eventListener3, eventListener4;
    String admin, vehicle;
    List<String> adminNumber, adminNumber1, vehicleCatagory;
    boolean data1fetched = false, data2fetched = false;
    int i = 0;

    public BookingsFragmentUser() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookings_user, container, false);

        recyclerView = view.findViewById(R.id.bookingRecycleView);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mobileNumber = sharedPreferences.getString("mobileNumber", "");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        data = new ArrayList<>();
//        data1 = new ArrayList<>();
//        data2 = new ArrayList<>();
        adminNumber = new ArrayList<>();
//        adminNumber1 = new ArrayList<>();
        vehicleCatagory = new ArrayList<>();

//        BookingAdapter adapter = new BookingAdapter(getContext(), data);
//        recyclerView.setAdapter(adapter);

        databaseReference0 = FirebaseDatabase.getInstance().getReference("Booking").child("BICYCLE");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Booking").child("CAR");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("Booking").child("SCOOTER");
        databaseReference4 = FirebaseDatabase.getInstance().getReference("Booking").child("BIKE");

        ReadData(new FirebaseCall() {
            @Override
            public void onCalledBack(List<BookingData> dataList) {
                //data.addAll(dataList);
                Log.d("Data1", dataList.toString());
                BookingAdapter adapter = new BookingAdapter(getContext(), dataList);
                recyclerView.setAdapter(adapter);
            }
        });

        Log.d("Data2", data.toString());
//
        return view;
    }

    private void ReadData(FirebaseCall firebaseCall) {

        //1
        eventListener0 = databaseReference0.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adminNumber.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        admin = itemSnapshot.getKey();
                        adminNumber.add(admin);
                    }
                    for (String admin : adminNumber) {
                        databaseReference1 = databaseReference0.child(admin);
                        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                                    BookingData dataclass = itemSnapshot.getValue(BookingData.class);
                                    if (dataclass != null) {
                                        dataclass.setKey(itemSnapshot.getKey());
                                        if (Objects.equals(dataclass.getCustomer(), mobileNumber)) {
                                            data.add(dataclass);
                                        }
                                    }
                                }
                                firebaseCall.onCalledBack(data);
//                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //databaseReference0.addValueEventListener(eventListener);

        //2
        eventListener2 = databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adminNumber.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        admin = itemSnapshot.getKey();
                        adminNumber.add(admin);
                    }
                    for (String admin : adminNumber) {
                        databaseReference1 = databaseReference2.child(admin);
                        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                                    BookingData dataclass = itemSnapshot.getValue(BookingData.class);
                                    if (dataclass != null) {
                                        dataclass.setKey(itemSnapshot.getKey());
                                        if (Objects.equals(dataclass.getCustomer(), mobileNumber)) {
                                            data.add(dataclass);
                                        }
                                    }
                                }
                                firebaseCall.onCalledBack(data);
//                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //3
        eventListener3 = databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adminNumber.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        admin = itemSnapshot.getKey();
                        adminNumber.add(admin);
                    }
                    for (String admin : adminNumber) {
                        databaseReference1 = databaseReference3.child(admin);
                        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                                    BookingData dataclass = itemSnapshot.getValue(BookingData.class);
                                    if (dataclass != null) {
                                        dataclass.setKey(itemSnapshot.getKey());
                                        if (Objects.equals(dataclass.getCustomer(), mobileNumber)) {
                                            data.add(dataclass);
                                        }
                                    }
                                }
                                firebaseCall.onCalledBack(data);
//                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //4
        eventListener4 = databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adminNumber.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        admin = itemSnapshot.getKey();
                        adminNumber.add(admin);
                    }
                    for (String admin : adminNumber) {
                        databaseReference1 = databaseReference4.child(admin);
                        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                                    BookingData dataclass = itemSnapshot.getValue(BookingData.class);
                                    if (dataclass != null) {
                                        dataclass.setKey(itemSnapshot.getKey());
                                        if (Objects.equals(dataclass.getCustomer(), mobileNumber)) {
                                            data.add(dataclass);
                                        }
                                    }
                                }
                                firebaseCall.onCalledBack(data);
//                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private interface FirebaseCall {
        void onCalledBack(List<BookingData> dataList);
    }
}