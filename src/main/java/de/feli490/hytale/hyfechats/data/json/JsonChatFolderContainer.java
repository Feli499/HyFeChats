package de.feli490.hytale.hyfechats.data.json;

import de.feli490.hytale.hyfechats.chat.Chat;
import java.nio.file.Path;
import java.util.UUID;

public class JsonChatFolderContainer {

    private final Path path;

    public JsonChatFolderContainer(Path path) {
        this.path = path;
    }

    public Path getMessagesFile() {
        return path.resolve("messages.dat");
    }

    public Path getMetaDataFile() {
        return path.resolve("metadata.json");
    }

    public Path getPlayerChatPropertyFile(UUID playerId) {
        return getPlayerChatPropertiesDirectory().resolve(playerId.toString() + ".json");
    }

    public Path getPlayerChatPropertiesDirectory() {
        return path.resolve("members");
    }

    public static JsonChatFolderContainer of(Path chatsDirectory, Chat chat) {
        return new JsonChatFolderContainer(chatsDirectory.resolve(chat.getId()
                                                                      .toString()));
    }
}
