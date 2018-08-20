package com.example.a9726446.lab2brightidea;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean isLightOn = false;

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                toggleLight();
                v.performClick();
                //v.setOnTouchListener(null);
                Log.d("Listener", "TAP");
            }
            return true;
        }
    };

    private void toggleLight() {
        ImageView ivLight = (ImageView) findViewById(R.id.imgLight);
        //Comparing drawables doesn't work. Let's just take the cheat's way and record the state separately. :/

        /*Drawable dLight = ivLight.getDrawable();
        Drawable dLightOn = getDrawable(R.drawable.lightbulb_on);
        Drawable dLightOff = getDrawable(R.drawable.lightbulb_off);*/
        //if (ivLight.getDrawable() == getDrawable(R.drawable.lightbulb_on)) {
        if (isLightOn){
            //If light is on, turn it off!
            Log.d("LightSwitch","Turning lamp off...");
            isLightOn = false;
            ivLight.setImageDrawable(getDrawable(R.drawable.lightbulb_off));
            ivLight.setContentDescription(getString(R.string.light_Off));
        } else {
            //If light is off, turn it on!
            Log.d("LightSwitch","Turning lamp on...");
            isLightOn = true;
            ivLight.setImageDrawable(getDrawable(R.drawable.lightbulb_on));
            ivLight.setContentDescription(getString(R.string.light_On));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initaliseUI();
    }

    private void initaliseUI() {
        findViewById(R.id.imgLight).setOnTouchListener(touchListener);
    }
}
