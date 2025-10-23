package kr.kimdavid.api.weather.service;

import kr.kimdavid.api.weather.domain.WeatherDTO;
import kr.kimdavid.api.common.domain.Messenger;

public interface WeatherService {
    Messenger save(WeatherDTO weatherDTO);
    Messenger update(WeatherDTO weatherDTO);
    Messenger delete(WeatherDTO weatherDTO);
    Messenger findById(WeatherDTO weatherDTO);
    Messenger findAll();

}
