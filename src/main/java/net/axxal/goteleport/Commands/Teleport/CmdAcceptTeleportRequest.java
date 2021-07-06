package net.axxal.goteleport.Commands.Teleport;

import net.axxal.goteleport.Classes.TeleportRequest;
import net.axxal.goteleport.Managers.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CmdAcceptTeleportRequest implements CommandExecutor {

    Player sender;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        this.sender = (Player) sender;
        if (!TeleportManager.HasActiveIncomingRequest(this.sender)) {
            this.sender.sendRawMessage("You have no active incoming requests!");
            return true;
        }

        ArrayList<TeleportRequest> incomingRequests = TeleportManager.GetActiveIncomingRequests(this.sender);

        if (incomingRequests.size() <= 1) {
            TeleportManager.ExecuteRequest(incomingRequests.get(0));
            return true;
        } else {
            this.sender.sendRawMessage("You have several incoming requests. Please specify a number from this list.");
            for (int i = 0; i < incomingRequests.size(); i++) {
                this.sender.sendRawMessage(i + ": " + incomingRequests.get(i).sender.getName());
            }
        }
        if (args.length != 0) {
            try {
                int parsedInteger = Integer.parseInt(args[0]);
                TeleportManager.ExecuteRequest(incomingRequests.get(parsedInteger));
            } catch (Exception e) {
                this.sender.sendRawMessage(args[0] + " is not a valid input...");
            }
        }
        return true;
    }
}
