package shootingstar.socketmessage.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shootingstar.socketmessage.Entity.ChatMessage;
import shootingstar.socketmessage.Entity.ChatRoom;
import shootingstar.socketmessage.Repository.ChatMessageRepository;
import shootingstar.socketmessage.Repository.ChatRoomRepository;
//import shootingstar.socketmessage.Repository.DTO.FindAllChatMessageByRoomIdDTO;
import shootingstar.socketmessage.Repository.DTO.FindAllChatMessageByRoomIdDTO;
import shootingstar.socketmessage.Service.DTO.ChatRoomDTO;
import shootingstar.socketmessage.Service.ChatService;
// import shootingstar.socketmessage.Repository.DTO.FindAllChatMessageByRoomIdDTO;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    /*
    채팅방 목록 나열 (삭제 예정)
     */
    @RequestMapping("chat/chatList")
    public String chatList(Model model){
        List<ChatRoom> roomList = chatService.findAllRoom();
        model.addAttribute("roomList",roomList);
        return "chatList";
    }


    /**
    채팅방 생성
     **/
    @PostMapping("chat/createRoom")
    public String createRoom(Model model, @RequestParam String name, String username) throws JsonProcessingException {
        ChatRoomDTO room = chatService.createRoom(name);
        model.addAttribute("room",room);
        model.addAttribute("username",username);
        saveRoom(room);
        System.out.println("방 만듬");
        return "chatRoom";
    }

    /**
    채팅방 roomid, containerid, name 저장
     **/
    @PostMapping("chat/saveRoom")
    public ChatRoom saveRoom(@Validated @ModelAttribute ChatRoomDTO chatRoomDTO) throws JsonProcessingException {
        System.out.println("saveRoom 실행");
        return chatService.saveRoom(chatRoomDTO);
    }

    /**
    채팅방 이동 & 불러오기
     **/
    @GetMapping("/chat/chatRoom")
    public String chatRoom(Model model, @RequestParam Long roomId){
        ChatRoomDTO room = chatService.findRoomById(roomId);
        model.addAttribute("room",room);
        return "chatRoom";
    }

    /**
     * 채팅 내역 불러오기
     */
//    @GetMapping("/chat/chatRoom")
//    public ResponseEntity<?> getMessageListPage(@RequestParam("roomId")Long roomId,
//                                                @PageableDefault(size=10)Pageable pageable){
//        Page<FindAllChatMessageByRoomIdDTO> messageLoad = chatService.getMessagePage(roomId,pageable);
//        return ResponseEntity.ok().body(messageLoad);
//    }
}