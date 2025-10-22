package kr.kimdavid.api.weather.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDTO {

    private LocalDate date;
    private Double avgTemp;
    private Double maxTemp;
    private LocalTime maxTempTime;
    private Double minTemp;
    private LocalTime minTempTime;
    private Double tempRange;
    private Double precipitation;
}
