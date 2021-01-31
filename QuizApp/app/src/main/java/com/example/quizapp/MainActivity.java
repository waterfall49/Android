package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    Button btnStart;
    String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        btnStart = findViewById(R.id.btnStart);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = etName.getText().toString();


                if(userName.equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, subActivity.class);
                    intent.putExtra("name", userName);
                    startActivity(intent);

                }
            }
        });

    }//end onCreate

}//end class
