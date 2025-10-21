package kr.kimdavid.api.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import kr.kimdavid.api.user.domain.UserDTO;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

            List<UserDTO> results = new ArrayList<>();
            int count = 0;

            for (CSVRecord record : parser) {
                if (count >= 5)
                    break; // 상위 5명만

                // UserDTO 객체 생성
                UserDTO userDTO = new UserDTO(
                        record.get("PassengerId"),
                        record.get("Survived"),
                        record.get("Pclass"),
                        record.get("Name"),
                        record.get("Sex"),
                        record.get("Age"),
                        record.get("SibSp"),
                        record.get("Parch"),
                        record.get("Ticket"),
                        record.get("Fare"),
                        record.get("Cabin"),
                        record.get("Embarked"));

                // 터미널에 출력
                System.out.println("=== 승객 " + (count + 1) + " ===");
                System.out.println("ID: " + userDTO.getPassengerId());
                System.out.println("생존: " + userDTO.getSurvived());
                System.out.println("등급: " + userDTO.getPclass());
                System.out.println("이름: " + userDTO.getName());
                System.out.println("성별: " + userDTO.getGender());
                System.out.println("나이: " + userDTO.getAge());
                System.out.println("형제자매/배우자: " + userDTO.getSibSp());
                System.out.println("부모/자녀: " + userDTO.getParch());
                System.out.println("티켓: " + userDTO.getTicket());
                System.out.println("요금: " + userDTO.getFare());
                System.out.println("객실: " + userDTO.getCabin());
                System.out.println("탑승항구: " + userDTO.getEmbarked());
                System.out.println("=========================");

                results.add(userDTO);
                count++;
            }

            parser.close();
            reader.close();

            // UserDTO 리스트를 HTML로 변환
            StringBuilder html = new StringBuilder();
            for (int i = 0; i < results.size(); i++) {
                UserDTO user = results.get(i);
                html.append(String.format(
                        "승객 %d: ID=%s, 생존=%s, 등급=%s, 이름=%s, 성별=%s, 나이=%s<br>",
                        i + 1,
                        user.getPassengerId(),
                        user.getSurvived(),
                        user.getPclass(),
                        user.getName(),
                        user.getGender(),
                        user.getAge()));
            }

            return html.toString();

        } catch (IOException e) {
            return "CSV 파일 읽기 오류: " + e.getMessage();
        }
    }
}
