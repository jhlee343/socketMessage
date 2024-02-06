package shootingstar.socketmessage.DTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChatMessage {
    private Long roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
    /*
    private String senderId; // 메시지 보낸 사람 아이디
     */
}