package dto.response;

import java.util.List;

public class Current {
    public String observation_time;
    public Integer temperature;
    public Integer weather_code;
    public List<String> weather_icons;
    public List<String> weather_descriptions;
    public Integer wind_speed;
    public Integer wind_degree;
    public String wind_dir;
    public Integer pressure;
    public Integer precip;
    public Integer humidity;
    public Integer cloudcover;
    public Integer feelslike;
    public Integer uv_index;
    public Integer visibility;
    public String is_day;
}
