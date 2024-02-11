package shootingstar.socketmessage.Service.DTO;

import lombok.*;
import shootingstar.socketmessage.Entity.ChatMessage;

import java.sql.Timestamp;

@Getter
@Setter
@Data
public class ChatMessageDTO {

    private String roomId; // 채팅방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
    private MessageType type; // 메시지 타입

    public enum MessageType {
        ENTER, TALK
        //,QUIT
    }

    private ChatRoomDTO chatRoomDTO;
    public ChatMessageDTO(MessageType type, String roomId, String sender, String message /*ChatRoomDTO chatRoomDTO*/){
        this.message = message;
        this.roomId = roomId;
        this.sender = sender;
        this.type = type;
//        this.chatRoomDTO = chatRoomDTO;
    }
    public ChatMessageDTO toEntity() {
        return new ChatMessageDTO(type, roomId,sender,message);
    }
}

