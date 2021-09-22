package com.example.maadprojectthree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {


    Button btnOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        btnOne=findViewById(R.id.button_one);

       btnOne.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent;
               intent = new Intent(MainActivity3.this , customerList.class);
               startActivity(intent);
           }
       });
    }
}