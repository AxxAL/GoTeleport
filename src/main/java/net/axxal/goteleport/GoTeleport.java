package net.axxal.goteleport;

import net.axxal.goteleport.Commands.Teleport.CmdAcceptTeleportRequest;
import net.axxal.goteleport.Commands.Teleport.CmdRequestTeleport;
import org.bukkit.plugin.java.JavaPlugin;

public final class GoTeleport extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Plugin enabled!");
        getCommand("tpr").setExecutor(new CmdRequestTeleport());
        getCommand("tpaccept").setExecutor(new CmdAcceptTeleportRequest());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
