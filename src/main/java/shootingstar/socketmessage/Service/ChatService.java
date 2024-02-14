package shootingstar.socketmessage.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shootingstar.socketmessage.Entity.ChatMessage;
import shootingstar.socketmessage.Entity.ChatRoom;
import shootingstar.socketmessage.Entity.MessageType;
import shootingstar.socketmessage.Repository.ChatMessageRepository;
import shootingstar.socketmessage.Repository.ChatRoomRepository;
import shootingstar.socketmessage.Repository.DTO.FindAllChatMessageByRoomIdDTO;
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
    db에 메세지 저장 - 메세지값이  저장
                   - ChatMessage와 ChatRoom의 외래키 연동
                   - 해결
    채팅방 아이디에 맞는 채팅 내역 불러오기 findMessageByRoomId()
                   - 두 디비 엮어서 조회
                   - 해결
    페이징
해야할일
    채팅 리스트 없애고 방 하나만 만들기
    채팅방, 채팅 내역 동시에 보이게



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

    public ChatRoomDTO findRoomById(Long roomId) {
        if(chatRoomsDTO.containsKey(roomId)){
            return chatRoomsDTO.get(roomId);
        }
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findById(roomId);
        if(chatRoomOptional.isEmpty()){
            throw new RuntimeException();
        }
        ChatRoom chatRoom = chatRoomOptional.get();
        ChatRoomDTO chatRoomDTO = ChatRoomDTO.builder()
                .roomId(chatRoom.getRoomId())
                .name(chatRoom.getName())
                .containerId(chatRoom.getContainerId())
                .build();
        chatRoomsDTO.put(roomId,chatRoomDTO);
        return chatRoomDTO;
    }

    public Page<FindAllChatMessageByRoomIdDTO> getAllMessagePage(Long roomId, Pageable pageable){
        log.info(chatMessageRepository.findAllMessageById(roomId, pageable).toString());
        return chatMessageRepository.findAllMessageById(roomId, pageable);
    }

    @Transactional
    public ChatRoom createRoom(String containerId) {
        ChatRoom chatRoom = ChatRoom.builder()
                .name("전체 채팅방")
                .containerId(containerId)
                .build();
        chatRoomRepository.save(chatRoom);

        return chatRoom;
    }

    @Transactional
    public ChatMessage saveMessage(ChatMessageDTO chatMessageDTO, ChatRoomDTO roomDTO) throws JsonProcessingException{
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomDTO.getRoomId());
        if(optionalChatRoom.isEmpty()){
            throw new RuntimeException();
        }
        ChatRoom chatRoom = optionalChatRoom.get();
        ChatMessage chatMessage = new ChatMessage(
                chatRoom,
                MessageType.TALK,
                chatMessageDTO.getSender(),
                chatMessageDTO.getMsg());
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }
    public String convertJSON(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(object);
    }
}