package shootingstar.socketmessage.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shootingstar.socketmessage.Entity.ChatMessage;
import shootingstar.socketmessage.Repository.DTO.FindAllChatMessageByRoomIdDTO;

import java.util.List;
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> ,ChatMessageRepositoryCustom{

//   List<ChatMessage> findByChatRoomRoomId(Long roomId);
//    @Query("select message from ChatMessage message where message.chatRoom.roomId = :roomId")
//    List<FindAllChatMessageByRoomIdDTO> findByMessageJpql(@Param("roomId") Long roomid);
}

