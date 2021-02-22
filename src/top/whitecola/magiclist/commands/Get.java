package top.whitecola.magiclist.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import top.whitecola.annotations.ItsACommand;
import top.whitecola.commandhandler.ICommand;
import top.whitecola.magiclist.data.Whitelist;
import top.whitecola.magiclist.utils.MSGUtil;
import top.whitecola.magiclist.utils.WhiteListUtil;
import java.util.Arrays;
import java.util.List;
@ItsACommand(CommandNmae = "get",premission = "ml.get")
public class Get implements ICommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length!=2 || MSGUtil.hasNullString(args,sender,false))
            return false;

        Whitelist.WLPlayer wlp;

        if((wlp=WhiteListUtil.fastGetWLPlayerByQQ(args[1]))!=null)
            MSGUtil.sendPluginMessage(wlp.name+(wlp.uuid==null?"":"["+wlp.uuid+"]")+(wlp.qq==0?"未绑定QQ":"["+wlp.qq+"]"),sender);

        if((wlp=WhiteListUtil.getWLPlayer(args[1]))!=null)
            MSGUtil.sendPluginMessage(wlp.name+(wlp.uuid==null?"":"["+wlp.uuid+"]")+(wlp.qq==0?"未绑定QQ":"["+wlp.qq+"]"),sender);

        if(wlp==null)
            MSGUtil.sendPluginMessage("没有在白名单中找到 ["+args[1]+"] 对应的玩家.");

        return true;
    }

    @Override
    public @NotNull List<String> getArgs() {
        return Arrays.asList("[qq/id]");
    }

    @Override
    public @NotNull List<String> handleArg(CommandSender sender, String handleArg) {
        switch (handleArg){
            case "[qq/id]":
                return WhiteListUtil.getQQAndIDFromWhitelist();
            default:
                return Arrays.asList(handleArg);
        }
    }

    @Override
    public @NotNull String getUsage() {
        return "/ml get <qq/id>";
    }

    @Override
    public @NotNull String getUsageDescripition() {
        return "获取白名单信息 (注意:qq不能为0)";
    }
}
