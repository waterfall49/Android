package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class lastActivity extends AppCompatActivity {

    int score;
    String userName;
    TextView tvResult;
    TextView tvThank;
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        tvThank = findViewById(R.id.tvThank);
        tvResult = findViewById(R.id.tvResult);
        btnRestart = findViewById(R.id.btnRestart);

        final Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        score = bundle.getInt("score");
        userName = bundle.getString("name");

        tvThank.setText("Thank you " + userName + " ♥");
        tvResult.setText(score + "/20");

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(lastActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("name", "");
                bundle.putInt("score", 0);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });

    }//end onCreate
}//end Class
