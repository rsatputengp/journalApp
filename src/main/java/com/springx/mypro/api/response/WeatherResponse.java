package com.springx.mypro.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class WeatherResponse {

    private Request request;
    private Location location;
    private Current current;

    @Getter
    @Setter
    @ToString
    public class Current{
        @JsonProperty("observation_time")
        private String observationTime;
        private int temperature;
        @JsonProperty("weather_code")
        private int weatherCode;
        @JsonProperty("weather_icons")
        private ArrayList<String> weatherIcons;
        @JsonProperty("weather_descriptions")
        private ArrayList<String> weatherDescriptions;
        private Astro astro;
        @JsonProperty("air_quality")
        private AirQuality airQuality;
        @JsonProperty("wind_speed")
        private int windSpeed;
        @JsonProperty("wind_degree")
        private int windDegree;
        @JsonProperty("windDir")
        private String wind_dir;
        private int pressure;
        private int precip;
        private int humidity;
        private int cloudcover;
        private int feelslike;
        @JsonProperty("uv_index")
        private int uvIndex;
        private int visibility;
        @JsonProperty("is_day")
        private String isDay;

        @Getter
        @Setter
        @ToString
        public class AirQuality{
            private String co;
            private String no2;
            private String o3;
            private String so2;
            @JsonProperty("pm2_5")
            private String pm25;
            private String pm10;
            @JsonProperty("us-epa-index")
            private String us_epa_index;
            @JsonProperty("gb-defra-index")
            private String gbDefraIndex;
        }

        @Getter
        @Setter
        @ToString
        public class Astro{
            private String sunrise;
            private String sunset;
            private String moonrise;
            private String moonset;
            @JsonProperty("moon_phase")
            private String moonPhase;
            @JsonProperty("moon_illumination")
            private int moonIllumination;
        }
    }

    @Getter
    @Setter
    @ToString
    public class Location{
        private String name;
        private String country;
        private String region;
        private String lat;
        private String lon;
        @JsonProperty("timezone_id")
        private String timezoneId;
        private String localtime;
        @JsonProperty("localtime_epoch")
        private int localtimeEpoch;
        @JsonProperty("utc_offset")
        private String utcOffset;
    }

    @Getter
    @Setter
    @ToString
    public class Request{
        private String type;
        private String query;
        private String language;
        private String unit;
    }
}






