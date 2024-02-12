package shootingstar.socketmessage.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import shootingstar.socketmessage.Base.BaseTime;
import shootingstar.socketmessage.Entity.ChatMessage;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue
    private Long roomId; //채팅방 아이디

    @NotNull
    private String name; //채팅방 이름

    @NotNull
    private String ContainerId;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    private List<ChatMessage> chatMessageList;

    @Builder
    public ChatRoom(String name, Long roomId, String ContainerId){
        this.name = name;
        this.roomId = roomId;
        this.ContainerId = ContainerId;
    }

    public void addChatMessage(ChatMessage message) {
        this.chatMessageList.add(message);
    }
    }

