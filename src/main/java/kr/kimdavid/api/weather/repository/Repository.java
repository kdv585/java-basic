package kr.kimdavid.api.weather.repository;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import kr.kimdavid.api.common.domain.Messenger;
import kr.kimdavid.api.weather.domain.WeatherDTO;

@Component("weatherRepository")
public class Repository {

    public Messenger save(WeatherDTO weatherDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    public Messenger update(WeatherDTO weatherDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public Messenger delete(WeatherDTO weatherDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    public Messenger findById(WeatherDTO weatherDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public Messenger findAll() {
        List<WeatherDTO> weatherList = new ArrayList<>();
        Messenger messenger = new Messenger();

        try {
            // CSV 파일 경로
            String csvFilePath = "src/main/resources/static/csv/TEST_weather_00.csv-Grid view.csv";

            // CSV 파일 읽기
            FileReader reader = new FileReader(csvFilePath);
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            // CSV 데이터를 WeatherDTO로 변환
            for (CSVRecord record : parser) {
                WeatherDTO weather = WeatherDTO.builder()
                        .date(LocalDate.parse(record.get("일시"), dateFormatter))
                        .avgTemp(parseDouble(record.get("평균기온(℃)")))
                        .maxTemp(parseDouble(record.get("최고기온(℃)")))
                        .maxTempTime(parseTime(record.get("최고기온시각"), timeFormatter))
                        .minTemp(parseDouble(record.get("최저기온(℃)")))
                        .minTempTime(parseTime(record.get("최저기온시각"), timeFormatter))
                        .tempRange(parseDouble(record.get("일교차")))
                        .precipitation(parseDouble(record.get("강수량(mm)")))
                        .build();

                weatherList.add(weather);
            }

            parser.close();
            reader.close();

            messenger.setCode(0);
            messenger.setMessage("날씨 데이터 조회 성공: " + weatherList.size() + "일");

            // 터미널에 데이터 출력
            printWeatherData(weatherList);

        } catch (IOException e) {
            messenger.setCode(1);
            messenger.setMessage("날씨 데이터 조회 중 오류 발생: " + e.getMessage());
        }

        return messenger;
    }

    private Double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private LocalTime parseTime(String value, DateTimeFormatter formatter) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalTime.parse(value, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    private void printWeatherData(List<WeatherDTO> weatherList) {
        System.out.println("\n=== 날씨 데이터 ===");
        System.out.printf("%-12s %-8s %-8s %-10s %-8s %-10s %-8s %-8s%n",
                "날짜", "평균기온", "최고기온", "최고기온시각", "최저기온", "최저기온시각", "일교차", "강수량");
        System.out.println("=".repeat(80));

        for (WeatherDTO weather : weatherList) {
            System.out.printf("%-12s %-8s %-8s %-10s %-8s %-10s %-8s %-8s%n",
                    weather.getDate(),
                    weather.getAvgTemp() != null ? String.format("%.1f", weather.getAvgTemp()) : "-",
                    weather.getMaxTemp() != null ? String.format("%.1f", weather.getMaxTemp()) : "-",
                    weather.getMaxTempTime() != null ? weather.getMaxTempTime().toString() : "-",
                    weather.getMinTemp() != null ? String.format("%.1f", weather.getMinTemp()) : "-",
                    weather.getMinTempTime() != null ? weather.getMinTempTime().toString() : "-",
                    weather.getTempRange() != null ? String.format("%.1f", weather.getTempRange()) : "-",
                    weather.getPrecipitation() != null ? String.format("%.1f", weather.getPrecipitation()) : "-");
        }
        System.out.println("=".repeat(80));
        System.out.println("총 " + weatherList.size() + "일의 날씨 데이터");
    }
}
