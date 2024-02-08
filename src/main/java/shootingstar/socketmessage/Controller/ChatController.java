package shootingstar.socketmessage.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shootingstar.socketmessage.Service.DTO.ChatRoomDTO;
import shootingstar.socketmessage.Service.ChatService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;


    @RequestMapping("chat/chatList")
    public String chatList(Model model){
        List<ChatRoomDTO> roomList = chatService.findAllRoom();
        model.addAttribute("roomList",roomList);
        return "chatList";
    }


    @PostMapping("chat/createRoom")
    public String createRoom(Model model, @RequestParam String name, String username) {
        ChatRoomDTO room = chatService.createRoom(name);
        model.addAttribute("room",room);
        model.addAttribute("username",username);
        return "chatRoom";
    }

    @GetMapping("/chat/chatRoom")
    public String chatRoom(Model model, @RequestParam Long roomId){
        ChatRoomDTO room = chatService.findRoomById(roomId);
        model.addAttribute("room",room);
        return "chatRoom";
    }

}