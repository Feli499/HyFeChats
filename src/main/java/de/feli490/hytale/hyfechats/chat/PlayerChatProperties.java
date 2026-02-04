package de.feli490.hytale.hyfechats.chat;

import de.feli490.hytale.hyfechats.chat.playerchatproperties.DisplayUnreadProperty;
import de.feli490.hytale.hyfechats.data.properties.EnumHyFeProperty;
import de.feli490.hytale.hyfechats.data.properties.HyFeProperty;
import de.feli490.hytale.hyfechats.data.properties.LongHyFeProperty;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayerChatProperties {

    public static final String MEMBER_SINCE_PROPERTY = "MemberSinceProperty";
    public static final String CHAT_ROLE_PROPERTY = "ChatRoleProperty";
    public static final String LAST_READ_PROPERTY = "LastReadProperty";
    public static final String DISPLAY_UNREAD_PROPERTY = "DisplayUnreadProperty";

    private final Chat chat;

    private final UUID playerId;

    private final Map<String, HyFeProperty<?>> properties;

    public PlayerChatProperties(Chat chat, UUID playerId) {
        this.chat = chat;
        this.playerId = playerId;

        List<HyFeProperty<?>> properties = List.of(//
                new LongHyFeProperty(MEMBER_SINCE_PROPERTY, System.currentTimeMillis()),//
                new EnumHyFeProperty<>(CHAT_ROLE_PROPERTY, ChatRole.MEMBER),//
                new LongHyFeProperty(LAST_READ_PROPERTY, System.currentTimeMillis()),//
                new EnumHyFeProperty<>(DISPLAY_UNREAD_PROPERTY, DisplayUnreadProperty.ALWAYS)//
        );
        this.properties = properties.stream()
                                    .collect(Collectors.toUnmodifiableMap(HyFeProperty::getKey, Function.identity()));
    }

    public Chat getChat() {
        return chat;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public ChatRole getRole() {
        HyFeProperty<?> hyFeProperty = properties.get(CHAT_ROLE_PROPERTY);
        if (!(hyFeProperty instanceof EnumHyFeProperty<?> enumHyFeProperty))
            return ChatRole.MEMBER;
        return (ChatRole) hyFeProperty.getValue();
    }

    public void setRole(ChatRole role) {
        setProperty(CHAT_ROLE_PROPERTY, role.name());
    }

    public long getMemberSince() {
        HyFeProperty<?> hyFeProperty = properties.get(MEMBER_SINCE_PROPERTY);
        if (!(hyFeProperty instanceof LongHyFeProperty longHyFeProperty))
            return System.currentTimeMillis();
        return longHyFeProperty.getValue();
    }

    public long getLastRead() {
        HyFeProperty<?> hyFeProperty = properties.get(LAST_READ_PROPERTY);
        if (!(hyFeProperty instanceof LongHyFeProperty longHyFeProperty))
            return System.currentTimeMillis();
        return longHyFeProperty.getValue();
    }

    public void setLastRead(long lastRead) {
        setProperty(LAST_READ_PROPERTY, Long.toString(lastRead));
    }

    public void setProperty(String key, String value) {

        if (!properties.containsKey(key))
            throw new IllegalArgumentException("Property with key " + key + " does not exist!");

        properties.get(key)
                  .setValueFromString(value);
    }

    public Collection<HyFeProperty<?>> getProperties() {
        return properties.values();
    }

    public DisplayUnreadProperty getDisplayUnreadProperty() {

        HyFeProperty<?> hyFeProperty = properties.get(DISPLAY_UNREAD_PROPERTY);
        if (!(hyFeProperty instanceof EnumHyFeProperty<?> enumHyFeProperty))
            return DisplayUnreadProperty.ALWAYS;
        return (DisplayUnreadProperty) enumHyFeProperty.getValue();
    }

    @Override
    public String toString() {
        return "PlayerChatProperties{" + "chat=" + chat + ", playerId=" + playerId + ", properties=" + properties + '}';
    }
}
