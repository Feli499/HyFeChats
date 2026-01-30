package de.feli490.hytale.hyfechats.data.json.dataobjects;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.util.RawJsonReader;
import com.hypixel.hytale.logger.HytaleLogger;
import de.feli490.hytale.hyfechats.chat.Chat;
import de.feli490.hytale.hyfechats.chat.ChatRole;
import de.feli490.hytale.hyfechats.chat.PlayerChatProperties;
import de.feli490.hytale.hyfechats.chat.playerchatproperties.DisplayUnreadProperty;
import de.feli490.hytale.hyfechats.data.json.JsonChatFolderContainer;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class JsonPlayerChatProperties {

    public static Codec<JsonPlayerChatProperties> CODEC = BuilderCodec.builder(JsonPlayerChatProperties.class,
                                                                               JsonPlayerChatProperties::new)
                                                                      .addField(new KeyedCodec<>("PlayerId", Codec.UUID_STRING),
                                                                                JsonPlayerChatProperties::setPlayerId,
                                                                                JsonPlayerChatProperties::getPlayerId)
                                                                      .addField(new KeyedCodec<>("MemberSince", Codec.LONG),
                                                                                JsonPlayerChatProperties::setMemberSince,
                                                                                JsonPlayerChatProperties::getMemberSince)
                                                                      .addField(new KeyedCodec<>("Role", Codec.STRING),
                                                                                JsonPlayerChatProperties::setRole,
                                                                                JsonPlayerChatProperties::getRole)
                                                                      .addField(new KeyedCodec<>("LastRead", Codec.LONG),
                                                                                JsonPlayerChatProperties::setLastRead,
                                                                                JsonPlayerChatProperties::getLastRead)
                                                                      .addField(new KeyedCodec<>("DisplayUnread", Codec.STRING),
                                                                                JsonPlayerChatProperties::setDisplayUnreadProperty,
                                                                                JsonPlayerChatProperties::getDisplayUnreadPropertyString)
                                                                      .build();

    private UUID playerId;
    private long memberSince;
    private ChatRole role;

    private long lastRead;
    private DisplayUnreadProperty displayUnreadProperty;

    private JsonPlayerChatProperties() {
        lastRead = System.currentTimeMillis();
        displayUnreadProperty = DisplayUnreadProperty.ALWAYS;
    }

    public JsonPlayerChatProperties(PlayerChatProperties playerChatProperties) {
        this.playerId = playerChatProperties.getPlayerId();
        this.role = playerChatProperties.getRole();
        this.memberSince = playerChatProperties.getMemberSince();
        this.lastRead = playerChatProperties.getLastRead();
        this.displayUnreadProperty = playerChatProperties.getDisplayUnread();
    }

    public UUID getPlayerId() {
        return playerId;
    }

    private void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public String getRole() {
        return role.name();
    }

    private void setRole(String role) {
        this.role = ChatRole.valueOf(role);
    }

    public long getMemberSince() {
        return memberSince;
    }

    private void setMemberSince(long memberSince) {
        this.memberSince = memberSince;
    }

    public String getDisplayUnreadPropertyString() {
        return displayUnreadProperty.name();
    }

    public DisplayUnreadProperty getDisplayUnreadProperty() {
        return displayUnreadProperty;
    }

    private void setDisplayUnreadProperty(String displayUnreadPropertyString) {
        this.displayUnreadProperty = DisplayUnreadProperty.valueOf(displayUnreadPropertyString);
    }

    private void setDisplayUnreadProperty(DisplayUnreadProperty displayUnreadProperty) {
        this.displayUnreadProperty = displayUnreadProperty;
    }

    public long getLastRead() {
        return lastRead;
    }

    private void setLastRead(long lastRead) {
        this.lastRead = lastRead;
    }

    public PlayerChatProperties toPlayerChatProperties(Chat chat) {
        return new PlayerChatProperties(chat, playerId, memberSince, role, lastRead, displayUnreadProperty);
    }

    public static Set<JsonPlayerChatProperties> loadFromChatDirectory(JsonChatFolderContainer jsonChatFolderContainer, HytaleLogger logger)
            throws IOException {

        Path playerChatPropertiesDirectory = jsonChatFolderContainer.getPlayerChatPropertiesDirectory();
        Set<JsonPlayerChatProperties> jsonPlayerChatPropertySet = new HashSet<>();
        try (DirectoryStream<Path> playerChatPropertyFiles = Files.newDirectoryStream(playerChatPropertiesDirectory, "*.json")) {
            for (Path playerChatPropertyFile : playerChatPropertyFiles) {
                JsonPlayerChatProperties jsonPlayerChatProperties = RawJsonReader.readSync(playerChatPropertyFile,
                                                                                           JsonPlayerChatProperties.CODEC,
                                                                                           logger);
                jsonPlayerChatPropertySet.add(jsonPlayerChatProperties);
            }
        }

        return jsonPlayerChatPropertySet;
    }
}
