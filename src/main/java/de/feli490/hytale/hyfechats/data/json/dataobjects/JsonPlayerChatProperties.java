package de.feli490.hytale.hyfechats.data.json.dataobjects;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.util.RawJsonReader;
import com.hypixel.hytale.logger.HytaleLogger;
import de.feli490.hytale.hyfechats.chat.Chat;
import de.feli490.hytale.hyfechats.chat.ChatRole;
import de.feli490.hytale.hyfechats.chat.PlayerChatProperties;
import de.feli490.hytale.hyfechats.data.json.JsonChatFolderContainer;
import de.feli490.hytale.hyfechats.data.json.JsonHyFePropertiesData;
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
                                                                      .addField(new KeyedCodec<>("Properties",
                                                                                                 JsonHyFePropertiesData.ARRAY_CODEC),
                                                                                JsonPlayerChatProperties::setProperties,
                                                                                JsonPlayerChatProperties::getProperties)
                                                                      .build();

    private UUID playerId;
    private long memberSince;
    private ChatRole role;

    private long lastRead;

    private JsonHyFePropertiesData[] properties;

    private JsonPlayerChatProperties() {
        lastRead = System.currentTimeMillis();
        properties = new JsonHyFePropertiesData[0];
    }

    public JsonPlayerChatProperties(PlayerChatProperties playerChatProperties) {
        this.playerId = playerChatProperties.getPlayerId();
        this.role = playerChatProperties.getRole();
        this.memberSince = playerChatProperties.getMemberSince();
        this.lastRead = playerChatProperties.getLastRead();
        this.properties = playerChatProperties.getProperties()
                                              .stream()
                                              .map(JsonHyFePropertiesData::new)
                                              .toArray(JsonHyFePropertiesData[]::new);
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

    public long getLastRead() {
        return lastRead;
    }

    private void setLastRead(long lastRead) {
        this.lastRead = lastRead;
    }

    public JsonHyFePropertiesData[] getProperties() {
        return properties;
    }

    private void setProperties(JsonHyFePropertiesData[] properties) {
        this.properties = properties;
    }

    public PlayerChatProperties toPlayerChatProperties(Chat chat) {

        PlayerChatProperties playerChatProperties = new PlayerChatProperties(chat, playerId, memberSince, role, lastRead);
        for (JsonHyFePropertiesData property : properties) {
            playerChatProperties.setProperty(property.getKey(), property.getValue());
        }
        return playerChatProperties;
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
