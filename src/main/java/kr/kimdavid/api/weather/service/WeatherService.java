package kr.kimdavid.api.weather.service;

import kr.kimdavid.api.weather.domain.WeatherDTO;
import kr.kimdavid.api.weather.repository.Repository;
import kr.kimdavid.api.common.domain.Messenger;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final Repository repository;

    public Messenger getTop5WeatherData(List<WeatherDTO> weatherList) {
        // 연결 용도로만 사용 - Repository 호출
        Messenger messenger = repository.printWeatherListMessage(weatherList);
        return messenger;
    }
}
