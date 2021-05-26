package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class garden_graph extends AppCompatActivity {

    LineChart graph_hum,graph_temp,graph_moi;
    ArrayList<Entry> yDatah,yDatat,yDatam;
    DatabaseReference gardenRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_graph);

        graph_hum = (LineChart) findViewById(R.id.garden_graph_hum);
        graph_temp = (LineChart) findViewById(R.id.garden_graph_temp);
        graph_moi = (LineChart) findViewById(R.id.garden_graph_moi);

        gardenRef = FirebaseDatabase.getInstance().getReference().child("sensor").child("Garden");

        gardenRef.child("Humidity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                float SensorValue;
                yDatah = new ArrayList<>();
                float i =0;
                for (DataSnapshot ds : dataSnapshot.child("Log").getChildren()){
                    if(!ds.getValue(String.class).equals("nan")){
                        i=i+1;
                        String SV = ds.getValue(String.class);
                        SensorValue = Float.parseFloat(SV);
                        yDatah.add(new Entry(i,SensorValue));
                    }

                }

                final LineDataSet lineDataSet = new LineDataSet(yDatah,"Humidity");
                LineData data = new LineData(lineDataSet);
                graph_hum.setData(data);
                graph_hum.setScrollbarFadingEnabled(true);
                graph_hum.notifyDataSetChanged();
                graph_hum.invalidate();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        gardenRef.child("Temperature").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                float SensorValue;
                yDatat = new ArrayList<>();
                float i =0;
                for (DataSnapshot ds : dataSnapshot.child("Log").getChildren()){
                    if(!ds.getValue(String.class).equals("nan")) {
                        i = i + 1;
                        String SV = ds.getValue(String.class);
                        SensorValue = Float.parseFloat(SV);
                        yDatat.add(new Entry(i, SensorValue));
                    }
                }

                final LineDataSet lineDataSet = new LineDataSet(yDatat,"Temperature");
                LineData data = new LineData(lineDataSet);
                graph_temp.setData(data);
                graph_temp.setScrollbarFadingEnabled(true);
                graph_temp.notifyDataSetChanged();
                graph_temp.invalidate();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        gardenRef.child("Moisture").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                float SensorValue;
                yDatam = new ArrayList<>();
                float i =0;
                for (DataSnapshot ds : dataSnapshot.child("Log").getChildren()){
                    if(!ds.getValue(String.class).equals("nan")) {
                        i = i + 1;
                        String SV = ds.getValue(String.class);
                        SensorValue = Float.parseFloat(SV);
                        yDatam.add(new Entry(i, SensorValue));
                    }
                }

                final LineDataSet lineDataSet = new LineDataSet(yDatam,"Moisture");
                LineData data = new LineData(lineDataSet);
                graph_moi.setData(data);
                graph_moi.setScrollbarFadingEnabled(true);
                graph_moi.notifyDataSetChanged();
                graph_moi.invalidate();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
