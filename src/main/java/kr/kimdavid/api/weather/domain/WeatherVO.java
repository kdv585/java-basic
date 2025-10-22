package kr.kimdavid.api.weather.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherVO {

    private LocalDate date;
    private Double avgTemp;
    private Double maxTemp;
    private LocalTime maxTempTime;
    private Double minTemp;
    private LocalTime minTempTime;
    private Double tempRange;
    private Double precipitation;
}
