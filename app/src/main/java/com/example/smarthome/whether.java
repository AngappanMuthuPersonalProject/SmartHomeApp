package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.EntityReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pl.pawelkleczkowski.customgauge.CustomGauge;

public class whether extends AppCompatActivity {

    CustomGauge temperature,humidity;
    DatabaseReference roomdht11;
    String strHum,strTemp;
    TextView temp,hum;
    int t,h;
    ArrayList<Entry> yData;
    ArrayList<Integer> inttemp = new ArrayList<Integer>();
    ArrayList<Integer> inthum = new ArrayList<Integer>();
    LineChart graph_temp,graph_hum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whether);

        temperature = (CustomGauge) findViewById(R.id.gauge_temp);
        humidity = (CustomGauge) findViewById(R.id.gauge_hum);

        temp = (TextView) findViewById(R.id.txttemp);
        hum = (TextView) findViewById(R.id.txthum);

        graph_temp = (LineChart) findViewById(R.id.graph_temp);
        graph_hum = (LineChart) findViewById(R.id.graph_hum);






        roomdht11 = FirebaseDatabase.getInstance().getReference().child("sensor").child("Room DHT11");

        roomdht11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                strHum = dataSnapshot.child("Humidity").child("H").getValue(String.class);
                strTemp = dataSnapshot.child("Temperature").child("C").getValue(String.class);

                float te = Float.valueOf(strTemp);
                float hu = Float.valueOf(strHum);

                h = (int)hu;
                t = (int)te;

                temperature.setEndValue(100);
                humidity.setEndValue(100);
                temperature.setPointSize(t);
                humidity.setPointSize(h);

                temp.setText(String.valueOf(temperature.getPointSize())+"*C");
                hum.setText(String.valueOf(humidity.getPointSize())+"%");

                inttemp.add(t);
                inthum.add(h);

//                insert_allYvalue(inttemp,tempYvalue);
//                insert_allYvalue(inthum,humYvalue);


                DatabaseReference df = dataSnapshot.child("Temperature").child("Log").getRef();
                DatabaseReference df1 = dataSnapshot.child("Humidity").child("Log").getRef();

                df.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        yData = new ArrayList<>();
                        float i =0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            i=i+1;
                            String SV = ds.getValue(String.class);
                            Float SensorValue = Float.parseFloat(SV);
                            yData.add(new Entry(i,SensorValue));
                        }
                        final LineDataSet lineDataSet = new LineDataSet(yData,"Temperature");
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

                df1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        yData = new ArrayList<>();
                        float i =0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            i=i+1;
                            String SV = ds.getValue(String.class);
                            Float SensorValue = Float.parseFloat(SV);
                            yData.add(new Entry(i,SensorValue));
                        }
                        final LineDataSet lineDataSet = new LineDataSet(yData,"Humidity");
                        LineData data = new LineData(lineDataSet);
                        graph_hum.setData(data);
                        graph_hum.notifyDataSetChanged();
                        graph_hum.invalidate();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    void insert_allYvalue(ArrayList<Integer> arrayList,ArrayList<Entry> y){
        for(int i =0;i<=arrayList.size();i++){
            y.add(new Entry(i,arrayList.get(i)));
        }
    }
}
