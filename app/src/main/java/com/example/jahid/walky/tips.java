package com.example.jahid.walky;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class tips extends Activity implements OnClickListener {
    int i=0;
    TextView t ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        t = (TextView)findViewById(R.id.tips);
        t.setOnClickListener(this);
    }

    public void onClick(View arg0) {
        i++;
        if(i==1)
             t.setText("Wear supportive footwear – wear low-heeled footwear with non-skid soles.     ---- Tap For Next Tip");
        if(i==2)
            t.setText("Avoid rushing – rushing increases your risk of falling. Take you time.     ---- Tap For Next Tip");
        if(i==3)
            t.setText("If using a walking aid (e.g. cane or walker) ensure that it is fitted for your height.     ---- Tap For Next Tip");
        if(i==4)
            t.setText("Check the Clean Air Peel website for the daily Air Quality Health Index (AQHI) and the Ultraviolet (UV) Index to plan outdoor activities accordingly and to protect your health or the health of seniors in your care.     ---- Tap For Next Tip");
        if(i==5)
            t.setText("Be extra careful in cold weather — sidewalks and paths can be slippery.     ---- Tap For Next Tip");
        if(i==6)
            t.setText("Cold weather can cause numbness and make it difficult for you to feel any pain or an injury. When it’s cold outside, consider walking in an indoor place, like a mall or community fitness centre.     ---- Tap For Next Tip");
        if(i==7)
            t.setText("Walk with friends or a walking club.     ---- Tap For Next Tip");
        if(i==8)
            t.setText("Carry a cell phone in case of emergencies.     ---- Tap For Next Tip");
        if(i==9)
            t.setText("Dress appropriately for the weather and drink plenty of water.    ---- Tap For Next Tip");
        if(i==10) {

            i=0;
            t.setText("Consider the surface you’ll be walking on. A smooth, soft surface that’s free of debris will put less strain on your joints and feet.     ---- Tap For Next Tip");
        }
}}

