package top.whitecola.magiclist.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import top.whitecola.annotations.ItsACommand;
import top.whitecola.commandhandler.ICommand;
import top.whitecola.magiclist.MagicList;
import top.whitecola.magiclist.utils.MSGUtil;

import java.util.Arrays;
import java.util.List;

@ItsACommand(CommandNmae = "reload",premission = "ml.reload")
public class Reload implements ICommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(MSGUtil.hasNullString(args,sender,false) || MSGUtil.hasNullString(args,sender,false)){
            return true;
        }
        MagicList.instance.whitelist.reloadConfig();
        MagicList.instance.config.reloadConfig();
        MagicList.instance.language.reloadConfig();
        return true;
    }

    @Override
    public @NotNull List<String> getArgs() {
        return Arrays.asList("");
    }

    @Override
    public @NotNull List<String> handleArg(CommandSender sender, String handleArg) {
        return Arrays.asList(handleArg);
    }

    @Override
    public @NotNull String getUsage() {
        return "/ml reload";
    }

    @Override
    public @NotNull String getUsageDescripition() {
        return "÷ÿ‘ÿ≈‰÷√Œƒº˛.";
    }
}
