package shootingstar.socketmessage.Entity;
/*
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shootingstar.socketmessage.Base.BaseTime;

import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ChatRoom extends BaseTime {
    @Id
    @GeneratedValue
    private Long roomId; //채팅방 아이디
    private String name; //채팅방 이름

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    private List<ChatMessage> chatMessageList;

    @Builder
    public ChatRoom(String name){
        this.name = name;
    }
}
*/