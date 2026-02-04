package de.feli490.hytale.hyfechats.chat;

import de.feli490.hytale.hyfechats.chat.playerchatproperties.DisplayUnreadProperty;
import de.feli490.hytale.hyfechats.data.properties.EnumHyFeProperty;
import de.feli490.hytale.hyfechats.data.properties.HyFeProperty;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayerChatProperties {

    public static final String DISPLAY_UNREAD_PROPERTIES = "DisplayUnreadProperties";

    private final Chat chat;

    private final UUID playerId;
    private final long memberSince;

    private ChatRole role;

    private long lastRead;

    private final Map<String, HyFeProperty<?>> properties;

    public PlayerChatProperties(Chat chat, UUID playerId, long memberSince, ChatRole role, long lastRead) {
        this.chat = chat;
        this.playerId = playerId;
        this.memberSince = memberSince;

        this.role = role;

        this.lastRead = lastRead;

        List<HyFeProperty<?>> properties = List.of(new EnumHyFeProperty<>(DISPLAY_UNREAD_PROPERTIES, DisplayUnreadProperty.ALWAYS));
        this.properties = properties.stream()
                                    .collect(Collectors.toUnmodifiableMap(HyFeProperty::getKey, Function.identity()));
    }

    public PlayerChatProperties(Chat chat, UUID playerId, ChatRole role) {
        this(chat, playerId, System.currentTimeMillis(), role, System.currentTimeMillis());
    }

    public Chat getChat() {
        return chat;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public ChatRole getRole() {
        return role;
    }

    public void setRole(ChatRole role) {
        this.role = role;
    }

    public long getMemberSince() {
        return memberSince;
    }

    public long getLastRead() {
        return lastRead;
    }

    public void setLastRead(long lastRead) {
        this.lastRead = lastRead;
    }

    public void setProperty(String key, String value) {

        if (!properties.containsKey(key))
            throw new IllegalArgumentException("Property with key " + key + " does not exist!");

        properties.get(key)
                  .setValueFromString(value);
    }

    public DisplayUnreadProperty getDisplayUnreadProperty() {

        HyFeProperty<?> hyFeProperty = properties.get(DISPLAY_UNREAD_PROPERTIES);
        if (!(hyFeProperty instanceof EnumHyFeProperty<?> enumHyFeProperty))
            return DisplayUnreadProperty.ALWAYS;
        return (DisplayUnreadProperty) enumHyFeProperty.getValue();
    }

    @Override
    public String toString() {
        return "PlayerChatProperties{" + "chat=" + chat.getId() + ", playerId=" + playerId + ", memberSince=" + memberSince + ", role="
                + role + ", lastRead=" + lastRead + ", properties=" + properties + '}';
    }

    public Collection<HyFeProperty<?>> getProperties() {
        return properties.values();
    }
}
