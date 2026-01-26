package de.feli490.hytale.hyfechats.events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import de.feli490.hytale.hyfechats.PrivateChatManager;
import de.feli490.utils.hytale.message.MessageBuilderFactory;
import java.util.function.Consumer;

public class PlayerUnreadChatsOnJoinEvent implements Consumer<PlayerReadyEvent> {

    private final MessageBuilderFactory messageBuilderFactory;
    private final PrivateChatManager privateChatManager;

    public PlayerUnreadChatsOnJoinEvent(MessageBuilderFactory messageBuilderFactory, PrivateChatManager privateChatManager) {
        this.messageBuilderFactory = messageBuilderFactory;
        this.privateChatManager = privateChatManager;
    }

    @Override
    public void accept(PlayerReadyEvent playerReadyEvent) {

        Ref<EntityStore> refOfPlayer = playerReadyEvent.getPlayerRef();
        PlayerRef playerRef = refOfPlayer.getStore()
                                         .getComponent(refOfPlayer, PlayerRef.getComponentType());
        if (!privateChatManager.hasUnreadChat(playerRef.getUuid()))
            return;

        playerRef.sendMessage(messageBuilderFactory.main("[HyFeChats] You have unread chats! View chats with /chat"));
    }
}
