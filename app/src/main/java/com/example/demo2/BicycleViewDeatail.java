package com.example.demo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class BicycleViewDeatail extends AppCompatActivity {

    ImageView carView_image,increment,decrement;
    Button book;
    int count=1;
    int price=0;
    TextView carView_name,carView_desc,carView_location,carView_price,updateprice;
    String AdminNo,UserNo;
    TextView hour;
    Uri uri=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicycle_view_deatail);

        carView_image=findViewById(R.id.carview_image);
        carView_name=findViewById(R.id.carview_name);
        carView_desc=findViewById(R.id.carview_desc);
        carView_location=findViewById(R.id.carview_location);
        carView_price=findViewById(R.id.carview_rupees);
        increment=findViewById(R.id.incbtn);
        decrement=findViewById(R.id.decbtn);
        hour=findViewById(R.id.car_hour);
        updateprice=findViewById(R.id.update_price);
        book=findViewById(R.id.car_book);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            Glide.with(this).load(bundle.getString("Bicycle_Image")).into(carView_image);
            carView_name.setText(bundle.getString("Bicycle_Name"));
            carView_desc.setText(bundle.getString("Bicycle_Desc"));
            carView_location.setText(bundle.getString("Bicycle_Location"));
            carView_price.setText(bundle.getString("Bicycle_Price"));
            AdminNo=bundle.getString("AdminNo");
            UserNo=bundle.getString("UserNo");
            uri= Uri.parse(bundle.getString("Bicycle_Image"));
        }
        price= Integer.parseInt(carView_price.getText().toString());
        hour.setText(String.valueOf(count));
        updateprice.setText(String.valueOf(price));
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement.setEnabled(true);
                hour.setText(String.valueOf(++count));
                update();
            }

        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<2)
                {
                    decrement.setEnabled(false);
                }else {
                    decrement.setEnabled(true);
                    hour.setText(String.valueOf(--count));
                    update();
                }
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();

                Intent intent = new Intent(BicycleViewDeatail.this, HomeUser.class);
                intent.putExtra("booking","Booked");
                startActivity(intent);
                finish();

            }
        });
    }
    public void update()
    {
        updateprice.setText(String.valueOf((price*count)));
        // hour.getText().toString();
    }
    public void savedata()
    {
        String cname=carView_name.getText().toString();
        String chour=hour.getText().toString();
        String cprice=updateprice.getText().toString();
        //Uri uri=carView_image;
        String cimage=uri.toString();
        String date =  DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        BookingData car=new BookingData(AdminNo,UserNo,cname,chour,cprice , cimage);
        FirebaseDatabase.getInstance().getReference("Booking").child("BICYCLE").child(AdminNo).child(date)
                .setValue(car).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(BicycleViewDeatail.this,"Saved Successful",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BicycleViewDeatail.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
    }
}