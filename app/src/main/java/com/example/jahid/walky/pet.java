package com.example.jahid.walky;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import android.hardware.SensorEventListener;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by rubai on 10/29/2017.
 */

public class pet extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SensorEventListener{

    private SensorManager sensorManager;
    private TextView count;
    boolean activityRunning;
    ProgressBar prg ;
    TextView text;
    int flag=0;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_home);

        ImageView petanim = (ImageView) findViewById(R.id.imageViewPet);
        petanim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petanim();
                //Intent intent = new Intent(getApplicationContext(), petanim.class);
                //startActivity(intent);

            }
        });

       // TextView text= (TextView) findViewById(R.id.textView2);
        //text.setTextColor(0);
        //text.setText("health");
        //ProgressBar prg = (ProgressBar)findViewById(R.id.health);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

     //   count = (TextView) findViewById(R.id.count);
        prg = (ProgressBar)findViewById(R.id.health);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        prg.setProgress(00);
        text=(TextView)findViewById(R.id.textView3);
       // prg.setProgress(40);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_routes) {
            Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.signout) {
            Intent intent = new Intent(getApplicationContext(),GoogleSignOutActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_walking_tips) {
            Intent intent = new Intent(getApplicationContext(),tips.class);
            startActivity(intent);
        } else if (id == R.id.schedule) {
            Intent intent = new Intent(getApplicationContext(),Schedul_Time.class);
            startActivity(intent);
        }else if (id == R.id.add_location_menu) {
            Intent intent = new Intent(getApplicationContext(),AddLocationActivity.class);
            startActivity(intent);
        }
       // else if (id == R.id.steps) {
         //   Intent intent = new Intent(getApplicationContext(),StepCounter.class);
           // startActivity(intent);
        //}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    void petanim()
    {
        setContentView(R.layout.petanim);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), pet.class);
                startActivity(intent);
            }
        },38*80+100);

    }
    /*step sensor*/


    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            prg.setProgress(00);
           // Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(activityRunning) {

            float data=event.values[0];
            int temp;

            if(data>100) {
                data = data % 100;
                temp = (int) data;

                if(temp>=60)
                {
                    prg.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                    if(temp>=90)text.setText("Health: EXCELLENT!!!");
                        else text.setText("Health: GOOD!!");
                }
                else if(temp>=30)
                {
                    prg.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                    text.setText("Health: BAD!!");
                    flag=0;
                }
                else {

                    prg.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                    text.setText("Health: VERY BAD!!!");

                    if(flag<1) {
                        noti();
                        flag=1;

                    }
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            flag=0;
                            noti();
                        }
                    },60000);
                }
                    prg.setProgress(temp);

            }
            else
            {
                temp = (int)data;
                if(temp>=60)
                {
                    prg.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                    if(temp>=90)text.setText("Health: EXCELLENT!!!");
                    else text.setText("Health: GOOD!!");
                }
                else if(temp>=30)
                {
                    prg.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                    text.setText("Health: BAD!!");
                    flag=0;
                }
                else {

                    prg.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                    text.setText("Health: VERY BAD!!!");
                    if(flag<1)
                    {
                        noti();
                        flag=1;
                    }
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            flag=0;
                            noti();
                        }
                    },60000);
                }
                prg.setProgress(temp);
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void noti()
    {
        long v[]={500,1000};
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(pet.this,0,intent,0);
        Notification Noti=new Notification.Builder(pet.this)
                .setTicker("Your pet")
                .setVibrate(v)
                .setSound(uri)
                .setContentTitle("Your Pet")
                .setContentText("your pet's comdition is bad walk soon to improve its health")
                .setSmallIcon(R.drawable.app_logo)
                .setContentIntent(pendingIntent).getNotification();
        Noti.flags=Notification.FLAG_AUTO_CANCEL;
        NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0,Noti);


    }


}

