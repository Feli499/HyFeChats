package de.feli490.hytale.hyfechats.data;

import de.feli490.hytale.hyfechats.chat.Chat;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

public interface ChatDataLoader {
    Collection<ChatData> loadChats() throws IOException;

    void deleteChat(UUID chatId) throws IOException;

    void saveChat(Chat chat) throws IOException;

    void saveChats(Collection<Chat> chats) throws IOException;
}
