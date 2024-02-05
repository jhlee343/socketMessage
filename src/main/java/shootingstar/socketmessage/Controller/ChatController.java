package shootingstar.socketmessage.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shootingstar.socketmessage.DTO.ChatRoom;
import shootingstar.socketmessage.Service.ChatService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }
}