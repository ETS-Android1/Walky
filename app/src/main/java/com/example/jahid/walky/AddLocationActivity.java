package com.example.jahid.walky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddLocationActivity extends AppCompatActivity {

    String locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        final EditText locationNameEditText = (EditText) findViewById(R.id.location_name);
        final Button locationAddButton = (Button) findViewById(R.id.add_location);

        locationAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationName = locationNameEditText.getText().toString();
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("Place");
                
                Toast.makeText(getApplicationContext(),"Location added successfully",Toast.LENGTH_SHORT).show();

            }
        });


    }
}
