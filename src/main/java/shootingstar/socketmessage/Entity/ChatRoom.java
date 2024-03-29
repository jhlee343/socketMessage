package shootingstar.socketmessage.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import shootingstar.socketmessage.Base.BaseTime;
import shootingstar.socketmessage.Entity.ChatMessage;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId; //채팅방 아이디

    private String name; //채팅방 이름

    private String containerId;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    @Builder
    public ChatRoom(String name, String containerId){
        this.name = name;
        this.containerId = containerId;
    }

    public void addChatMessage(ChatMessage message) {
        this.chatMessageList.add(message);
    }
    }

