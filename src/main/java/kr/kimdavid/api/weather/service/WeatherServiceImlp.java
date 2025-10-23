package kr.kimdavid.api.weather.service;

import org.springframework.stereotype.Service;

import kr.kimdavid.api.common.domain.Messenger;
import kr.kimdavid.api.weather.domain.WeatherDTO;
import kr.kimdavid.api.weather.repository.Repository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherServiceImlp implements WeatherService {

    private final Repository weatherRepository;

    @Override
    public Messenger save(WeatherDTO weatherDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Messenger update(WeatherDTO weatherDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Messenger delete(WeatherDTO weatherDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Messenger findById(WeatherDTO weatherDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Messenger findAll() {
        // Repository에서 데이터 처리
        return weatherRepository.findAll();
    }
}