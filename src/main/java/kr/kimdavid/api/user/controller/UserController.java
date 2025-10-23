package kr.kimdavid.api.user.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.kimdavid.api.common.domain.Messenger;
import kr.kimdavid.api.user.domain.UserDTO;
import lombok.RequiredArgsConstructor;
import kr.kimdavid.api.user.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public Messenger save(UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PutMapping("/{id}")
    public Messenger update(UserDTO userDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @DeleteMapping("/{id}")
    public Messenger delete(UserDTO userDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @GetMapping("id/{id}")
    public Messenger findById(UserDTO userDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @GetMapping("/all")
    public String findAll(Model model) {
        List<UserDTO> users = new ArrayList<>();
        Messenger messenger = new Messenger();

        try {
            // CSV 파일 경로
            String csvFilePath = "src/main/resources/static/csv/train.csv";

            // CSV 파일 읽기
            FileReader reader = new FileReader(csvFilePath);
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            int count = 0;

            // 처음 5명의 데이터만 읽기
            for (CSVRecord record : parser) {
                if (count >= 5)
                    break;

                UserDTO user = new UserDTO();
                user.setPassengerId(record.get("PassengerId"));
                user.setSurvived(record.get("Survived"));
                user.setPclass(record.get("Pclass"));
                user.setName(record.get("Name"));
                user.setGender(record.get("Sex"));
                user.setAge(record.get("Age"));
                user.setSibSp(record.get("SibSp"));
                user.setParch(record.get("Parch"));
                user.setTicket(record.get("Ticket"));
                user.setFare(record.get("Fare"));
                user.setCabin(record.get("Cabin"));
                user.setEmbarked(record.get("Embarked"));

                users.add(user);
                count++;
            }

            parser.close();
            reader.close();

            messenger.setCode(0);
            messenger.setMessage("승객 데이터 조회 성공: " + users.size() + "명");

            // 터미널에 데이터 출력
            printPassengerData(users);

        } catch (IOException e) {
            // 오류 발생 시 빈 리스트로 처리
            users = new ArrayList<>();
            messenger.setCode(1);
            messenger.setMessage("데이터 조회 중 오류 발생: " + e.getMessage());
        }

        // Model에 데이터 추가
        model.addAttribute("messenger", messenger);
        model.addAttribute("users", users);

        // 템플릿 반환
        return "user/list";
    }

    private void printPassengerData(List<UserDTO> users) {
        System.out.println("\n=== 타이타닉 승객 데이터 ===");
        System.out.printf("%-8s %-4s %-4s %-30s %-4s %-6s %-8s %-8s %-15s %-8s %-8s %-8s%n",
                "승객ID", "생존", "등급", "이름", "성별", "나이", "형제자매", "부모자녀", "티켓", "요금", "객실", "승선항구");
        System.out.println("=".repeat(120));

        for (UserDTO user : users) {
            String name = user.getName();
            String ticket = user.getTicket();
            String cabin = user.getCabin();

            // null 체크 후 긴 문자열은 줄임 처리
            if (name != null && name.length() > 30) {
                name = name.substring(0, 27) + "...";
            }
            if (ticket != null && ticket.length() > 15) {
                ticket = ticket.substring(0, 12) + "...";
            }
            if (cabin != null && cabin.length() > 8) {
                cabin = cabin.substring(0, 5) + "...";
            }

            System.out.printf("%-8s %-4s %-4s %-30s %-4s %-6s %-8s %-8s %-15s %-8s %-8s %-8s%n",
                    user.getPassengerId(),
                    user.getSurvived(),
                    user.getPclass(),
                    name,
                    user.getGender(),
                    user.getAge(),
                    user.getSibSp(),
                    user.getParch(),
                    ticket,
                    user.getFare(),
                    cabin,
                    user.getEmbarked());
        }

        System.out.println("=".repeat(120));
        System.out.println("총 " + users.size() + "명의 승객 데이터");
    }
}