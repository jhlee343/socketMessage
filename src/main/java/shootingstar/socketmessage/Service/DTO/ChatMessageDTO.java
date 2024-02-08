package shootingstar.socketmessage.Service.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDTO {
    public enum MessageType {
        ENTER, TALK
        //,QUIT
    }
    private MessageType type; // 메시지 타입
    private String roomId; // 채팅방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
    /*
    private Sring [] usersId; //
    private String senderId; //
     */

}