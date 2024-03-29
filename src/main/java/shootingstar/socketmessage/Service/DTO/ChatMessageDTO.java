package shootingstar.socketmessage.Service.DTO;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import shootingstar.socketmessage.Entity.ChatMessage;
import shootingstar.socketmessage.Entity.MessageType;
import java.sql.Timestamp;

@Getter
@Setter
@Data
public class ChatMessageDTO {

    private Long roomId; // 채팅방번호
    private String sender; // 메시지 보낸사람

    @NotNull
    private String msg; // 메시지

    @NotNull
    private MessageType type; // 메시지 타입

   public enum MessageType {
        ENTER, TALK
       //,QUIT
    }

//    private ChatRoomDTO chatRoomDTO;
    public ChatMessageDTO(MessageType type, Long roomId, String sender, String msg){
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.msg = msg;

    }
}
