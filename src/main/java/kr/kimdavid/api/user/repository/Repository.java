package kr.kimdavid.api.user.repository;

import kr.kimdavid.api.user.domain.UserDTO;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Repository {

    /**
     * 승객 명단을 터미널에 출력하는 메서드
     */
    public void printPassengerList(List<UserDTO> passengers) {
        System.out.println("========================================");
        System.out.println("     타이타닉 승객 명단");
        System.out.println("========================================");
        System.out.println();

        // 헤더 출력
        System.out.printf("%-8s %-4s %-4s %-30s %-4s %-6s %-8s %-8s %-15s %-8s %-8s %-8s%n",
                "승객ID", "생존", "등급", "이름", "성별", "나이", "형제자매/배우자", "부모/자녀", "티켓", "요금", "객실", "승선항구");

        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------");

        // 데이터 출력
        for (UserDTO passenger : passengers) {
            String name = passenger.getName();
            String ticket = passenger.getTicket();
            String cabin = passenger.getCabin();

            // 긴 문자열은 줄임 처리
            if (name.length() > 30) {
                name = name.substring(0, 27) + "...";
            }
            if (ticket.length() > 15) {
                ticket = ticket.substring(0, 12) + "...";
            }
            if (cabin.length() > 8) {
                cabin = cabin.substring(0, 5) + "...";
            }

            System.out.printf("%-8s %-4s %-4s %-30s %-4s %-6s %-8s %-8s %-15s %-8s %-8s %-8s%n",
                    passenger.getPassengerId(),
                    passenger.getSurvived(),
                    passenger.getPclass(),
                    name,
                    passenger.getGender(),
                    passenger.getAge(),
                    passenger.getSibSp(),
                    passenger.getParch(),
                    ticket,
                    passenger.getFare(),
                    cabin,
                    passenger.getEmbarked());
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println("총 " + passengers.size() + "명의 승객 데이터");
        System.out.println("========================================");
    }
}
