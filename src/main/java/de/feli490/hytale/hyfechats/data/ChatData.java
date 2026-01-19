package de.feli490.hytale.hyfechats.data;

import de.feli490.hytale.hyfechats.chat.ChatMessage;
import de.feli490.hytale.hyfechats.chat.ChatType;
import de.feli490.hytale.hyfechats.chat.PlayerChatRole;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ChatData {
    UUID getId();

    long getCreated();

    ChatType getChatType();

    JsonChatDataLoader.JsonChatRoleData[] getJsonChatRoleData();

    Set<PlayerChatRole> getPlayerChatRoles();

    List<ChatMessage> getMessages();
}
