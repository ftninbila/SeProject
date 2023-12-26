package com.example.seproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText nameWrong, emailWrong, password;

    private Button register, cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Find views
        password = findViewById(R.id.password);
        nameWrong = findViewById(R.id.nameWrong);
        emailWrong = findViewById(R.id.emailWrong);

        // Register button click listener
        register = findViewById(R.id.register);
        cancel = findViewById(R.id.cancel);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname = nameWrong.getText().toString();
                String uemail = emailWrong.getText().toString();
                String upass = password.getText().toString();

                if (validateInput(uname, uemail, upass)) {
                    // Store user data into the local database
                    if (storeUserData(uname, uemail, upass)) {
                        // Show a toast message
                        Toast.makeText(Register.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                        // Redirect to the login page
                        redirectToLoginPage();
                    } else {
                        // Show an error message if data already exists
                        Toast.makeText(Register.this, "Your name or email already exists!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to navigate to the LoginActivity
                Intent intent = new Intent(Register.this, MainActivity.class);

                // Start the LoginActivity
                startActivity(intent);
            }
        });
    }

    private boolean validateInput(String uname, String uemail, String upass ) {
        boolean isValid = true;

        if (uname.isEmpty()) {
            nameWrong.setText("Cannot leave blank");
            isValid = false;
        }

        if (uemail.isEmpty()) {
            emailWrong.setText("Cannot leave blank");
            isValid = false;
        }

        if (upass.isEmpty()) {
            password.setText("Cannot leave blank");
            isValid = false;
        }


        // Check if email, username, or phone already exists in the database
        if (isUserExists(uname)) {
            nameWrong.setText("Your name already exists!");
            isValid = false;
        }

        if (isEmailExists(uemail)) {
            emailWrong.setText("Email already exists!");
            isValid = false;
        }

        return isValid;
    }

    private boolean isUserExists(String fullname) {
        // Get a readable database
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Define a projection that specifies which columns to query
        String[] projection = {DatabaseHelper.COLUMN_FULLNAME};

        // Define the selection criteria
        String selection = DatabaseHelper.COLUMN_FULLNAME + " = ? ";
        String[] selectionArgs = {fullname};

        // Perform the query
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USER,   // The table to query
                projection,                  // The array of columns to return (null to return all)
                selection,                   // The columns for the WHERE clause
                selectionArgs,               // The values for the WHERE clause
                null,                        // don't group the rows
                null,                        // don't filter by row groups
                null                         // don't sort order
        );

        // Check if the cursor has any rows
        boolean userExists = cursor.moveToFirst();

        // Close the cursor and database
        cursor.close();
        db.close();

        return userExists;
    }

    private boolean isEmailExists(String email) {
        // Get a readable database
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Define a projection that specifies which columns to query
        String[] projection = {DatabaseHelper.COLUMN_EMAIL};

        // Define the selection criteria
        String selection = DatabaseHelper.COLUMN_EMAIL + " = ? ";
        String[] selectionArgs = {email};

        // Perform the query
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USER,   // The table to query
                projection,                  // The array of columns to return (null to return all)
                selection,                   // The columns for the WHERE clause
                selectionArgs,               // The values for the WHERE clause
                null,                        // don't group the rows
                null,                        // don't filter by row groups
                null                         // don't sort order
        );

        // Check if the cursor has any rows
        boolean emailExists = cursor.moveToFirst();

        // Close the cursor and database
        cursor.close();
        db.close();

        return emailExists;
    }

    private boolean storeUserData(String uname, String uemail, String upass ) {
        // Get a writable database
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // Create a ContentValues object to store data
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_FULLNAME, uname);
        values.put(DatabaseHelper.COLUMN_EMAIL, uemail);
        values.put(DatabaseHelper.COLUMN_PASSWORD, upass);

        // Insert data into the "user" table
        long newRowId = db.insert(DatabaseHelper.TABLE_USER, null, values);

        // Close the database
        db.close();

        // Check if the insertion was successful
        return newRowId != -1;
    }

    private void redirectToLoginPage() {
        // Create an Intent to navigate to the LoginActivity
        Intent intent = new Intent(Register.this, Login.class);

        // Start the LoginActivity
        startActivity(intent);

        // Finish the current activity to prevent going back to the registration page
        finish();
    }

}