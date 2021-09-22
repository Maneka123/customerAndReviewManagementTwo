package com.example.maadprojectthree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    EditText txtOne, txtTwo, txtFour, txtFive, txtThree;
    Button btnSave, btnShow, btnUpdate, btnDelete;
    DatabaseReference dbRef;
    Customer cust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOne = findViewById(R.id.one);
        txtTwo = findViewById(R.id.two);
        txtThree = findViewById(R.id.three);
        txtFour = findViewById(R.id.four);
        txtFive = findViewById(R.id.four);


        btnSave = findViewById(R.id.btnOne);
        btnShow = findViewById(R.id.btnTwo);
        btnUpdate = findViewById(R.id.btnThree);
        btnDelete = findViewById(R.id.btnFour);

        cust = new Customer();

        btnSave.setOnClickListener(v -> {
            // Code here executes on main thread after user presses button


            //FirebaseDatabase  dbRef=new FirebaseDatabase.getInstance("https://test-application-firebas-e4f41-default-rtdb.firebaseio.com/");
            dbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
            //dbRef=FirebaseDatabase.getInstance(com.google.firebase:firebase-database@@19.2.1:97);

            try {
                if (TextUtils.isEmpty(txtOne.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter first name", Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(txtTwo.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter last name", Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(txtThree.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter contact number", Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(txtFour.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter user name", Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(txtFive.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();

                else {
                    cust.setFirstName(txtOne.getText().toString().trim());
                    cust.setLastName(txtTwo.getText().toString().trim());
                    cust.setContactNumber(txtThree.getText().toString().trim());
                    cust.setUsername(txtFour.getText().toString().trim());
                    cust.setEmail(txtFive.getText().toString().trim());

                    dbRef.push().setValue(cust);
                    Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();
                }


            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "invalid number", Toast.LENGTH_SHORT).show();
            }


        });


        btnShow.setOnClickListener(v -> {


            //Get datasnapshot at your "users" root node
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Customer");
            ref.addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //Get map of users in datasnapshot
                            collectPhoneNumbers((Map<String,Object>) dataSnapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //handle databaseError
                        }

                        private void collectPhoneNumbers(Map<String,Object> customer) {

                            ArrayList<Long> phoneNumbers = new ArrayList<>();

                            //iterate through each user, ignoring their UID
                            for (Map.Entry<String, Object> entry : customer.entrySet()){

                                //Get user map
                                Map singleUser = (Map) entry.getValue();
                                //Get phone field and append to list
                                phoneNumbers.add((Long) singleUser.get("email"));
                            }

                            System.out.println(phoneNumbers.toString());
                        }



                    });


        });


    }
}

