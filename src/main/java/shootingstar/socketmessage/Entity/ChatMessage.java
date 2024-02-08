
package shootingstar.socketmessage.Entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shootingstar.socketmessage.Base.BaseTime;
import shootingstar.socketmessage.Entity.ChatRoom;
import shootingstar.socketmessage.Service.DTO.ChatMessageDTO;

@Entity
@Getter
@NoArgsConstructor
public class ChatMessage extends BaseTime {

    public enum MessageType {
        ENTER, TALK
        //,QUIT
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String roomId; // 채팅방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
    private MessageType type; // 메시지 타입

    @ManyToOne
    private ChatRoom chatRoom;

    @Builder
    public ChatMessage(String roomId, String sender, String message, ChatRoom chatRoom){
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
    }
}