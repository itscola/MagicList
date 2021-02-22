package top.whitecola.magiclist.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import top.whitecola.annotations.ItsACommand;
import top.whitecola.commandhandler.ICommand;
import top.whitecola.magiclist.MagicList;
import top.whitecola.magiclist.data.Whitelist;
import top.whitecola.magiclist.utils.MSGUtil;
import top.whitecola.magiclist.utils.WhiteListUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ItsACommand(CommandNmae = "nremove",premission = "ml.nremove")
public class NRemove implements ICommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length!=2 || MSGUtil.hasNullString(args,sender,false))
            return false;
        Whitelist.WLPlayer wlp;
        if((wlp=WhiteListUtil.getWLPlayer(args[1]))==null){
            MSGUtil.sendPluginMessage("操作失败, 没有在白名单中找到ID为"+args[1]+"的玩家.",sender);
            return true;
        }
        WhiteListUtil.fastRemove(sender,wlp);
        return true;
    }

    @Override
    public @NotNull List<String> getArgs() {
        return Arrays.asList("[PlayerName]");
    }

    @Override
    public @NotNull List<String> handleArg(CommandSender sender, String handleArg) {
        switch (handleArg) {
            case "[PlayerName]":
                return MagicList.instance.whitelist.getConfig().players.stream().map(i->i.name).collect(Collectors.toList());
            default:
                return Arrays.asList(handleArg);
        }
    }

    @Override
    public @NotNull String getUsage() {
        return "/ml nremove <id>";
    }

    @Override
    public @NotNull String getUsageDescripition() {
        return "移除对应id玩家的白名单.";
    }
}
