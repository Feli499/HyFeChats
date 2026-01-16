package de.feli490.hytale.privatechats.gui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.player.pages.CustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import de.feli490.hytale.privatechats.PrivateChatManager;
import de.feli490.hytale.privatechats.chat.Chat;
import de.feli490.hytale.privatechats.chat.ChatMessage;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class PrivateChatsUI extends CustomUIPage {

    private final PrivateChatManager chatManager;

    public PrivateChatsUI(@NonNullDecl PlayerRef playerRef, @NonNullDecl CustomPageLifetime lifetime, PrivateChatManager chatManager) {
        super(playerRef, lifetime);

        this.chatManager = chatManager;
    }

    @Override
    public void build(@NonNullDecl Ref<EntityStore> ref, @NonNullDecl UICommandBuilder uiCommandBuilder,
            @NonNullDecl UIEventBuilder uiEventBuilder, @NonNullDecl Store<EntityStore> store) {

        uiCommandBuilder.append("PrivateChats.ui");
        updateChatMessages();
    }

    public void updateChatMessages() {

        UICommandBuilder uiCommandBuilder = new UICommandBuilder();

        uiCommandBuilder.clear("#ChatPreviews");
        for (Chat chat : chatManager.getSortedChats(playerRef.getUuid())) {

            String previewText = "No messages yet.";

            ChatMessage lastMessage = chat.getLastMessage();
            if (lastMessage != null)
                previewText = lastMessage.message();

            uiCommandBuilder.append("#ChatPreviews[" + chat.getId() + "] #hatName.Text", chat.getChatName());
            uiCommandBuilder.append("#ChatPreviews[" + chat.getId() + "] #MessagePreview.Text", previewText);
        }

        sendUpdate(uiCommandBuilder, false);
    }
}
