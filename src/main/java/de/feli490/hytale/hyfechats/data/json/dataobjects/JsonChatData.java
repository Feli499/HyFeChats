package de.feli490.hytale.hyfechats.data.json.dataobjects;

import de.feli490.hytale.hyfechats.chat.Chat;
import de.feli490.hytale.hyfechats.chat.ChatMessage;
import de.feli490.hytale.hyfechats.chat.ChatType;
import de.feli490.hytale.hyfechats.chat.PlayerChatProperties;
import de.feli490.hytale.hyfechats.data.ChatData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class JsonChatData implements ChatData {

    private final JsonChatMetaData metaData;
    private final Set<JsonPlayerChatProperties> playerChatProperties;
    private final List<JsonMessageData> messages;

    public JsonChatData(JsonChatMetaData metaData, List<JsonMessageData> messages, Set<JsonPlayerChatProperties> playerChatProperties) {
        this.metaData = metaData;
        this.messages = new ArrayList<>(messages);
        this.playerChatProperties = new HashSet<>(playerChatProperties);
    }

    public JsonChatData(Chat chat) {

        this.metaData = new JsonChatMetaData(chat);

        this.messages = new ArrayList<>();
        chat.getMessages()
            .forEach(message -> messages.add(new JsonMessageData(message)));

        this.playerChatProperties = new HashSet<>();
        chat.getMembers()
            .forEach(member -> playerChatProperties.add(new JsonPlayerChatProperties(member)));
    }

    @Override
    public UUID getId() {
        return metaData.getId();
    }

    @Override
    public long getCreated() {
        return metaData.getCreated();
    }

    @Override
    public ChatType getChatType() {
        return ChatType.valueOf(metaData.getChatTypeString());
    }

    @Override
    public Set<PlayerChatProperties> getPlayerChatProperties(Chat chat) {
        return Set.of();
    }

    @Override
    public List<ChatMessage> getMessages(Chat chat) {
        return List.of();
    }
}
