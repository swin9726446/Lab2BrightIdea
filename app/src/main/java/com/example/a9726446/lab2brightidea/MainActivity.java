package com.example.a9726446.lab2brightidea;

//import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.AndroidException;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isLightOn = false;

    private View.OnTouchListener touchListener = new View.OnTouchListener() {

        // Task 5
        // This is just a lab exercise, I'm okay with using a depreciated method for now.
        // GestureDetector gDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener()){
        GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener(){
            public void onLongPress(MotionEvent e){
                    toggleLight();
            }
        });

        /*private Context getContext() {
            try{
                return getApplicationContext();
            } catch (Exception e){
                Log.d("initUI", "Error producing Application Context:\n" + e.toString());
                return null;
            }
        }*/

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

    private void toggleLight() {
        ImageView ivLight = findViewById(R.id.imgLight);
        //Comparing drawables doesn't work. Let's just take the cheat's way and record the state separately. :/

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
        findViewById(R.id.imgLight).setOnTouchListener(touchListener);
        findViewById(R.id.txtLight).setOnTouchListener(touchListener);
        if (b == null) return; //move along, nothing to see here.

        //Flip the value, so you can then 'toggle' it back to what it should be! >_>
        isLightOn = !b.getBoolean("LightState");
        toggleLight();
    }
}
