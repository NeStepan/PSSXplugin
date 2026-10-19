package su.pssx.PSSXplugin;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import io.papermc.paper.event.player.AsyncChatEvent;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Level;

public final class PSSXplugin extends JavaPlugin implements Listener {

    public boolean chat = true;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        //commands
        LiteralArgumentBuilder<CommandSourceStack> root = Commands.literal("event").requires(source -> source.getSender().isOp());
        root.then(Commands.literal("hide")
                .then(Commands.argument("target", ArgumentTypes.players())
                    .executes(this::hideCommand)));

        root.then(Commands.literal("show")
                .then(Commands.argument("target", ArgumentTypes.players())
                    .executes(this::showCommand)));

        root.then(Commands.literal("chat").executes(ctx -> {chat = !chat; return 0;}));
        LiteralCommandNode<CommandSourceStack> buildCommand = root.build();

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(buildCommand);
        });

        //events
        getServer().getPluginManager().registerEvents(this, this);



        getLogger().log(Level.INFO,"Funny X1miya plugin started!");
    }

    public int hideCommand(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {

        final PlayerSelectorArgumentResolver resolver = (PlayerSelectorArgumentResolver) ctx.getArgument("target", PlayerSelectorArgumentResolver.class);
        final List<Player> targets = resolver.resolve(ctx.getSource());

        for (Player target : targets)
            hideLib.hidePlayer(this,target,this.getConfig().getStringList("hideFrom"));

        return 0;
    }

    public int showCommand(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {

        final PlayerSelectorArgumentResolver resolver = (PlayerSelectorArgumentResolver) ctx.getArgument("target", PlayerSelectorArgumentResolver.class);
        final List<Player> targets = resolver.resolve(ctx.getSource());

        for (Player target : targets)
            hideLib.showPlayer(this,target);

        return 0;
    }

    @EventHandler
    public void plrJoin(PlayerJoinEvent ev){
        Player plr = ev.getPlayer();

        if(this.getConfig().getBoolean("hideOnJoin") && !this.getConfig().getStringList("showOnJoin").contains(plr.getName())){
            hideLib.hidePlayer(this,plr,this.getConfig().getStringList("hideFrom"));
            ev.joinMessage(null);
            for (Player p : Bukkit.getOnlinePlayers()){
                if(p.isOp()){
                    p.sendMessage(plr.getName()+" joined the server!");
                }
            }
        }

    }

    @EventHandler
    public void plrJoin(PlayerQuitEvent ev){
        Player plr = ev.getPlayer();

        if(this.getConfig().getBoolean("hideOnJoin") && !this.getConfig().getStringList("showOnJoin").contains(plr.getName())){
            hideLib.hidePlayer(this,plr,this.getConfig().getStringList("hideFrom"));
            ev.quitMessage(null);
            for (Player p : Bukkit.getOnlinePlayers()){
                if(p.isOp()){
                    p.sendMessage(plr.getName()+" quited the server!");
                }
            }
        }

    }

    @EventHandler
    public void chat(AsyncChatEvent ev){
        ev.setCancelled(!chat);
    }

    @EventHandler
    public void status(PaperServerListPingEvent ev){
        ev.setNumPlayers(1);
    }

}
