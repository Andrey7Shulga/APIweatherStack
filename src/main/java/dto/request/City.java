package dto.request;

import java.util.Objects;

public class City {

    private String name;
    private String country;
    private String lat;
    private String lon;
    private Integer temperature;
    private Integer wind_speed;
    private Integer pressure;

    public City(String name, String country, String lat, String lon, Integer temperature, Integer wind_speed, Integer pressure) {
        this.name = name;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
        this.temperature = temperature;
        this.wind_speed = wind_speed;
        this.pressure = pressure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(Integer wind_speed) {
        this.wind_speed = wind_speed;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getName().equals(city.getName()) && getCountry().equals(city.getCountry()) && getLat().equals(city.getLat()) && getLon().equals(city.getLon()) && getTemperature().equals(city.getTemperature()) && getWind_speed().equals(city.getWind_speed()) && getPressure().equals(city.getPressure());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCountry(), getLat(), getLon(), getTemperature(), getWind_speed(), getPressure());
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", temperature='" + temperature + '\'' +
                ", wind_speed=" + wind_speed +
                ", pressure=" + pressure +
                '}';
    }
}
