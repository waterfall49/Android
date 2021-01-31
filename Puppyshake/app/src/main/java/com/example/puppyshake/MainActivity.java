package com.example.puppyshake;

import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.view.animation.*;

public class MainActivity extends AppCompatActivity {

    Button btnShake;
    ImageView imageView;
    Integer[] imageId = {R.drawable.puppy};
    Animation shakeAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShake = findViewById(R.id.btnShake);
        imageView = findViewById(R.id.imageViewShake);

        btnShake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageResource(imageId[0]);
                puppyShake();
                imageView.setVisibility(View.INVISIBLE);
            }
        });
    }//end onCreate

    public void puppyShake(){
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        imageView.startAnimation(shakeAnimation);
    }

}//end class
