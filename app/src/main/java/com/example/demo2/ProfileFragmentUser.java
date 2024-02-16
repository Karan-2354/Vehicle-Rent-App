package com.example.demo2;

import static android.content.Context.MODE_PRIVATE;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragmentUser extends Fragment {

    public static final String SHARED_PREFS = "sharedPrefs";
    ImageView imageView;
    TextInputEditText name,email,phoneNo,address;
    FloatingActionButton floatingActionButton;
    Button confirm;
    Uri uri = null;
    Profile dataclass;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    Uri uriImage;

    public ProfileFragmentUser() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_user, container, false);
        
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        imageView = view.findViewById(R.id.image_view);
        name=view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);
        phoneNo=view.findViewById(R.id.phoneno);
        address=view.findViewById(R.id.address);
        confirm=view.findViewById(R.id.update);
        floatingActionButton = view.findViewById(R.id.floating_action);

        String mobileNo = sharedPreferences.getString("mobileNumber","");

        phoneNo.setText(mobileNo);
        phoneNo.setEnabled(false);

        databaseReference=FirebaseDatabase.getInstance().getReference("USER").child(mobileNo);
        valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        dataclass = itemSnapshot.getValue(Profile.class);
                        name.setText(dataclass.getName());
                        email.setText(dataclass.getEmail());
                        address.setText(dataclass.getAddress());
                       // uri = Uri.parse(dataclass.getPhoto());
                       // imageView.setImageURI(dataclass.getPhoto());
                        Glide.with(getContext())
                                .load(dataclass.getPhoto())
                                .into(imageView);

                        uriImage = Uri.parse(dataclass.getPhoto());
                        uri = uriImage;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        floatingActionButton.setOnClickListener(v -> ImagePicker.with(ProfileFragmentUser.this).crop().compress(1024).maxResultSize(1080, 1080).start());

        imageView.setOnClickListener(v -> {
            // Create a dialog to show the enlarged image
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            // Create a parent layout to hold the ImageView
            LinearLayout parentLayout = new LinearLayout(getContext());
            parentLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            parentLayout.setGravity(Gravity.CENTER);

            // Create the ImageView and set its image resource or URI
            ImageView enlargedImageView = new ImageView(getContext());
            enlargedImageView.setLayoutParams(new LinearLayout.LayoutParams(
                    1000, // Set width in pixels
                    1000  // Set height in pixels
            ));
            if (uriImage == null) {
                enlargedImageView.setImageResource(R.drawable.person1);
                //imageView.setImageResource(R.drawable.person1);
            } else {
                enlargedImageView.setImageURI(uriImage);
            }

            // Add the ImageView to the parent layout
            parentLayout.addView(enlargedImageView);

            // Set the parent layout as the view for the dialog
            builder.setView(parentLayout);

            AlertDialog dialog = builder.create();
            dialog.show();

        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=name.getText().toString();
                String Email=email.getText().toString();
                String Phone=phoneNo.getText().toString();
                String Address=address.getText().toString();
                String Image=uri.toString();
                if(Name.isEmpty() || Email.isEmpty() || Phone.isEmpty() || Address.isEmpty() || Image.isEmpty())
                {

                    Toast.makeText(getContext(),"Please fill the required detail",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    save();
                    name.setText(Name);
                    email.setText(Email);
                    phoneNo.setText(Phone);
                    address.setText(Address);
                    Glide.with(getContext())
                            .load(Image)
                            .into(imageView);
                }

            }
        });
        return view;
    }
    public void save()
    {
        String uName=name.getText().toString();
        String uEmail=email.getText().toString();
        String uPhone=phoneNo.getText().toString();
        String uAddress=address.getText().toString();
        String uImage=null;
        if(uri!=null) {
            uImage = uri.toString();
        }

        Profile user=new Profile(uName,uImage,uEmail,uPhone,uAddress);
        FirebaseDatabase.getInstance().getReference("USER").child(uPhone).child("Profile")
                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(),"Saved Successful",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uri = data.getData();
        if (uri!=null) {
            imageView.setImageURI(uri);
        }
    }
}