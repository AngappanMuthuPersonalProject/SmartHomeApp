package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class connected_devices extends AppCompatActivity {

    List<String> deviceslist = new ArrayList<>();
    ListView lv;
    DatabaseReference connected_devicesRef;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected_devices);
        connected_devicesRef = FirebaseDatabase.getInstance().getReference().child("Connected_devices");



        connected_devicesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!deviceslist.isEmpty()){
                    deviceslist.clear();
                }

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String device_name = ds.getKey();
//                    Toast.makeText(getApplicationContext(),device_name,Toast.LENGTH_LONG).show();
                    deviceslist.add(device_name);
                    lv  = (ListView) findViewById(R.id.ListView);
                    adapter = new ArrayAdapter<String>(connected_devices.this,R.layout.support_simple_spinner_dropdown_item,deviceslist);
                    lv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
