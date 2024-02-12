
package shootingstar.socketmessage.Entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import shootingstar.socketmessage.Base.BaseTime;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class ChatMessage{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private String roomId; // 채팅방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지

    @Enumerated(EnumType.STRING)
    private MessageType type; // 메시지 타입
    private LocalDateTime createTime; //생성 시간

    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    @Builder
    public ChatMessage(MessageType type ,String roomId, String sender, String message) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.createTime = LocalDateTime.now();
//        this.chatRoomDTO = chatRoomDTO;

    }


}