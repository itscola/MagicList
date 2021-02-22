package top.whitecola.magiclist.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import top.whitecola.annotations.ItsACommand;
import top.whitecola.commandhandler.ICommand;
import top.whitecola.magiclist.MagicList;
import top.whitecola.magiclist.utils.MSGUtil;
import top.whitecola.magiclist.utils.NumberUtils;
import top.whitecola.magiclist.utils.QQUtils;
import top.whitecola.magiclist.utils.WhiteListUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ItsACommand(CommandNmae = "confirm",premission = "ml.confirm")
public class Confirm implements ICommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length!=2 || !NumberUtils.isNumber(args[1]) || MSGUtil.hasNullString(args,sender,false))
            return false;
        if(MSGUtil.hasNullString(args,sender,false)){
            return true;
        }
        long qq;
        try {
            qq = QQUtils.checkQQNumber(args[2]);
            if(qq==0)
                return false;
        }catch (IllegalArgumentException e){
            return false;
        }
        final long finalQq = qq;
        MagicList.instance.confirmList.forEach((player, confirmer) -> {
            try {
                if(confirmer.getWlp().qq==finalQq){
                    switch (confirmer.getType()){
                        case 1:
                        {
                            WhiteListUtil.removeFromWhitelist(confirmer.getWlp());
                            confirmer.getWlp().name = player.getName();
                            WhiteListUtil.addToWhitelist(confirmer.getWlp());
                            break;
                        }
                        case 2:
                        {
                            WhiteListUtil.removeFromWhitelist(confirmer.getWlp());
                            confirmer.getWlp().uuid = player.getUniqueId();
                            WhiteListUtil.addToWhitelist(confirmer.getWlp());
                            break;
                        }
                    }
                    MSGUtil.sendPluginMessage("操作成功!",sender);
                    return;
                }
            } catch (IOException e) {
                MSGUtil.sendPluginMessage("操作失败, 储存文件或许已顺坏.",sender);
            }
        });
        return true;
    }

    @Override
    public @NotNull List<String> getArgs() {
        return Arrays.asList("[qq]");
    }

    @Override
    public @NotNull List<String> handleArg(CommandSender sender, String handleArg) {
        switch (handleArg) {
            case "[qq]":
                return MagicList.instance.whitelist.getConfig().players.stream().map(i->i.qq+"").collect(Collectors.toList());
            default:
                return Arrays.asList(handleArg);
        }
    }

    @Override
    public @NotNull String getUsage() {
        return "/ml confirm <qq>";
    }

    @Override
    public @NotNull String getUsageDescripition() {
        return "更新对应此QQ号的玩家白名单资料";
    }
}
