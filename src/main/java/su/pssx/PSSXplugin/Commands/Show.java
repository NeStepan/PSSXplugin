package su.pssx.PSSXplugin.Commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.entity.Player;
import su.pssx.PSSXplugin.Libs.hideLib;

import java.util.List;

public class Show {

    public static int showCommand(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {

        final PlayerSelectorArgumentResolver resolver = (PlayerSelectorArgumentResolver) ctx.getArgument("target", PlayerSelectorArgumentResolver.class);
        final List<Player> targets = resolver.resolve(ctx.getSource());

        for (Player target : targets)
            hideLib.showPlayer(target);

        return 0;
    }
}
