package su.pssx.PSSXplugin.Libs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import su.pssx.PSSXplugin.PSSXplugin;

import java.util.List;

public class hideLib {

    public static void hidePlayer( Player plr, List<String> hideFromList){
        for (Player hostPlr : Bukkit.getOnlinePlayers()){
            if(hideFromList.contains(hostPlr.getName())){
                hostPlr.hidePlayer(PSSXplugin.getPlugin(),plr);
            }
        }
    }

    public static void hidePlayer(Player plr){
        for (Player hostPlr : Bukkit.getOnlinePlayers()){
            hostPlr.hidePlayer(PSSXplugin.getPlugin(),plr);
        }
    }

    public static void showPlayer(Player plr){
        for (Player hostPlr : Bukkit.getOnlinePlayers()){
            hostPlr.showPlayer(PSSXplugin.getPlugin(),plr);
        }
    }

}
