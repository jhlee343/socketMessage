package shootingstar.socketmessage.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shootingstar.socketmessage.DTO.ChatMessage;
import shootingstar.socketmessage.DTO.ChatRoom;
import shootingstar.socketmessage.Handler.WebSockChatHandler;
import shootingstar.socketmessage.Service.ChatService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;
    private final WebSockChatHandler webSockChatHandler;
    @PostMapping
    public ChatRoom createRoom(@RequestParam Long containerId) {
        //컨테이너 아이디 연결해오기
        return chatService.createRoom(containerId);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }
}