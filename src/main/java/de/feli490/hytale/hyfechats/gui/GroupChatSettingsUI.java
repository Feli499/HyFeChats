package de.feli490.hytale.hyfechats.gui;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import de.feli490.hytale.hyfechats.chat.Chat;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class GroupChatSettingsUI extends InteractiveCustomUIPage<GroupChatSettingsUI.GroupChatSettingsData> {

    public GroupChatSettingsUI(@NonNullDecl PlayerRef playerRef, @NonNullDecl CustomPageLifetime lifetime,
            @NonNullDecl BuilderCodec<GroupChatSettingsData> eventDataCodec, Chat chat) {
        super(playerRef, lifetime, eventDataCodec);
    }

    @Override
    public void build(@NonNullDecl Ref<EntityStore> ref, @NonNullDecl UICommandBuilder uiCommandBuilder,
            @NonNullDecl UIEventBuilder uiEventBuilder, @NonNullDecl Store<EntityStore> store) {

    }

    public static class GroupChatSettingsData {

        static final String GROUP_CHAT_NAME_KEY = "GroupChatName";

        public static final BuilderCodec<GroupChatSettingsData> CODEC = BuilderCodec.builder(GroupChatSettingsData.class,
                                                                                             GroupChatSettingsData::new)
                                                                                    .addField(new KeyedCodec<>(GROUP_CHAT_NAME_KEY,
                                                                                                               Codec.STRING),
                                                                                              GroupChatSettingsData::setGroupChatName,
                                                                                              GroupChatSettingsData::getGroupChatName)
                                                                                    .build();
        private String groupChatName;

        private GroupChatSettingsData() {}

        public String getGroupChatName() {
            return groupChatName;
        }

        private void setGroupChatName(String groupChatName) {
            this.groupChatName = groupChatName;
        }
    }
}
