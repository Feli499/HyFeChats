package de.feli490.hytale.hyfechats.data;

import de.feli490.hytale.hyfechats.chat.Chat;
import de.feli490.hytale.hyfechats.chat.ChatMessage;
import java.io.IOException;
import java.util.UUID;

public interface ChatDataSaver {

    void saveChatMetaData(Chat chat) throws IOException;

    void deleteChat(UUID chatId) throws IOException;

    void saveMessage(ChatMessage message) throws IOException;

    void savePlayerChatProperties(ChatMessage message) throws IOException;
}
