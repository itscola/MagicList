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
                    sender.sendMessage("��" + (i + 1) + "������,����Ϊ��! index :" + i + " ����Ϊ :" + args[1]);
                }
                return true;
            }
        }
        return false;
    }
}
