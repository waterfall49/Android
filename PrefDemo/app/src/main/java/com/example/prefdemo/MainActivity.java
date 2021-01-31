package com.example.prefdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.util.TypedValue;
import android.content.*;

public class MainActivity extends AppCompatActivity {

    private Button btnText;
    private SeekBar sbText;
    private EditText etText;
    private SharedPreferences prefs;
    private static final String FONT_SIZE_KEY = "fontsize";
    private static final String TEXT_VALUE_KEY = "textvalue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnText = findViewById(R.id.btnText);
        sbText = findViewById(R.id.sbText);
        etText = findViewById(R.id.etText);

        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = prefs.edit();
                editor.putFloat(FONT_SIZE_KEY,etText.getTextSize());
                editor.putString(TEXT_VALUE_KEY,etText.getText().toString());
                editor.commit();
                Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_LONG).show();

            }
        });
        prefs = getPreferences(MODE_PRIVATE);
        float fontSize = prefs.getFloat(FONT_SIZE_KEY, 10);
        sbText.setProgress((int)fontSize);
        etText.setText(prefs.getString(TEXT_VALUE_KEY,""));
        etText.setTextSize(TypedValue.COMPLEX_UNIT_PX, sbText.getProgress());
        // px to sp conversion

        sbText.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                etText.setTextSize(TypedValue.COMPLEX_UNIT_PX,progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }//end onCreate

}//end class
