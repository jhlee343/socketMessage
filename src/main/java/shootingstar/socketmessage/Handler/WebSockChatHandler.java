package shootingstar.socketmessage.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import shootingstar.socketmessage.DTO.ChatMessage;
import shootingstar.socketmessage.DTO.ChatRoom;
import shootingstar.socketmessage.Service.ChatService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSockChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;
    private final ChatRoom chatRoom;
    private List<String[]> sessionList = new ArrayList<String[]>();

    //메세지 전송시 처리
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom room = chatService.findRoomByroomId(chatMessage.getRoomId());
        room.handleActions(session, chatMessage, chatService);

    }

    //채팅 참여 알림
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("#ChattingHandler, afterConnectionEstablished");
        sessionList.add(chatRoom.getUsersId());
        log.info(session.getPrincipal().getName() + "님이 입장하셨습니다.");
    }
}
