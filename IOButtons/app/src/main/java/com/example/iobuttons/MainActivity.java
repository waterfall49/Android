package com.example.iobuttons;

import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;//file IO libraries
import android.content.*; //context
import android.content.res.*; //asset manager
//import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnInternalRead, btnInternalWrite, btnExternalRead, btnExternalWrite, btnTextExnternal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInternalRead = findViewById(R.id.btnInternalRead);
        btnInternalWrite = findViewById(R.id.btnInternalWrite);
        btnExternalRead = findViewById(R.id.btnExternalRead);
        btnExternalWrite = findViewById(R.id.btnExternalWrite);
        btnTextExnternal = findViewById(R.id.btnTestExternal);

        /* Checks if external storage is available for read and write */
//        public boolean isExternalStorageWritable() {
//            String state = Environment.getExternalStorageState();
//            if (Environment.MEDIA_MOUNTED.equals(state)) {
//                return true;
//            }
//            return false;
//        }
//
//        /* Checks if external storage is available to at least read */
//        public boolean isExternalStorageReadable() {
//            String state = Environment.getExternalStorageState();
//            if (Environment.MEDIA_MOUNTED.equals(state) ||
//                    Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
//                return true;
//            }
//            return false;
//        }


        btnTextExnternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Test R/W status external");
                System.out.println("isExternalStorageReadable = " + isExternalStorageReadable());
                System.out.println("IsExternalStorageWriteable =" + isExternalStorageWriteable());

            }

            private Boolean isExternalStorageWriteable() {
                String state = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    return true;
                }
                return false;
            };

            private Boolean isExternalStorageReadable() {
                String state = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state) ||
                        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                    return true;
                }
                return false;
            };

        });

        btnInternalRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Begin Internal read");
                String str = "";
                BufferedReader br = null;
                InputStream is = null;


                try {
                    is = getResources().openRawResource(R.raw.poetry);
                    br = new BufferedReader(new InputStreamReader(is));
                    System.out.println("Raw file is open");

                } catch (Exception e) {
                    System.out.println("Error opening file");
                }
                try {
                    while ((str = br.readLine()) != null) {
                        System.out.println(str);
                    }

                } catch (IOException e) {
                    System.out.println("Error reading file");
                }


//                System.out.println("Test Internal Read/Write status");
//                System.out.println("is");

            }
        });

        btnInternalWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Test Internal Write");
                String filename = "myfile";
                String s = "hello world";
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(s.getBytes());
                    outputStream.close();
                    System.out.println("Write");
                } catch (Exception e) {
                    System.out.println("Error opening file");
                }

            }
        });

    }//end OnCreate

}//end class
