package net.axxal.goteleport.Managers;

import net.axxal.goteleport.Classes.TeleportRequest;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeleportManager {

    public static float TeleportationDelay = 5000;
    private static final ArrayList<TeleportRequest> TeleportationRequests = new ArrayList<>();

    public static void CreateRequest(Player sender, Player target) {
        TeleportationRequests.add(new TeleportRequest(sender, target));
        target.sendRawMessage(sender.getName() + " has sent a teleportation request to you!");
    }

    public static void ExecuteRequest(TeleportRequest request) {
        for (int i = 0; i < TeleportationRequests.size(); i++) {
            if (TeleportationRequests.get(i) != request) continue;
            TeleportationRequests.get(i).run();
        }
    }

    public static ArrayList<TeleportRequest> GetActiveIncomingRequests(Player player) {
        ArrayList<TeleportRequest> activeIncomingRequests = new ArrayList<>();
        for (int i = 0; i < TeleportationRequests.size(); i++) {
            if (TeleportationRequests.get(i).target == player) {
                activeIncomingRequests.add(TeleportationRequests.get(i));
            }
        }
        return activeIncomingRequests;
    }

    public static boolean HasActiveOutgoingRequest(Player player) {
        for (int i = 0; i < TeleportationRequests.size(); i++) {
            if (TeleportationRequests.get(i).sender == player) {
                return true;
            }
        }
        return false;
    }

    public static boolean HasActiveIncomingRequest(Player player) {
        for (int i = 0; i < TeleportationRequests.size(); i++) {
            if (TeleportationRequests.get(i).target == player) {
                return true;
            }
        }
        return false;
    }

    public static void RemoveRequest(TeleportRequest request) {
        TeleportationRequests.remove(request);
    }
}
