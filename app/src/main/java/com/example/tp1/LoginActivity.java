package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tp1.bussines.UserBO;
import com.tp1.model.User;
import com.tp1.persist.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button  buttonEnter;
    TextView register;

    User user;

    DatabaseHelper dbHelper;
    UserBO userBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        register = (TextView) findViewById(R.id.register);

        //dbHelper = new DatabaseHelper(this);
        userBO = new UserBO(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                int id = userBO.checkUser(user);

                if(id != 0){
                    Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    homeIntent.putExtra("id", id);
                    startActivity(homeIntent);
                }else
                    Toast.makeText(LoginActivity.this, "Usuario y/o contrasena incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
