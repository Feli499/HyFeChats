package de.feli490.hytale.hyfechats.data.json.dataobjects;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import de.feli490.hytale.hyfechats.chat.Chat;
import de.feli490.hytale.hyfechats.chat.ChatType;
import de.feli490.hytale.hyfechats.data.json.JsonHyFePropertiesData;
import java.util.UUID;

public class JsonChatMetaData {

    public static Codec<JsonChatMetaData> CODEC = BuilderCodec.builder(JsonChatMetaData.class, JsonChatMetaData::new)
                                                              .addField(new KeyedCodec<>("Id", Codec.UUID_STRING),
                                                                        JsonChatMetaData::setId,
                                                                        JsonChatMetaData::getId)
                                                              .addField(new KeyedCodec<>("ChatType", Codec.STRING),
                                                                        JsonChatMetaData::setChatTypeString,
                                                                        JsonChatMetaData::getChatTypeString)
                                                              .addField(new KeyedCodec<>("Created", Codec.LONG),
                                                                        JsonChatMetaData::setCreated,
                                                                        JsonChatMetaData::getCreated)
                                                              .addField(new KeyedCodec<>("Properties", JsonHyFePropertiesData.ARRAY_CODEC),
                                                                        JsonChatMetaData::setProperties,
                                                                        JsonChatMetaData::getProperties)
                                                              .build();

    private UUID id;
    private ChatType chatType;
    private long created;
    private JsonHyFePropertiesData[] properties;

    private JsonChatMetaData() {
        properties = new JsonHyFePropertiesData[0];
    }

    public JsonChatMetaData(Chat chat) {
        this.id = chat.getId();
        this.chatType = chat.getChatType();
        this.created = chat.getCreated();
        this.properties = chat.getProperties()
                              .stream()
                              .map(JsonHyFePropertiesData::new)
                              .toArray(JsonHyFePropertiesData[]::new);
    }

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    public long getCreated() {
        return created;
    }

    private void setCreated(long created) {
        this.created = created;
    }

    public String getChatTypeString() {
        return chatType.name();
    }

    private void setChatTypeString(String chatType) {
        this.chatType = ChatType.valueOf(chatType);
    }

    public JsonHyFePropertiesData[] getProperties() {
        return properties;
    }

    private void setProperties(JsonHyFePropertiesData[] properties) {
        this.properties = properties;
    }
}
