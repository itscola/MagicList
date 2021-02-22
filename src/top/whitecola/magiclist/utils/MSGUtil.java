package top.whitecola.magiclist.utils;

import org.bukkit.command.CommandSender;

public class MSGUtil {
    public static void sendPluginMessage(String text){
        System.out.println("[MagicList]"+text);
    }

    public static void sendPluginMessage(String text, CommandSender sender){
        sender.sendMessage("[MagicList]"+text);
    }
    public static boolean hasNullString(String[] args, CommandSender sender,Boolean allowLast){
        int index = allowLast ==true?args.length:args.length-1;
        for(int i=0;i<index;i++){
            if(args[i].equals("")){
                if(sender!=null) {
                    sender.sendMessage("第" + (i + 1) + "个参数,不能为空! index :" + i + " 内容为 :" + args[1]);
                }
                return true;
            }
        }
        return false;
    }
}
