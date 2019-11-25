package com.tp1.model;

public class OpenWeatherMap {
    private Object coord;
    private Object weather;
    private String base;
    private Object main;
    private Object wind;
    private Object clouds;
    private String dt;
    private Object sys;
    private String timezone;
    private String id;
    private String name;
    private String cod;

    public Object getCoord() {
        return coord;
    }

    public void setCoord(Object coord) {
        this.coord = coord;
    }

    public Object getWeather() {
        return weather;
    }

    public void setWeather(Object weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Object getMain() {
        return main;
    }

    public void setMain(Object main) {
        this.main = main;
    }

    public Object getWind() {
        return wind;
    }

    public void setWind(Object wind) {
        this.wind = wind;
    }

    public Object getClouds() {
        return clouds;
    }

    public void setClouds(Object clouds) {
        this.clouds = clouds;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Object getSys() {
        return sys;
    }

    public void setSys(Object sys) {
        this.sys = sys;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}
