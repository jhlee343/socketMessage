package shootingstar.socketmessage.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import shootingstar.socketmessage.Entity.ChatMessage;
import shootingstar.socketmessage.Repository.DTO.FindAllChatMessageByRoomIdDTO;

import java.util.List;
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}

