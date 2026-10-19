package su.pssx.PSSXplugin.Commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.entity.Player;
import su.pssx.PSSXplugin.Libs.hideLib;
import su.pssx.PSSXplugin.PSSXplugin;

import java.util.List;

public class Hide {

    public static int hideCommand(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {

        final PlayerSelectorArgumentResolver resolver = (PlayerSelectorArgumentResolver) ctx.getArgument("target", PlayerSelectorArgumentResolver.class);
        final List<Player> targets = resolver.resolve(ctx.getSource());

        for (Player target : targets)
            hideLib.hidePlayer(target, PSSXplugin.getPlugin().getConfig().getStringList("hideFrom"));

        return 0;
    }

}
