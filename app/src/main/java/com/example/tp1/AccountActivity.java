package com.example.tp1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tp1.model.User;
import com.tp1.persist.DatabaseHelper;
import com.tp1.presentation.VerPassActivity;

public class AccountActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText txtUser, txtPassword, txtEmail;
    Button btnUpdate;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        databaseHelper = new DatabaseHelper(this);

        user = databaseHelper.getUser(getIntent().getExtras().getInt("id"));

        txtUser = findViewById(R.id.txtUser);
        txtPassword = findViewById(R.id.txtPassword);
        txtEmail = findViewById(R.id.txtEmail);
        btnUpdate = findViewById(R.id.btnUpdate);

        txtUser.setText(user.getUsername());
        txtPassword.setText(user.getPassword());
        txtEmail.setText(user.getEmail());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createVerPassDialog().show();
            }
        });
    }

    public void updateUser(){
        user = new User();
        user.setId(getIntent().getExtras().getInt("id"));
        user.setUsername(txtUser.getText().toString());
        user.setPassword(txtPassword.getText().toString());
        user.setEmail(txtEmail.getText().toString());
        databaseHelper.updateUser(user);
    }

    public AlertDialog createVerPassDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Verificar Contrasena");

        LayoutInflater inflater = getLayoutInflater();

        View v = inflater.inflate(R.layout.activity_ver_pass, null);

        builder.setView(v);

        Button btnOk = (Button) v.findViewById(R.id.btnOk);
        Button btnCancel = (Button) v.findViewById(R.id.btnCancel);
        final EditText txtPassword = v.findViewById(R.id.txtPassword);

        btnOk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user.setPassword(txtPassword.getText().toString());
                        if(databaseHelper.checkUser(user) != 0){
                            updateUser();
                            Toast.makeText(AccountActivity.this, "Datos actualizados", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AccountActivity.this, "Contrasena incorrecta", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                }
        );

        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

        return builder.create();
    }
}
