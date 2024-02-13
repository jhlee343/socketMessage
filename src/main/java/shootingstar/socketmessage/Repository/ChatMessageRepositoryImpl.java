package shootingstar.socketmessage.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import shootingstar.socketmessage.Entity.ChatRoom;
import shootingstar.socketmessage.Repository.DTO.*;

import java.util.List;
import java.util.Optional;

import static shootingstar.socketmessage.Entity.QChatMessage.*;
import static shootingstar.socketmessage.Entity.QChatRoom.chatRoom;

public class ChatMessageRepositoryImpl implements ChatMessageRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    public ChatMessageRepositoryImpl(EntityManager em){ this.queryFactory =new JPAQueryFactory(em);}

    @Override
    public List<FindAllChatMessageByRoomIdDTO> findAllByRoomId(Long roomId) {
        return queryFactory
                .select(new QFindAllChatMessageByRoomIdDTO(
                        chatRoom.roomId,
                        chatMessage.sender,
                        chatMessage.message,
                        chatMessage.createTime,
                        chatMessage.type
                ))
                .from(chatMessage)
                //.leftJoin(chatRoom).on(chatRoom.roomId.eq(chatMessage.chatRoom.roomId))
                .where(roomIdEq(roomId))
                .orderBy()
                .fetch();
    }


    @Override
    public Page<FindAllChatMessageByRoomIdDTO> findAllMessageById(Long roomId, Pageable pageable) {
        List<FindAllChatMessageByRoomIdDTO> content = queryFactory
                .select(new QFindAllChatMessageByRoomIdDTO(
                        chatRoom.roomId,
                        chatMessage.sender,
                        chatMessage.message,
                        chatMessage.createTime,
                        chatMessage.type
                ))
                .from(chatMessage)
                //.leftJoin(chatRoom).on(chatRoom.roomId.eq(chatMessage.chatRoom.roomId))
                .where(roomIdEq(roomId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(chatMessage.count())
                .from(chatMessage);

        return PageableExecutionUtils.getPage(content,pageable,countQuery::fetchOne);
    }

    private BooleanExpression roomIdEq(Long roomId){
        return roomId !=null ? chatMessage.chatRoom.roomId.eq(roomId) : null;
    }
}
