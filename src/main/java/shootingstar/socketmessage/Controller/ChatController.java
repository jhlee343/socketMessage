package shootingstar.socketmessage.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shootingstar.socketmessage.Entity.ChatRoom;
import shootingstar.socketmessage.Repository.ChatMessageRepository;
import shootingstar.socketmessage.Repository.ChatRoomRepository;
import shootingstar.socketmessage.Repository.DTO.FindAllChatMessageByRoomIdDTO;
import shootingstar.socketmessage.Service.DTO.ChatRoomDTO;
import shootingstar.socketmessage.Service.ChatService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
//RestController 따로 파서 만들어두기
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
        ChatRoom room = chatService.createRoom(name);
        model.addAttribute("room",room);
        model.addAttribute("username",username);
        return "chatRoom";
    }


    //채팅방 이동 & 불러오기

    @GetMapping("/chat/chatRoom")
    public String chatRoom(Model model, @RequestParam Long roomId){
        ChatRoomDTO room = chatService.findRoomById(roomId);
        log.info(room.getRoomId().toString());
        model.addAttribute("room",room);
        return "chatRoom";
    }


    @GetMapping("/chat/chatRoom/{roomId}")
    public ResponseEntity<?> getAllLisgtPage(@RequestParam("roomId") Long roomId,
                                             @PageableDefault(size =10) Pageable pageable){

        Page<FindAllChatMessageByRoomIdDTO> findAllChatMessageByRoomIdDTOPage = chatService.getAllMessagePage(roomId, pageable);
        log.info(findAllChatMessageByRoomIdDTOPage.toString());
        return ResponseEntity.ok().body(findAllChatMessageByRoomIdDTOPage);
    }
}


