package shootingstar.socketmessage.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRoom is a Querydsl query type for ChatRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRoom extends EntityPathBase<ChatRoom> {

    private static final long serialVersionUID = -794449589L;

    public static final QChatRoom chatRoom = new QChatRoom("chatRoom");

    public final shootingstar.socketmessage.Base.QBaseTime _super = new shootingstar.socketmessage.Base.QBaseTime(this);

    public final ListPath<ChatMessage, QChatMessage> chatMessageList = this.<ChatMessage, QChatMessage>createList("chatMessageList", ChatMessage.class, QChatMessage.class, PathInits.DIRECT2);

    public final StringPath ContainerId = createString("ContainerId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath name = createString("name");

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QChatRoom(String variable) {
        super(ChatRoom.class, forVariable(variable));
    }

    public QChatRoom(Path<? extends ChatRoom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatRoom(PathMetadata metadata) {
        super(ChatRoom.class, metadata);
    }

}

