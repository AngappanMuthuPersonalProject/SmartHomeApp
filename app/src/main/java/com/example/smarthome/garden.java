package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.pawelkleczkowski.customgauge.CustomGauge;

public class garden extends AppCompatActivity {

    CustomGauge temperature,humidity,moisture;
    TextView temp,hum,moist;
    DatabaseReference GardenRoot;
    Button graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);

        temperature = (CustomGauge) findViewById(R.id.gauge_plant_temp);
        humidity = (CustomGauge) findViewById(R.id.gauge_plant_hum);
        moisture = (CustomGauge) findViewById(R.id.gauge_moisture);

        temp = (TextView) findViewById(R.id.txtplanttemp);
        hum = (TextView) findViewById(R.id.txtplanthum);
        moist = (TextView) findViewById(R.id.txtplantmoisture);

        graph = (Button) findViewById(R.id.btn_graph);

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i = new Intent(getApplicationContext(),garden_graph.class);
                startActivity(i);

            }
        });

        GardenRoot = FirebaseDatabase.getInstance().getReference().child("sensor").child("Garden");

        GardenRoot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int inth,intt,intmoi;
                String h = dataSnapshot.child("Humidity").child("H").getValue(String.class);
                String m = dataSnapshot.child("Moisture").child("M").getValue(String.class);
                String t = dataSnapshot.child("Temperature").child("C").getValue(String.class);

                if(!h.equals("nan") && !t.equals("nan")){
                    float te = Float.valueOf(t);
                    float hu = Float.valueOf(h);
                    float mo = Float.valueOf(m);

                    inth = (int)hu;
                    intt = (int)te;
                    intmoi = (int)mo;

                    temperature.setEndValue(100);
                    humidity.setEndValue(100);
                    moisture.setEndValue(100);
                    temperature.setPointSize(intt);
                    humidity.setPointSize(inth);
                    moisture.setPointSize(intmoi);


                    temp.setText(intt+"*C");
                    hum.setText(inth+"%");
                    moist.setText(intmoi+"%");
                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
