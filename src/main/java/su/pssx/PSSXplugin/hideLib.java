package su.pssx.PSSXplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class hideLib {

    public static void hidePlayer(JavaPlugin plugin, Player plr, List<String> hideFromList){
        for (Player hostPlr : Bukkit.getOnlinePlayers()){
            if(hideFromList.contains(hostPlr.getName())){
                hostPlr.hidePlayer(plugin,plr);
            }
        }
    }

    public static void hidePlayer(JavaPlugin plugin, Player plr){
        for (Player hostPlr : Bukkit.getOnlinePlayers()){
            hostPlr.hidePlayer(plugin,plr);
        }
    }

    public static void showPlayer(JavaPlugin plugin, Player plr){
        for (Player hostPlr : Bukkit.getOnlinePlayers()){
            hostPlr.showPlayer(plugin,plr);
        }
    }

}
