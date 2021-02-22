package top.whitecola.magiclist.utils;

import java.util.List;

public class Utils {
    public static boolean ignoreCaseContains(List<String> list, String name){
        for(String i : list){
            if(i.equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
}
