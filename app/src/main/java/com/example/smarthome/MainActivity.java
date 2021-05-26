package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference SwitchRoot,SensorRoot;
    Switch Srelay1,Srelay2,Srelay3,Srelay4,Srelay5,Srelay6,Srelay7,Srelay8;
    ImageView Irelay1,Irelay2,Irelay3,Irelay4,Irelay5,Irelay6,Irelay7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SwitchRoot = FirebaseDatabase.getInstance().getReference().child("SWITCHES");
        Srelay1 = (Switch) findViewById(R.id.relay1);
        Srelay2 = (Switch) findViewById(R.id.relay2);
        Srelay3 = (Switch) findViewById(R.id.relay3);
        Srelay4 = (Switch) findViewById(R.id.relay4);
        Srelay5 = (Switch) findViewById(R.id.relay5);
        Srelay6 = (Switch) findViewById(R.id.relay6);
        Srelay7 = (Switch) findViewById(R.id.relay7);

        Irelay1 = (ImageView) findViewById(R.id.irelay1);
        Irelay2 = (ImageView) findViewById(R.id.irelay2);
        Irelay3 = (ImageView) findViewById(R.id.irelay3);
        Irelay4 = (ImageView) findViewById(R.id.irelay4);
        Irelay5 = (ImageView) findViewById(R.id.irelay5);
        Irelay6 = (ImageView) findViewById(R.id.irelay6);
        Irelay7 = (ImageView) findViewById(R.id.irelay7);


        Srelay1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // The toggle is enabled
                    SwitchRoot.child("RELAY_1_STATUS").setValue("ON");
                } else {
                    // The toggle is disabled
                    SwitchRoot.child("RELAY_1_STATUS").setValue("OFF");
                }
            }
        });

        Srelay2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // The toggle is enabled
                    SwitchRoot.child("RELAY_2_STATUS").setValue("ON");
                } else {
                    // The toggle is disabled
                    SwitchRoot.child("RELAY_2_STATUS").setValue("OFF");
                }
            }
        });

        Srelay3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // The toggle is enabled
                    SwitchRoot.child("RELAY_3_STATUS").setValue("ON");
                } else {
                    // The toggle is disabled
                    SwitchRoot.child("RELAY_3_STATUS").setValue("OFF");
                }
            }
        });

        Srelay4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // The toggle is enabled
                    SwitchRoot.child("RELAY_4_STATUS").setValue("ON");
                } else {
                    // The toggle is disabled
                    SwitchRoot.child("RELAY_4_STATUS").setValue("OFF");
                }
            }
        });

        Srelay5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // The toggle is enabled
                    SwitchRoot.child("RELAY_5_STATUS").setValue("ON");
                } else {
                    // The toggle is disabled
                    SwitchRoot.child("RELAY_5_STATUS").setValue("OFF");
                }
            }
        });

        Srelay6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // The toggle is enabled
                    SwitchRoot.child("RELAY_6_STATUS").setValue("ON");
                } else {
                    // The toggle is disabled
                    SwitchRoot.child("RELAY_6_STATUS").setValue("OFF");
                }
            }
        });

        Srelay7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // The toggle is enabled
                    SwitchRoot.child("RELAY_7_STATUS").setValue("ON");
                } else {
                    // The toggle is disabled
                    SwitchRoot.child("RELAY_7_STATUS").setValue("OFF");
                }
            }
        });


        SwitchRoot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String relay1 = dataSnapshot.child("RELAY_1_STATUS").getValue(String.class);
                String relay2 = dataSnapshot.child("RELAY_2_STATUS").getValue(String.class);
                String relay3 = dataSnapshot.child("RELAY_3_STATUS").getValue(String.class);
                String relay4 = dataSnapshot.child("RELAY_4_STATUS").getValue(String.class);
                String relay5 = dataSnapshot.child("RELAY_5_STATUS").getValue(String.class);
                String relay6 = dataSnapshot.child("RELAY_6_STATUS").getValue(String.class);
                String relay7 = dataSnapshot.child("RELAY_7_STATUS").getValue(String.class);

                retriveSwitchData(Srelay1,relay1,Irelay1);
                retriveSwitchData(Srelay2,relay2,Irelay2);
                retriveSwitchData(Srelay3,relay3,Irelay3);
                retriveSwitchData(Srelay4,relay4,Irelay4);
                retriveSwitchData(Srelay5,relay5,Irelay5);
                retriveSwitchData(Srelay6,relay6,Irelay6);
                retriveSwitchData(Srelay7,relay7,Irelay7);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    void retriveSwitchData(Switch s, String r, ImageView i){
        if(r.equals("ON")){
            s.setChecked(true);
            i.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
            i.setBackgroundResource(R.drawable.circlebackgroundlightgreen);

        }else{
            s.setChecked(false);
            i.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
            i.setBackgroundResource(R.drawable.circlebackgroundred);
        }
    }


}
