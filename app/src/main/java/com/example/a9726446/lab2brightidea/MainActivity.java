package com.example.a9726446.lab2brightidea;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isLightOn = false;

    //Isuru the sage, suggested make this it's own stored variable doodad.
    // This way it can be used several times.
    // ALSO! Context! There's applciation, view and etc etc context, so you should just have to pass the thing (see initUI())
    private View.OnTouchListener touchListener(final Context context){
      return new View.OnTouchListener() {

        // Task 5
        // This is just a lab exercise, I'm okay with using a depreciated method for now.

         GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener()){
        // GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener(){

            //TODO: Fix this following suggestions from Isuru.
             public void onLongPress(MotionEvent e){
                    toggleLight();
            }
        };

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // Tasks 2 - 4
            // if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
            //    toggleLight();
            //    Log.d("Listener", "TAP");
            // }
            gestureDetector.onTouchEvent(event);
            v.performClick();
            return true;
        }
      };
    }

    private void toggleLight() {
        ImageView ivLight = findViewById(R.id.imgLight);
        //Comparing drawables doesn't work. Let's just take the cheat's way and record the state separately. :/

        //And comparing drawables is virtually impossible. You actually do have to use an associated tag ahahaha.
        /*Drawable dLight = ivLight.getDrawable();
        Drawable dLightOn = getDrawable(R.drawable.lightbulb_on);
        Drawable dLightOff = getDrawable(R.drawable.lightbulb_off);*/
        //if (ivLight.getDrawable() == getDrawable(R.drawable.lightbulb_on)) { //Breakpoint went here.
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
        ((TextView)findViewById(R.id.txtLight)).setText((findViewById(R.id.imgLight)).getContentDescription());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initaliseUI(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle b){
        b.putBoolean("LightState", isLightOn);
        super.onSaveInstanceState(b);
    }

    private void initaliseUI(Bundle b) {
        //"This" is also a context! And it's what we're using so why not pass it along!
        findViewById(R.id.imgLight).setOnTouchListener(touchListener(this));
        findViewById(R.id.txtLight).setOnTouchListener(touchListener(this));
        if (b == null) return; //move along, nothing to see here.

        //Flip the value, so you can then 'toggle' it back to what it should be! >_>
        isLightOn = !b.getBoolean("LightState");
        toggleLight();
    }
}
