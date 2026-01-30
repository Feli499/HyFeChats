package de.feli490.hytale.hyfechats.data.json;

import de.feli490.hytale.hyfechats.chat.Chat;
import de.feli490.hytale.hyfechats.chat.ChatMessage;
import de.feli490.hytale.hyfechats.data.ChatDataSaver;
import java.io.IOException;
import java.util.UUID;

public class JsonChatDataSaver implements ChatDataSaver {

    @Override
    public void saveChatMetaData(Chat chat) throws IOException {

    }

    @Override
    public void deleteChat(UUID chatId) throws IOException {

    }

    @Override
    public void saveMessage(ChatMessage message) throws IOException {

    }

    @Override
    public void savePlayerChatProperties(ChatMessage message) throws IOException {

    }
}
