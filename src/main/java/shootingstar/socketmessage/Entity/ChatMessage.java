
package shootingstar.socketmessage.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shootingstar.socketmessage.Base.BaseTime;
import shootingstar.socketmessage.Service.DTO.ChatMessageDTO;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
public class ChatMessage extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String roomId; // 채팅방번호
    @Column
    private String sender; // 메시지 보낸사람
    @Column
    private String message; // 메시지
    @Column
    private MessageType type; // 메시지 타입
    @Column
    private Timestamp timestamp;

    public ChatMessage(String message, String message1, String sender, ChatMessageDTO.MessageType type) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
//        this.chatRoomDTO = chatRoomDTO;
//        this.type = type;
    }

    public enum MessageType {
        ENTER, TALK
        //,QUIT
    }

    @ManyToOne
    private ChatRoom chatRoom;

    public ChatMessage(String roomId, String sender, String message/*,ChatRoomDTO chatRoomDTO*/, MessageType type){
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
//        this.chatRoomDTO = chatRoomDTO;
        this.type = type;
    }

    public ChatMessage toEntity(){
        return new ChatMessage(roomId, sender, message, type);
    }

}