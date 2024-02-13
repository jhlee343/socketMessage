package shootingstar.socketmessage.Repository.DTO;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import shootingstar.socketmessage.Entity.ChatRoom;
import shootingstar.socketmessage.Entity.MessageType;

import java.time.LocalDateTime;

@Data
public class FindAllChatMessageByRoomIdDTO {
    private String sender;
    private String message;
    private MessageType type;
    private LocalDateTime createTime ;
    private Long roomId;

    @QueryProjection
    public FindAllChatMessageByRoomIdDTO(Long roomId, String sender, String message, LocalDateTime createTime, MessageType type){
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.type = type;
        this.createTime = createTime;
    }
}
