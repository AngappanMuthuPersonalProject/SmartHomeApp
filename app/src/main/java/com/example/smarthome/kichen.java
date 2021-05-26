package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import pl.pawelkleczkowski.customgauge.CustomGauge;

public class kichen extends AppCompatActivity {

    LineChart graph_gas;
    ArrayList<Entry> yData;
    DatabaseReference roomdht11;
    CustomGauge air;
    TextView airTextView;
    float SensorValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kichen);
        graph_gas = (LineChart) findViewById(R.id.graph_air);
        roomdht11 = FirebaseDatabase.getInstance().getReference().child("sensor").child("Room DHT11");
        air = (CustomGauge) findViewById(R.id.gauge_air);
        airTextView = (TextView) findViewById(R.id.txtair);

        roomdht11.child("Air_Quality").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                yData = new ArrayList<>();
                float i =0;
                for (DataSnapshot ds : dataSnapshot.child("Log").getChildren()){
                    i=i+1;
                    String SV = ds.getValue(String.class);
                    SensorValue = Float.parseFloat(SV);
                    yData.add(new Entry(i,SensorValue));

                }

                air.setEndValue(1000);
                air.setPointSize((int)SensorValue);
                final LineDataSet lineDataSet = new LineDataSet(yData,"Air Quality");
                LineData data = new LineData(lineDataSet);
                graph_gas.setData(data);
                graph_gas.setScrollbarFadingEnabled(true);
                graph_gas.notifyDataSetChanged();
                graph_gas.invalidate();

                airTextView.setText(String.valueOf((int)SensorValue)+"%");



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
