package kr.kimdavid.api.weather.repository;

import kr.kimdavid.api.common.domain.Messenger;
import kr.kimdavid.api.weather.domain.WeatherDTO;
import org.springframework.stereotype.Component;
import java.util.List;

@Component("weatherRepository")
public class Repository {

    /**
     * 날씨 데이터를 터미널에 출력하고 Messenger를 반환하는 메서드
     */
    public Messenger printWeatherListMessage(List<WeatherDTO> weatherList) {
        System.out.println("========================================");
        System.out.println("     날씨 데이터");
        System.out.println("========================================");
        System.out.println();

        // 헤더 출력
        System.out.printf("%-12s %-8s %-8s %-12s %-8s %-12s %-8s %-8s%n",
                "일시", "평균기온", "최고기온", "최고기온시각", "최저기온", "최저기온시각", "일교차", "강수량");

        System.out.println(
                "------------------------------------------------------------------------------------------------");

        // 데이터 출력
        for (WeatherDTO weather : weatherList) {
            System.out.printf("%-12s %-8.1f %-8.1f %-12s %-8.1f %-12s %-8.1f %-8.1f%n",
                    weather.getDate(),
                    weather.getAvgTemp(),
                    weather.getMaxTemp(),
                    weather.getMaxTempTime(),
                    weather.getMinTemp(),
                    weather.getMinTempTime(),
                    weather.getTempRange(),
                    weather.getPrecipitation() != null ? weather.getPrecipitation() : 0.0);
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println("총 " + weatherList.size() + "개의 날씨 데이터");
        System.out.println("========================================");

        Messenger messenger = new Messenger();
        messenger.setCode(0);
        messenger.setMessage("WeatherService에서 Repository 연결 완료: " + weatherList.size() + "개의 날씨 데이터 처리");
        return messenger;
    }
}
