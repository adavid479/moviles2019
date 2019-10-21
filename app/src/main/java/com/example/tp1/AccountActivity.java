package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.tp1.model.User;
import com.tp1.persist.DatabaseHelper;

public class AccountActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText txtUser, txtPassword, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        databaseHelper = new DatabaseHelper(this);

        User user = databaseHelper.getUser(1);

        txtUser = findViewById(R.id.txtUser);
        txtPassword = findViewById(R.id.txtPassword);
        txtEmail = findViewById(R.id.txtEmail);

        txtUser.setText(user.getUsername());
        txtPassword.setText(user.getPassword());
        txtEmail.setText(user.getEmail());
    }
}
