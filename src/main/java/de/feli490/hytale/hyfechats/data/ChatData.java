package de.feli490.hytale.hyfechats.data;

import de.feli490.hytale.hyfechats.chat.Chat;
import de.feli490.hytale.hyfechats.chat.ChatMessage;
import de.feli490.hytale.hyfechats.chat.ChatType;
import de.feli490.hytale.hyfechats.chat.PlayerChatProperties;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface ChatData {
    UUID getId();

    long getCreated();

    ChatType getChatType();

    Map<String, String> getProperties();

    Set<PlayerChatProperties> getPlayerChatProperties(Chat chat);

    List<ChatMessage> getMessages(Chat chat);
}
