package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp1.ui.home.HomeFragment;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        spUnit = findViewById(R.id.spUnit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.optionsUnit, android.R.layout.simple_spinner_item);
        spUnit.setAdapter(adapter);
        spUnit.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String strUnit = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), strUnit, Toast.LENGTH_SHORT).show();
        HomeFragment homeFragment = new HomeFragment();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
