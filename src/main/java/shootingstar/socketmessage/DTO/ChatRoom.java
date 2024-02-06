package shootingstar.socketmessage.DTO;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;
import shootingstar.socketmessage.DTO.ChatMessage;
import shootingstar.socketmessage.Service.ChatService;

import java.util.HashSet;
import java.util.Set;

@Getter

public class ChatRoom {
    private Long roomId; //채팅방 아이디
    private Long containerId; // 컨테이너 아이디
    private String[] usersId; // 참여 사용자 리스트

    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(Long roomId, Long containerId, String [] usersId) {
        this.roomId = roomId;
        this.containerId = containerId;
        this.usersId = usersId;
    }

    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
    /*    if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
        }*/
        sendMessage(chatMessage, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}