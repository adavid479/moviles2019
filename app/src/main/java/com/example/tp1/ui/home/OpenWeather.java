package com.example.tp1.ui.home;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;


public class OpenWeather extends Thread {

    private TextView txtTemp;
    private TextView txtLocation;

    public OpenWeather(TextView txtTemp, TextView txtLocation){
        this.txtTemp = txtTemp;
        this.txtLocation = txtLocation;
    }

    @Override
    public void run(){
        String key = "5d16680af4d3b46122a501f6e3ba80e2";
        String units = "metric";
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + txtLocation.getText()
                + "&appid=" + key + "&units=" + units;

        try{
            StringBuilder result = new StringBuilder();
            URL urlr = new URL(url);
            URLConnection connection = urlr.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            String strJson = "";
            while((line = bufferedReader.readLine()) != null){
                strJson += line;
            }
            JsonParser jsonParser = new JsonParser();
            Object owm = jsonParser.parse(strJson);
            JsonObject jsonObject = (JsonObject) owm;
            String strWeather = jsonObject.get("main").toString();
            owm = jsonParser.parse(strWeather.toString()); //substring(1, strWeather.length()-1));
            jsonObject = (JsonObject) owm;
            try {
                txtTemp.setText(jsonObject.get("temp").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            bufferedReader.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

}
