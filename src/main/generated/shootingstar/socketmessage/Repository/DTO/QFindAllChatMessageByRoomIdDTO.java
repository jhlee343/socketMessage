package shootingstar.socketmessage.Repository.DTO;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * shootingstar.socketmessage.Repository.DTO.QFindAllChatMessageByRoomIdDTO is a Querydsl Projection type for FindAllChatMessageByRoomIdDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QFindAllChatMessageByRoomIdDTO extends ConstructorExpression<FindAllChatMessageByRoomIdDTO> {

    private static final long serialVersionUID = -1241079669L;

    public QFindAllChatMessageByRoomIdDTO(com.querydsl.core.types.Expression<String> sender, com.querydsl.core.types.Expression<String> message, com.querydsl.core.types.Expression<java.time.LocalDateTime> createTime, com.querydsl.core.types.Expression<shootingstar.socketmessage.Entity.MessageType> type) {
        super(FindAllChatMessageByRoomIdDTO.class, new Class<?>[]{String.class, String.class, java.time.LocalDateTime.class, shootingstar.socketmessage.Entity.MessageType.class}, sender, message, createTime, type);
    }

}

