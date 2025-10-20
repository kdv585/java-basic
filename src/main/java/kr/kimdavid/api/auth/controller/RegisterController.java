package kr.kimdavid.api.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
public class RegisterController {

    @GetMapping("/csv-test")
    @ResponseBody
    public String readCsvData() {
        try {
            // CSV 파일 읽기
            ClassPathResource resource = new ClassPathResource("static/csv/train.csv");
            InputStreamReader reader = new InputStreamReader(resource.getInputStream());

            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

            List<Map<String, String>> results = new ArrayList<>();
            int count = 0;

            for (CSVRecord record : parser) {
                if (count >= 5)
                    break; // 상위 5명만

                Map<String, String> passengerData = new HashMap<>();
                passengerData.put("passengerId", record.get("PassengerId"));
                passengerData.put("survived", record.get("Survived"));
                passengerData.put("pclass", record.get("Pclass"));
                passengerData.put("name", record.get("Name"));
                passengerData.put("sex", record.get("Sex"));
                passengerData.put("age", record.get("Age"));
                passengerData.put("sibSp", record.get("SibSp"));
                passengerData.put("parch", record.get("Parch"));
                passengerData.put("ticket", record.get("Ticket"));
                passengerData.put("fare", record.get("Fare"));
                passengerData.put("cabin", record.get("Cabin"));
                passengerData.put("embarked", record.get("Embarked"));

                results.add(passengerData);
                count++;
            }

            parser.close();
            reader.close();

            // Map 구조를 HTML로 변환
            StringBuilder html = new StringBuilder();
            for (int i = 0; i < results.size(); i++) {
                Map<String, String> passenger = results.get(i);
                html.append(String.format(
                        "승객 %d: ID=%s, 생존=%s, 등급=%s, 이름=%s, 성별=%s, 나이=%s<br>",
                        i + 1,
                        passenger.get("passengerId"),
                        passenger.get("survived"),
                        passenger.get("pclass"),
                        passenger.get("name"),
                        passenger.get("sex"),
                        passenger.get("age")));
            }

            return html.toString();

        } catch (IOException e) {
            return "CSV 파일 읽기 오류: " + e.getMessage();
        }
    }
}
