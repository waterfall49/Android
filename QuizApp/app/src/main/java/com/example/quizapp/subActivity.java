package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.view.*;
import java.io.*;//file IO libraries
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class subActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvDescription;
    TextView tvNumberOfQuiz;
    RadioButton rbOne;
    RadioButton rbTwo;
    RadioButton rbThree;
    RadioButton rbFour;
    Button btnNext;
    Button btnFinish;

    List<String> def = new ArrayList<String>();
    List<String> term = new ArrayList<String>();
    HashMap<String, String> myHashmap = new HashMap<String, String>();
    int score = 0;
    int numRemainQuiz;
    String answer;
    String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        rbOne = findViewById(R.id.rbOne);
        rbTwo = findViewById(R.id.rbTwo);
        rbThree = findViewById(R.id.rbThree);
        rbFour = findViewById(R.id.rbFour);
        btnNext = findViewById(R.id.btnNext);
        btnFinish = findViewById(R.id.btnFinish);
        tvNumberOfQuiz = findViewById(R.id.tvNumberOfQuiz);

        Intent intent = getIntent();
        userName = intent.getStringExtra("name");
        tvName.setText("Welcome! Awesome " + userName);

        /**
         * Read files
         */
        String str_def = "";
        BufferedReader br_def = null;
        InputStream is_def = null;

        String str_term = "";
        BufferedReader br_term = null;
        InputStream is_term = null;

        try {
            is_def = getResources().openRawResource(R.raw.definition);
            br_def = new BufferedReader(new InputStreamReader(is_def));
            System.out.println("Raw file is open");

        } catch (Exception e) {
            Log.println(Log.ERROR, "Error opening file", e.getMessage());
        }
        try {
            while ((str_def = br_def.readLine()) != null) {
                def.add(str_def);
            }

        } catch (IOException e) {
            Log.println(Log.ERROR, "Error reading file", e.getMessage());
        }

        try {
            is_term = getResources().openRawResource(R.raw.term);
            br_term = new BufferedReader(new InputStreamReader(is_term));
            System.out.println("Raw file is open");

        } catch (Exception e) {
            Log.println(Log.ERROR, "Error opening file", e.getMessage());
        }
        try {
            while ((str_term = br_term.readLine()) != null) {
                term.add(str_term);
            }//end while
        } catch (IOException e) {
            Log.println(Log.ERROR, "Error reading file", e.getMessage());
        }

        /**
         * Generate Hashmap
         */

        int i=0;
        for(i=0; i<def.size(); i++){
            myHashmap.put(def.get(i), term.get(i));
        }

        /**
         * Do quiz, call the method
         */

        makeQuiz();


        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RadioGroup rdGroup = (RadioGroup) findViewById(R.id.rdGroup);
                System.out.println(rdGroup.getCheckedRadioButtonId());

                try {
                    Button temp = findViewById(rdGroup.getCheckedRadioButtonId());
                    String temp_answer = temp.getText().toString();
                    if(temp_answer.equals(answer)){
                        Toast.makeText(getApplicationContext(), "Wow! It's correct!", Toast.LENGTH_SHORT).show();
                        score++; }
                    else {
                        Toast.makeText(getApplicationContext(), "Oh No! You got wrong answer", Toast.LENGTH_SHORT).show();
                    }
                    if(def.size() > 0){
                        rdGroup.clearCheck();
                        makeQuiz();
                    } else {
                        Toast.makeText(getApplicationContext(), "You finished! Click finish button!", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please choose your answer", Toast.LENGTH_SHORT).show();
                }
            }//end onClick
        });//end btnNext onClickListener

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(subActivity.this, lastActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", userName);
                bundle.putInt("score", score);
                intent.putExtra("bundle", bundle);
//                intent.putExtra("score", score);
                startActivity(intent);
            }
        });//end btnFinish

    }//end onCreate

    private void makeQuiz(){
        /**
         * Choose one of questions and display
         */

        numRemainQuiz = def.size();
        String remainQuiz = "Number of Remain Quiz is " + numRemainQuiz + "/20";
        tvNumberOfQuiz.setText(remainQuiz);

        Collections.shuffle(def);

        String question = def.get(0);
        tvDescription.setText(question);

        def.remove(0);

        /**
         * Make an array for choices and display
         */
        List<String> choice = new ArrayList<String>();
        answer = myHashmap.get(question);

        choice.add(answer);
        term.remove(answer);
        Collections.shuffle(term);
        choice.add(term.get(0));
        choice.add(term.get(1));
        choice.add(term.get(2));
        term.add(answer);

        Collections.shuffle(choice);

        rbOne.setText(choice.get(0));
        rbTwo.setText(choice.get(1));
        rbThree.setText(choice.get(2));
        rbFour.setText(choice.get(3));

    }

}//end class
