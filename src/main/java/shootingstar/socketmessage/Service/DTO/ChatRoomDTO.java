package shootingstar.socketmessage.Service.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Data
public class ChatRoomDTO {
    private Long roomId; //채팅방 아이디
    private String name; //채팅방 이름
    private String  containerId; //컨테이너 아이디
    private Set<WebSocketSession> sessions = new HashSet<>();
    @Builder
    public ChatRoomDTO(String name, Long roomId,String containerId) {
        this.roomId = roomId;
        this.name = name;
        this.containerId = containerId;
    }
}