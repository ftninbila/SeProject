package com.example.seproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChooseSeater extends AppCompatActivity {

    private ImageButton seat2, seat4, seat6, seat8, seat10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_seater);

        seat2 = findViewById(R.id.seat2);
        seat4 = findViewById(R.id.seat4);
        seat6 = findViewById(R.id.seat6);
        seat8 = findViewById(R.id.seat8);
        seat10 = findViewById(R.id.seat10);

        seat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Declare method
                seat2Actvity();
            }
        });

        seat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declare method
                seat4Activity();
            }
        });

        seat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declare method
                seat6Activity();

            }
        });

        seat8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declare method
                seat8Activity();

            }
        });

        seat10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declare method
                seat10Activity();

            }
        });
    }

    public void seat2Actvity() {
        Intent intentHome = new Intent(this, DateTimebooking.class);
        startActivity(intentHome);
    }

    public void seat4Activity() {
        Intent intentHome = new Intent(this, DateTimebooking.class);
        startActivity(intentHome);
    }

    public void seat6Activity() {
        Intent intentHome = new Intent(this, DateTimebooking.class);
        startActivity(intentHome);
    }

    public void seat8Activity() {
        Intent intentHome = new Intent(this, DateTimebooking.class);
        startActivity(intentHome);
    }

    public void seat10Activity() {
        Intent intentHome = new Intent(this, DateTimebooking.class);
        startActivity(intentHome);
    }
}