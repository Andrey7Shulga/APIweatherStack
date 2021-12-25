package data;

import lombok.Data;

@Data
public class PlaceToMeasure {

    private String type;
    private String name;
    private String country;
    private String lat;
    private String lon;
    private int temperature;
    private int wind_speed;
    private int wind_degree;
    private int pressure;
    private int cloudcover;

    public PlaceToMeasure(String type, String name, String country, String lat, String lon) {
        this.type = type;
        this.name = name;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
    }
}
