package shootingstar.socketmessage.Handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import shootingstar.socketmessage.Entity.ChatMessage;
import shootingstar.socketmessage.Repository.ChatMessageRepository;
import shootingstar.socketmessage.Service.DTO.ChatMessageDTO;
import shootingstar.socketmessage.Service.DTO.ChatRoomDTO;
import shootingstar.socketmessage.Service.ChatService;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSockChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;
    @Autowired
    private final ChatMessageRepository chatMessageRepository;
    private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());

//    private Map<String, ArrayList<WebSocketSession>> RoomList = new ConcurrentHashMap<String, ArrayList<WebSocketSession>>();
//    private Map<WebSocketSession, String> sessionList = new ConcurrentHashMap<WebSocketSession, String>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(session.getId());
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        ChatMessageDTO chatMessageDTO = objectMapper.readValue(payload, ChatMessageDTO.class);
        ChatRoomDTO room = chatService.findRoomById(Long.valueOf(chatMessageDTO.getRoomId()));
        //Set<WebSocketSession>
        sessions = room.getSessions();

        if (chatMessageDTO.getType().equals(ChatMessageDTO.MessageType.ENTER)) {
            sessions.add(session);
            chatMessageDTO.setMessage(chatMessageDTO.getSender() + "님이 입장했습니다.");

            sendToEachSocket(sessions, new TextMessage(objectMapper.writeValueAsString(chatMessageDTO)));
        } else {
            sendToEachSocket(sessions, message);
        }
    }
        //about save message
/*        String saved = objectMapper.writeValueAsString(chatMessageDTO);
        System.out.println(saved);
        saveMessage(chatMessageDTO);

    }
    @Transactional
    public ChatMessage saveMessage(ChatMessageDTO chatMessageDTO){
//        String save = objectMapper.writeValueAsString(chatMessageDTO);
        ChatMessage chatMessage = new ChatMessage(
                chatMessageDTO.getMessage(),
                chatMessageDTO.getMessage(),
                chatMessageDTO.getSender(),
                chatMessageDTO.getType());

        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

 */
    private  void sendToEachSocket(Set<WebSocketSession> sessions, TextMessage message){
        sessions.parallelStream().forEach( roomSession -> {
            try {
                roomSession.sendMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }



    //세션 끊을 때
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    public String convertJSON(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(object);
    }

}