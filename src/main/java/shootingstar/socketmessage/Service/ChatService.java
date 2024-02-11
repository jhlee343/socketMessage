package shootingstar.socketmessage.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shootingstar.socketmessage.Entity.ChatRoom;
import shootingstar.socketmessage.Repository.ChatRoomRepository;
import shootingstar.socketmessage.Service.DTO.ChatRoomDTO;

import java.util.*;
/*
내가한것
    채팅방 구현 (1:n)
    채팅방 목록 구현
현재 구현문제
    현재 채팅방 리스트가 제대로 안나오고 있음 해당 부분 파악해야함 - 해결
    한번에 입장할지 container owner가 만드는 걸지는 생각해봐야함
    채팅방 나가면 그대로 종료?

추후 연결해야할 것
    containerId 연결
    roomId 연결( 현재 랜덤 값으로 넣어주고 있음)
    containerId or roomId를 공유하는 사용자 쿼리를 써서 리스트로 받아와야하나

*/
@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<Long, ChatRoomDTO> chatRoomsDTO;
    private ChatRoomRepository chatRoomRepository;

    @PostConstruct
    private void init() {
        chatRoomsDTO = new LinkedHashMap<>();
    }

    public List<ChatRoomDTO> findAllRoom() {
        return new ArrayList<>(chatRoomsDTO.values());
        //db로 수정 구현
    }

    public ChatRoomDTO findRoomById(Long roomId) {
        //db로 구정 구현
        return chatRoomsDTO.get(roomId);
    }

    public ChatRoomDTO createRoom(String name) {
        Long randomId = (long)(Math.random()*100)+1;
        ChatRoomDTO chatRoomDTO = ChatRoomDTO.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chatRoomsDTO.put(randomId, chatRoomDTO);
        saveRoom(chatRoomDTO);
        return chatRoomDTO;
    }
    @Transactional
    public ChatRoom saveRoom(ChatRoomDTO chatRoomDTO){
        ChatRoom chatRoom = new ChatRoom(
                chatRoomDTO.getName(),
                chatRoomDTO.getRoomId(),
                chatRoomDTO.getContainerId());
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    public String convertJSON(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(object);
    }
}