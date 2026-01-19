package de.feli490.hytale.hyfechats.chat.listeners;

import de.feli490.hytale.hyfechats.chat.ChatRole;
import de.feli490.hytale.hyfechats.chat.PlayerChatRole;

public interface MemberChangedListener {

    default void onMemberAdded(PlayerChatRole member) {}

    default void onMemberRoleChanged(PlayerChatRole newRole, ChatRole oldRole) {}

    default void onMemberRemoved(PlayerChatRole member) {}

}
