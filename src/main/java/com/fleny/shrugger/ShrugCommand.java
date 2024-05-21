package com.fleny.shrugger;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

public class ShrugCommand {
    private static final String SHRUG = "¯\\_(ツ)_/¯";

    public ShrugCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("shrug").executes(this::execute));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        PlayerList playerList = context.getSource().getServer().getPlayerList();

        if (player == null) {
            playerList.broadcastChatMessage(
                    PlayerChatMessage.system(SHRUG),
                    context.getSource(),
                    ChatType.bind(ChatType.SAY_COMMAND, context.getSource())
            );
        }
        else {
            playerList.broadcastChatMessage(
                    PlayerChatMessage.unsigned(player.getUUID(), SHRUG),
                    player,
                    ChatType.bind(ChatType.CHAT, player)
            );
        }

        return 1;
    }
}
