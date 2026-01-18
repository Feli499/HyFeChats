package de.feli490.hytale.privatechats;

import de.feli490.hytale.privatechats.chat.Chat;
import de.feli490.hytale.privatechats.chat.ChatFactory;
import de.feli490.hytale.privatechats.chat.ChatType;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class PrivateChatManager {

    private final Set<Chat> chats;
    private final ChatFactory chatFactory;

    public PrivateChatManager(ChatFactory chatFactory) {
        this.chatFactory = chatFactory;
        chats = new HashSet<>();
    }

    public Chat createGroupChat(UUID owner) {
        Chat group = chatFactory.createGroup(owner);
        chats.add(group);
        return group;
    }

    public Chat createDirectChat(UUID player1, UUID player2) {

        Optional<Chat> first = chats.stream()
                                    .filter(chat -> chat.isMember(player1) && chat.isMember(player2)
                                            && ChatType.DIRECT.equals(chat.getChatType()))
                                    .findFirst();
        if (first.isPresent())
            return first.get();

        Chat direct = chatFactory.createDirect(player1, player2);
        chats.add(direct);
        return direct;
    }

    public List<Chat> getSortedChats(UUID playerId) {
        return chats.stream()
                    .filter(chat -> chat.isMember(playerId))
                    .sorted(Comparator.comparingLong(Chat::getLastUpdate))
                    .toList();
    }

}
