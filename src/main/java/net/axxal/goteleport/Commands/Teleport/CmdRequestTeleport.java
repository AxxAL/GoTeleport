package net.axxal.goteleport.Commands.Teleport;

import net.axxal.goteleport.Managers.TeleportManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CmdRequestTeleport implements CommandExecutor {

    private Player sender;
    private Player target;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        this.sender = (Player) sender;

        if (args.length == 0) {
            this.sender.sendRawMessage("No arguments provided...");
            return true;
        }

        if (TeleportManager.HasActiveOutgoingRequest(this.sender)) {
            this.sender.sendRawMessage("You already have an active request!");
            return true;
        }

        if (args[0].equals(this.sender.getName())) {
            this.sender.sendRawMessage("You can't teleport to yourself!");
            return true;
        }

        this.target = Bukkit.getPlayer(args[0]);

        TeleportManager.CreateRequest(this.sender, this.target);
        this.sender.sendRawMessage("Request was sent!");

        return true;
    }
}
