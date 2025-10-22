package kr.kimdavid.api.weather.controller;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kimdavid.api.weather.domain.WeatherDTO;
import kr.kimdavid.api.weather.service.WeatherService;
import kr.kimdavid.api.common.domain.Messenger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("weather/data")
    @ResponseBody
    public Messenger printWeatherData() throws Exception {
        log.info("=== Weather API 호출됨 ===");

        // CSV 파일에서 모든 데이터 읽기
        String csvFilePath = "src/main/resources/static/csv/TEST_weather_00.csv-Grid view.csv";
        List<WeatherDTO> weatherList = new ArrayList<>();

        FileReader reader = new FileReader(csvFilePath);
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

        for (CSVRecord record : parser) {
            WeatherDTO weather = WeatherDTO.builder()
                    .date(LocalDate.parse(record.get("﻿일시")))
                    .avgTemp(Double.parseDouble(record.get("평균기온(℃)")))
                    .maxTemp(Double.parseDouble(record.get("최고기온(℃)")))
                    .maxTempTime(LocalTime.parse(record.get("최고기온시각")))
                    .minTemp(Double.parseDouble(record.get("최저기온(℃)")))
                    .minTempTime(LocalTime.parse(record.get("최저기온시각")))
                    .tempRange(Double.parseDouble(record.get("일교차")))
                    .precipitation(record.get("강수량(mm)") != null && !record.get("강수량(mm)").isEmpty()
                            ? Double.parseDouble(record.get("강수량(mm)"))
                            : 0.0)
                    .build();

            weatherList.add(weather);
        }

        parser.close();
        reader.close();

        log.info("=== CSV 파일에서 {}개의 데이터 로드 완료 ===", weatherList.size());

        // WeatherService를 통해 Repository 호출
        return weatherService.getTop5WeatherData(weatherList);
    }

    @GetMapping("weather")
    public String weatherPage(Model model) throws Exception {
        log.info("=== Weather 웹 페이지 호출됨 ===");

        List<WeatherDTO> weatherList = new ArrayList<>();

        try {
            // CSV 파일에서 모든 데이터 읽기
            String csvFilePath = "src/main/resources/static/csv/TEST_weather_00.csv-Grid view.csv";

            FileReader reader = new FileReader(csvFilePath);
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            for (CSVRecord record : parser) {
                WeatherDTO weather = WeatherDTO.builder()
                        .date(LocalDate.parse(record.get("﻿일시")))
                        .avgTemp(Double.parseDouble(record.get("평균기온(℃)")))
                        .maxTemp(Double.parseDouble(record.get("최고기온(℃)")))
                        .maxTempTime(LocalTime.parse(record.get("최고기온시각")))
                        .minTemp(Double.parseDouble(record.get("최저기온(℃)")))
                        .minTempTime(LocalTime.parse(record.get("최저기온시각")))
                        .tempRange(Double.parseDouble(record.get("일교차")))
                        .precipitation(record.get("강수량(mm)") != null && !record.get("강수량(mm)").isEmpty()
                                ? Double.parseDouble(record.get("강수량(mm)"))
                                : 0.0)
                        .build();

                weatherList.add(weather);
            }

            parser.close();
            reader.close();

        } catch (Exception e) {
            // 오류 발생 시 빈 리스트로 처리
            weatherList = new ArrayList<>();
        }

        log.info("=== 웹 페이지용 {}개의 데이터 로드 완료 ===", weatherList.size());

        Messenger messenger = weatherService.getTop5WeatherData(weatherList);
        model.addAttribute("messenger", messenger);
        model.addAttribute("weatherList", weatherList);

        return "weather/list";
    }
}
