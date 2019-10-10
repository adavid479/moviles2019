package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity {

    Spinner spMeasurement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        spMeasurement = findViewById(R.id.spMeasurement);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.optionsMeasures, android.R.layout.simple_spinner_item);
        spMeasurement.setAdapter(adapter);
    }
}
