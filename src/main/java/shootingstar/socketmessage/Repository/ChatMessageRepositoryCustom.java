package shootingstar.socketmessage.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shootingstar.socketmessage.Entity.ChatMessage;
import shootingstar.socketmessage.Repository.DTO.FindAllChatMessageByRoomIdDTO;

import java.util.List;

public interface ChatMessageRepositoryCustom {
//    List<ChatMessage> findByChatRoomRoomId(Long roomId);
    List<FindAllChatMessageByRoomIdDTO> findAllByRoomId(Long roomId);

    Page<FindAllChatMessageByRoomIdDTO> findAllMessageById(Long roomId, Pageable pageable);
}
