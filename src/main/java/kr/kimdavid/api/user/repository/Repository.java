package kr.kimdavid.api.user.repository;

import kr.kimdavid.api.common.domain.Messenger;
import kr.kimdavid.api.user.domain.UserDTO;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Repository {

    public Messenger printPassengerListMessage(List<UserDTO> passengers) {

        // 데이터 출력
        for (UserDTO passenger : passengers) {
            String name = passenger.getName();
            String ticket = passenger.getTicket();
            String cabin = passenger.getCabin();

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

        Messenger messenger = new Messenger();
        messenger.setCode(0);
        messenger.setMessage("UserService에서 Repository 연결 완료: " + passengers.size() + "명의 데이터 처리");
        return messenger;
    }
}
