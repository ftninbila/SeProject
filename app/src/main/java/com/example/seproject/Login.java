package com.example.seproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText wrongEmail, wrongPassword;


    private Button login, cancel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Find views
        wrongEmail = findViewById(R.id.wrongEmail);
        wrongPassword = findViewById(R.id.wrongPassword);

        login = findViewById(R.id.login);
        cancel = findViewById(R.id.cancel);


        login.setOnClickListener(new View.OnClickListener() {
            private User userDetails;

            @Override
            public void onClick(View view) {

                String email = wrongEmail.getText().toString();
                String password = wrongPassword.getText().toString();


                if (validateInput(email, password)) {
                    if (authenticateUser(email, password)) {
                        // Successful login
                        Toast.makeText(Login.this, "Login successful!", Toast.LENGTH_SHORT).show();

                        userDetails = getUserDetails(email);

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("userDetails", userDetails);
                        startActivity(intent);


                    } else {
                        // Invalid email or password
                        Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private boolean validateInput(String email, String password) {
        boolean isValid = true;

        if (email.isEmpty()) {
            wrongEmail.setText("Cannot leave blank");
            isValid = false;
        }

        if (password.isEmpty()) {
            wrongPassword.setText("Cannot leave blank");
            isValid = false;
        }

        return isValid;
    }

    private boolean authenticateUser(String email, String password) {
        // Get a readable database
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Define the columns you want to retrieve
        String[] projection = {DatabaseHelper.COLUMN_EMAIL, DatabaseHelper.COLUMN_PASSWORD};

        // Define the selection criteria
        String selection = DatabaseHelper.COLUMN_EMAIL + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        // Query the database
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USER,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );


        // Check if the cursor has any rows, indicating a successful login
        boolean loginSuccessful = cursor.getCount() > 0;

        // Close the cursor and database
        cursor.close();
        db.close();

        return loginSuccessful;
    }


    private User getUserDetails(String email) {
        User userDetails = new User();

        // Get a readable database
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Define the columns you want to retrieve
        String[] projection = {
                DatabaseHelper.COLUMN_FULLNAME,
                DatabaseHelper.COLUMN_EMAIL,
        };

        // Define the selection criteria
        String selection = DatabaseHelper.COLUMN_FULLNAME + " = ?";
        String[] selectionArgs = {userDetails.getFullName()};

        // Query the database
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USER,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        // Move to the first row of the result
        if (cursor.moveToFirst()) {
            userDetails.setFullName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FULLNAME)));
            userDetails.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return userDetails;
    }
}
