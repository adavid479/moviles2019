package com.example.tp1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.tp1.LoginActivity;
import com.example.tp1.R;
import com.tp1.presentation.CameraActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        //getWeather();

        final TextView txtTemp = root.findViewById(R.id.txtTemp);
        final TextView txtLocation = root.findViewById(R.id.txtLocation);
        final Button btnLoadTemp = root.findViewById(R.id.btnLoadTemp);
        final Button btnCloseSession = root.findViewById(R.id.btnCloseSession);

        btnLoadTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findWeather(txtTemp, txtLocation);
            }
        });

        btnCloseSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Sesion cerrada", Toast.LENGTH_SHORT).show();
                Intent intentHome = new Intent(getContext(), LoginActivity.class);
                getActivity().startActivity(intentHome);
            }
        });

        final Button btnOpenCamera = root.findViewById(R.id.btnOpenCamera);
        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(getActivity(), CameraActivity.class);
                startActivity(cameraIntent);
            }
        });
        return root;
    }

    public void findWeather(TextView txtTemp, TextView txtLocation){
        OpenWeather openWeather = new OpenWeather(txtTemp, txtLocation);
        openWeather.start();
    }

    public void getWeather(){
        String key = "5d16680af4d3b46122a501f6e3ba80e2";
        String location = "Cordoba";
        String units = "imperial";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + location
                + "&appid=" + key + "&units=" + units;
        String inputLine;
        String inputText = "";

        try{
            URL url = new URL (urlString);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((inputLine = in.readLine()) != null){
                inputText += inputLine;
            }
            in.close();
            System.out.println(inputText);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}