package shootingstar.socketmessage.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import shootingstar.socketmessage.DTO.ChatRoom;

import java.io.IOException;
import java.util.*;


//채팅 관련 앤티티 작성해서 넘겨주기
@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<Long, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }


    public ChatRoom findRoomByroomId(Long roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(Long containerId) {
        Long randomId = ((long)(Math.random()*100)+1);
//        String[] usersId = findusersIdBycontainerId(Long containerId);
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomId)
//                .usersId(usersId)
//                .name(name)
                .containerId(containerId)
                .build();
        chatRooms.put(randomId, chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
            //메시지 database에 저장하기 json파일 형태로?
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}