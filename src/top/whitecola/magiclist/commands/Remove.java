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

@ItsACommand(CommandNmae = "remove",premission = "ml.remove")
public class Remove implements ICommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length!=2 || MSGUtil.hasNullString(args,sender,false))
            return false;
        Whitelist.WLPlayer wlp;
        if(((wlp=WhiteListUtil.fastGetWLPlayerByQQ(args[1]))==null) && (wlp=WhiteListUtil.getWLPlayer(args[1]))==null){
            MSGUtil.sendPluginMessage("����ʧ��,û���ڰ��������ҵ� ["+args[1]+"] ��Ӧ�����.");
            return true;
        }
        WhiteListUtil.fastRemove(sender,wlp);
        return true;
    }

    @Override
    public @NotNull List<String> getArgs() {
        return Arrays.asList("[qq/id]");
    }

    @Override
    public @NotNull List<String> handleArg(CommandSender sender, String handleArg) {
        switch (handleArg) {
            case "[qq/id]":
                return WhiteListUtil.getQQAndIDFromWhitelist();
            default:
                return Arrays.asList(handleArg);
        }
    }

    @Override
    public @NotNull String getUsage() {
        return "/ml remove <qq/PlayerName>";
    }

    @Override
    public @NotNull String getUsageDescripition() {
        return "ͨ��qq/idɾ����Ұ�����. (qq�����ȼ�����,�����Ҫָ��ID,��ʹ��NRemove)";
    }

}
