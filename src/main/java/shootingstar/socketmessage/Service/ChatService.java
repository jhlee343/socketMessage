package shootingstar.socketmessage.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shootingstar.socketmessage.Entity.ChatMessage;
import shootingstar.socketmessage.Entity.ChatRoom;
import shootingstar.socketmessage.Entity.MessageType;
import shootingstar.socketmessage.Repository.ChatMessageRepository;
import shootingstar.socketmessage.Repository.ChatRoomRepository;
import shootingstar.socketmessage.Service.DTO.ChatMessageDTO;
import shootingstar.socketmessage.Service.DTO.ChatRoomDTO;

import java.util.*;
/*
내가한것
    채팅방 구현 (1:n) - 해결
    채팅방 목록 구현 - 해결
    현재 채팅방 리스트가 제대로 안나오고 있음 해당 부분 파악해야함 - 해결
    채팅방 나가면 그대로 종료? - 해결
    db에 채팅방 저장 -해결

해야할일
    db에 메세지 저장 - 메세지값이  저장안됨
                   - ChatMessage와 ChatRoom의 roomId가 다름
    채팅 리스트 없애고 방 하나만 만들기
    컨테이너 아이디에 맞는 채팅방 불러오기
    채팅방 아이디에 맞는 채팅 내역 불러오기 findMessageByRoomId()
       - 두 디비 엮어서 조회


추후 연결해야할 것
    containerId 연결
    roomId 연결( 현재 랜덤 값으로 넣어주고 있음)
*/
@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<Long, ChatRoomDTO> chatRoomsDTO;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private Map<String, ChatMessageDTO> chatMessageDTO;

    @PostConstruct
    private void init() {
        chatRoomsDTO = new LinkedHashMap<>();
    }

    /*
    채팅방 목록 불러오기
     */
    public List<ChatRoom> findAllRoom() {
        return chatRoomRepository.findAll();
    }
    private final Long randomId = (long)(Math.random()*100)+1;

    public ChatRoomDTO findRoomById(Long roomId) {
        //db로 구정 구현
        return chatRoomsDTO.get(roomId);
    }

//    public Page<FindAllChatMessageByRoomIdDTO> getMessagePage(Long roomId, Pageable pageable){
//        return chatMessageRepository.findAllMessageById(roomId, pageable);
//    }

    public ChatRoomDTO createRoom(String name) {
        ChatRoomDTO chatRoomDTO = ChatRoomDTO.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chatRoomsDTO.put(randomId, chatRoomDTO);
//        saveRoom(chatRoomDTO);
        return chatRoomDTO;
    }
    @Transactional
    public ChatRoom saveRoom(ChatRoomDTO chatRoomDTO) throws JsonProcessingException {

        ChatRoom chatRoom = new ChatRoom(
                chatRoomDTO.getName(),
                randomId,
                chatRoomDTO.getContainerId());
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    @Transactional
    public ChatMessage saveMessage(ChatMessageDTO chatMessageDTO) throws JsonProcessingException{
        System.out.println(chatMessageDTO.getMessage());
        ChatMessage chatMessage = new ChatMessage(
                randomId,
                MessageType.TALK,
                chatMessageDTO.getSender(),
                chatMessageDTO.getMessage());
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }
    public String convertJSON(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(object);
    }
}