package de.feli490.hytale.hyfechats.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.CustomUIPage;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import de.feli490.hytale.hyfechats.PrivateChatManager;
import de.feli490.hytale.hyfechats.gui.PrivateChatsUI;
import de.feli490.utils.hytale.playerdata.PlayerDataProvider;
import de.feli490.utils.hytale.utils.CommandUtils;
import java.util.concurrent.CompletableFuture;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class ChatCommand extends AbstractAsyncCommand {
    private final PrivateChatManager chatManager;
    private final PlayerDataProvider playerDataProvider;

    public ChatCommand(PrivateChatManager chatManager, PlayerDataProvider playerDataProvider) {
        super("chat", "Opens the chat menu");

        this.chatManager = chatManager;
        this.playerDataProvider = playerDataProvider;

        addSubCommand(new ChatCreateCommand(chatManager, playerDataProvider));

        requirePermission("hyfechats.chat");
    }

    @NonNullDecl
    @Override
    protected CompletableFuture<Void> executeAsync(@NonNullDecl CommandContext commandContext) {
        Player player = CommandUtils.getPlayerFromContext(commandContext);

        return CommandUtils.getPlayerRefFuture(commandContext)
                           .thenAccept(playerRef -> {

                               CustomUIPage customPage = player.getPageManager()
                                                               .getCustomPage();
                               if (customPage != null)
                                   return;

                               Ref<EntityStore> reference = playerRef.getReference();

                               PrivateChatsUI privateChatsUI = new PrivateChatsUI(playerRef,
                                                                                  CustomPageLifetime.CanDismiss,
                                                                                  chatManager,
                                                                                  playerDataProvider);

                               player.getPageManager()
                                     .openCustomPage(reference, reference.getStore(), privateChatsUI);
                           });
    }
}
