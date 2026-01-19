package de.feli490.hytale.hyfechats.chat;

import de.feli490.utils.hytale.playerdata.PlayerDataProvider;
import java.util.UUID;

public class ChatFactory {

    private final PlayerDataProvider playerDataProvider;

    public ChatFactory(PlayerDataProvider playerDataProvider) {
        this.playerDataProvider = playerDataProvider;
    }

    public Chat createDirect(UUID player1, UUID player2) {
        Chat chat = createChat(ChatType.DIRECT);
        chat.addChatter(player1, ChatRole.OWNER);
        chat.addChatter(player2, ChatRole.OWNER);
        return chat;
    }

    public Chat createGroup(UUID player1) {
        Chat chat = createChat(ChatType.GROUP);
        chat.addChatter(player1, ChatRole.OWNER);
        return chat;
    }

    private Chat createChat(ChatType chatType) {
        return new Chat(UUID.randomUUID(), chatType, System.currentTimeMillis(), playerDataProvider);
    }
}
