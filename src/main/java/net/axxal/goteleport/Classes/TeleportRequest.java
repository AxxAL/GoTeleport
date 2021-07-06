package net.axxal.goteleport.Classes;

import net.axxal.goteleport.Managers.TeleportManager;
import org.bukkit.entity.Player;

public class TeleportRequest implements Runnable {

    public Player sender;
    public Player target;

    public TeleportRequest(Player sender, Player target) {
        this.sender = sender;
        this.target = target;
    }

    @Override
    public void run() {
        try {
            for (int i = ((int) TeleportManager.TeleportationDelay / 1000); i > 0; i--) {
                this.sender.sendRawMessage("Teleporting you in: " + i + "...");
                Thread.sleep(1000);
            }
            this.sender.teleport(this.target);
            this.sender.sendRawMessage("You have been teleported to " + this.target.getName() + ".");
            this.target.sendRawMessage(this.sender.getName() + " was teleported to you.");
            TeleportManager.RemoveRequest(this);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
