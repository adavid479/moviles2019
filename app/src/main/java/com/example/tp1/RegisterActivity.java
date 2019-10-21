package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tp1.model.User;
import com.tp1.persist.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, rpPassword, txtEmail;
    Button buttonRegister;
    Button buttonCancel;

    DatabaseHelper dbHelper;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        rpPassword = (EditText) findViewById(R.id.rpPassword);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        txtEmail = findViewById(R.id.txtEmail);

        dbHelper = new DatabaseHelper(this);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = new User();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.setEmail(txtEmail.getText().toString());

                if(!password.getText().toString().equals(rpPassword.getText().toString()))
                    Toast.makeText(RegisterActivity.this, "Las contrasenas no coinciden", Toast.LENGTH_SHORT).show();
                else{
                    if(dbHelper.addUser(user) > 0){
                        Toast.makeText(RegisterActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                    }else
                        Toast.makeText(RegisterActivity.this, "Error de registro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
