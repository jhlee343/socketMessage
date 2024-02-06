package shootingstar.socketmessage.DTO;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {
    private Long roomId; //채팅방 아이디
    private String name; //채팅방 이름
    //private Long containerId //컨테이너 아이디
    private Set<WebSocketSession> sessions = new HashSet<>();
    @Builder
    public ChatRoom(Long roomId, String name) {
        this.roomId = roomId;
        this.name = name;
        //this.containerId = containerId;
    }
}