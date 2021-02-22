package top.whitecola.magiclist.utils;

public class NumberUtils {
    public static boolean isNumber(String str){
        for(int i=0;i<str.length();i++){
            if(!Character.isDigit(str.charAt(i)))
                return false;
        }
        return true;
    }
}
