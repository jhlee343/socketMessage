package shootingstar.socketmessage.Repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.hibernate.annotations.processing.Find;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import shootingstar.socketmessage.Entity.QChatMessage;
import shootingstar.socketmessage.Repository.DTO.FindAllChatMessageByRoomIdDTO;
import shootingstar.socketmessage.Repository.DTO.QFindAllChatMessageByRoomIdDTO;

import java.util.List;

import static shootingstar.socketmessage.Entity.QChatMessage.chatMessage;

public class ChatMessageRepositoryImpl implements ChatMessageRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    public ChatMessageRepositoryImpl(EntityManager em){ this.queryFactory =new JPAQueryFactory(em);}

    @Override
    public List<FindAllChatMessageByRoomIdDTO> findAllByRoomId(Long roomId) {
        return queryFactory
                .select(new QFindAllChatMessageByRoomIdDTO(
                        chatMessage.sender,
                        chatMessage.message,
                        chatMessage.createTime,
                        chatMessage.type
                ))
                .from(chatMessage)
                .orderBy()
                .fetch();
    }

    @Override
    public Page<FindAllChatMessageByRoomIdDTO> findAllMessageById(Long roomId, Pageable pageable) {
        List<FindAllChatMessageByRoomIdDTO> content = queryFactory
                .select(new QFindAllChatMessageByRoomIdDTO(
                        chatMessage.sender,
                        chatMessage.message,
                        chatMessage.createTime,
                        chatMessage.type
                ))
                .from(chatMessage)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(chatMessage.count())
                .from(chatMessage);

        return PageableExecutionUtils.getPage(content,pageable,countQuery::fetchOne);
    }
}
