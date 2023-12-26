package com.example.seproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsBooking extends AppCompatActivity {

    private TextView food, seating, date, time;

    private Button editBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_booking);

        editBtn = findViewById(R.id.editBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        food = findViewById(R.id.food);
        seating = findViewById(R.id.seating);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // For simplicity, you can replace these with your actual logic to get user choices
                String selectedFood = "Pizza";
                String selectedLunch = "Vegetarian";
                String selectedDate = "2023-01-01"; // Replace with actual date
                String selectedTime = "12:30 PM"; // Replace with actual time

                // Set the selected options in TextViews
                food.setText("Food: " + selectedFood);
                seating.setText("Lunch: " + selectedLunch);
                date.setText("Date: " + selectedDate);
                time.setText("Time: " + selectedTime);
            }
        });
    }
}