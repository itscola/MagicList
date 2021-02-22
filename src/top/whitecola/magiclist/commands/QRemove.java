package top.whitecola.magiclist.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import top.whitecola.annotations.ItsACommand;
import top.whitecola.commandhandler.ICommand;
import top.whitecola.magiclist.data.Whitelist;
import top.whitecola.magiclist.utils.MSGUtil;
import top.whitecola.magiclist.utils.NumberUtils;
import top.whitecola.magiclist.utils.WhiteListUtil;

import java.util.Arrays;
import java.util.List;

@ItsACommand(CommandNmae = "qremove",premission = "ml.qremove")
public class QRemove implements ICommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length!=2 || MSGUtil.hasNullString(args,sender,false))
            return false;
        Whitelist.WLPlayer wlp;
        if(!NumberUtils.isNumber(args[1])){
            return false;
        }
        if((wlp=WhiteListUtil.fastGetWLPlayerByQQ(args[1]))==null){
            MSGUtil.sendPluginMessage("操作失败, 没有在白名单中找到QQ为"+args[1]+"的玩家.");
            return true;

        }
        WhiteListUtil.fastRemove(sender,wlp);
        return true;

    }

    @Override
    public @NotNull List<String> getArgs() {
        return Arrays.asList("[qq]");
    }

    @Override
    public @NotNull List<String> handleArg(CommandSender sender, String handleArg) {
        return null;
    }

    @Override
    public @NotNull String getUsage() {
        return "/ml qremove <qq>";
    }

    @Override
    public @NotNull String getUsageDescripition() {
        return "删除对应QQ的玩家 (注意:QQ号不能为0)";
    }
}
