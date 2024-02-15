
package shootingstar.socketmessage.Entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class ChatMessage{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private String sender; // 메시지 보낸사람
    private String message; // 메시지
    private LocalDateTime createTime; //생성 시간

    @Enumerated(EnumType.STRING)
    private MessageType type; // 메시지 타입
//    private Long roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatRoomId")
    private ChatRoom chatRoom;

    @Builder
    public ChatMessage(ChatRoom chatRoom, MessageType type, String sender, String message) {
        this.chatRoom = chatRoom;
//        this.roomId = roomId;
        this.type = type;
        this.sender = sender;
        this.message = message;
        this.createTime = LocalDateTime.now();

    }


}