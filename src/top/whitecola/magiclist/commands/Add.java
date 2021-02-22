package top.whitecola.magiclist.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import top.whitecola.annotations.ItsACommand;
import top.whitecola.commandhandler.ICommand;
import top.whitecola.magiclist.utils.*;
import top.whitecola.magiclist.data.Whitelist;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ItsACommand(CommandNmae = "add",premission = "ml.add")
public class Add implements ICommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if((args.length<2)  || MSGUtil.hasNullString(args,sender,false))
            return false;

        long qq = 0;
        if(args.length==3){
            if(!NumberUtils.isNumber(args[2]))
                return false;
            try {
                qq = QQUtils.checkQQNumber(args[2]);
                if(qq==0)
                    return false;
            }catch (IllegalArgumentException e){
                if(e.getLocalizedMessage().equals("黑名单qq")){
                    MSGUtil.sendPluginMessage("操作失败,"+qq+"为黑名单QQ,无法加入到白名单.",sender);
                    return true;
                }
                return false;
            }
        }
        Whitelist.WLPlayer wlp;
        try {
            if((wlp=WhiteListUtil.getWLPlayer(args[1]))!=null || (wlp=WhiteListUtil.getWLPlayer(qq))!=null){
                if(wlp.qq!=0){
                    MSGUtil.sendPluginMessage("操作失败, "+wlp.getName()+"["+wlp.getQQ()+"] 已存在于白名单.",sender);
                    return true;
                }
                WhiteListUtil.removeFromWhitelist(wlp);

            }
            WhiteListUtil.addToWhitelist(new Whitelist.WLPlayer(args[1], qq, ServerUtil.isStrongOnlineMode()?Bukkit.getOfflinePlayer(args[1]).getUniqueId():null));
        } catch (IOException e) {
            MSGUtil.sendPluginMessage("操作失败, 储存文件或许已顺坏.",sender);
        }
        MSGUtil.sendPluginMessage("成功将"+args[1]+"["+(qq!=0?args[2]:0)+"]加入白名单.",sender);
        return true;
    }

    @Override
    public @NotNull List<String> getArgs() {
        return Arrays.asList("[OnlinePlayer]","[qq]");
    }

    @Override
    public @NotNull List<String> handleArg(CommandSender sender, String handleArg) {
        switch (handleArg) {
            case "[OnlinePlayer]":
                return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
            default:
                return Arrays.asList(handleArg);
        }
    }

    @Override
    public @NotNull String getUsage() {
        return "/ml add <PlayerName> [qq]";
    }

    @Override
    public @NotNull String getUsageDescripition() {
        return "给玩家添加白名单,qq为可选. 使用#代替空格";
    }
}
