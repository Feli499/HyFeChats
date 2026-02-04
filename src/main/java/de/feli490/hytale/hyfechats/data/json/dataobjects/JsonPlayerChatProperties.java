package de.feli490.hytale.hyfechats.data.json.dataobjects;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.util.RawJsonReader;
import com.hypixel.hytale.logger.HytaleLogger;
import de.feli490.hytale.hyfechats.chat.Chat;
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
                                                                      .addField(new KeyedCodec<>("Properties",
                                                                                                 JsonHyFePropertiesData.ARRAY_CODEC),
                                                                                JsonPlayerChatProperties::setProperties,
                                                                                JsonPlayerChatProperties::getProperties)
                                                                      .build();

    private UUID playerId;
    private JsonHyFePropertiesData[] properties;

    private JsonPlayerChatProperties() {
        properties = new JsonHyFePropertiesData[0];
    }

    public JsonPlayerChatProperties(PlayerChatProperties playerChatProperties) {
        this.playerId = playerChatProperties.getPlayerId();
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

    public JsonHyFePropertiesData[] getProperties() {
        return properties;
    }

    private void setProperties(JsonHyFePropertiesData[] properties) {
        this.properties = properties;
    }

    public PlayerChatProperties toPlayerChatProperties(Chat chat) {

        PlayerChatProperties playerChatProperties = new PlayerChatProperties(chat, playerId);
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
