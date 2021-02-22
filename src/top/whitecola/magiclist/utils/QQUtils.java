package top.whitecola.magiclist.utils;

public class QQUtils {
    public static long checkQQNumber(String qq) throws IllegalArgumentException{
        long id = 0;
        try {
            id = Long.parseLong(qq);
        }catch (NumberFormatException e){
            throw e;
        }
        if(isInBlackList(id))
            throw new IllegalArgumentException("ºÚÃûµ¥qq");

        return id;
    }

    public static boolean isInBlackList(long qq){
        return false;
    }


}
