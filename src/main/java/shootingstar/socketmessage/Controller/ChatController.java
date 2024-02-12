package shootingstar.socketmessage.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shootingstar.socketmessage.Entity.ChatRoom;
import shootingstar.socketmessage.Repository.ChatRoomRepository;
import shootingstar.socketmessage.Service.DTO.ChatRoomDTO;
import shootingstar.socketmessage.Service.ChatService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final ChatRoomRepository chatRoomRepository;

    @RequestMapping("chat/chatList")
    public String chatList(Model model){
        List<ChatRoomDTO> roomList = chatService.findAllRoom();
        model.addAttribute("roomList",roomList);
        return "chatList";
    }


    @PostMapping("chat/createRoom")
    public String createRoom(Model model, @RequestParam String name, String username) throws JsonProcessingException {
        ChatRoomDTO room = chatService.createRoom(name);
        model.addAttribute("room",room);
        model.addAttribute("username",username);
        saveRoom(room);
        return "chatRoom";
    }

    @PostMapping("chat/saveRoom")
    public ChatRoom saveRoom(@Validated @ModelAttribute ChatRoomDTO chatRoomDTO) throws JsonProcessingException {
        System.out.println("saveRoom 화면 이동");
        //chatService.saveRoom(chatRoomDTO);
        return chatService.saveRoom(chatRoomDTO);
    }

    @GetMapping("/chat/chatRoom")
    public String chatRoom(Model model, @RequestParam Long roomId){
        ChatRoomDTO room = chatService.findRoomById(roomId);
        model.addAttribute("room",room);

        return "chatRoom";
    }

}