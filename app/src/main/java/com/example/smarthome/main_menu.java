package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class main_menu extends AppCompatActivity {

    CardView home,whether,garden,kichen,connected_devices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        home = (CardView) findViewById(R.id.home_card);
        whether = (CardView) findViewById(R.id.whether_card);
        garden = (CardView) findViewById(R.id.garden_card);
        kichen = (CardView) findViewById(R.id.kichen_card);
        connected_devices = (CardView) findViewById(R.id.connected_device_card);

        onclick_cardView(home,MainActivity.class);
        onclick_cardView(whether,whether.class);
        onclick_cardView(garden,garden.class);
        onclick_cardView(kichen,kichen.class);
        onclick_cardView(connected_devices,connected_devices.class);

    }

    void onclick_cardView(CardView c, final Class a){
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),a);
                startActivity(i);
            }
        });
    }
}
