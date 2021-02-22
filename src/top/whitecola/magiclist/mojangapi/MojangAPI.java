package top.whitecola.magiclist.mojangapi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import top.whitecola.datahandler.HiURL;
import top.whitecola.magiclist.mojangapi.mcpl.struct.ChangeName;

import java.util.ArrayList;

public class MojangAPI {
    public static String getUUIDByName(String name){
        Gson gson = new Gson();
        top.whitecola.magiclist.mojangapi.mcpl.struct.UUID uuid;
        String uuidRequest;
        try {
            if(!(uuidRequest = HiURL.readURL("https://api.mojang.com/users/profiles/minecraft/"+name+"?at="+System.currentTimeMillis())).equals("") && !uuidRequest.contains("IllegalArgumentException")){
                uuid = gson.fromJson(uuidRequest, top.whitecola.magiclist.mojangapi.mcpl.struct.UUID.class);
                return uuid.getId();
            }
        } catch (Throwable throwable) {}
        return "";
    }

    public static String getNameByUUID(String uuid){
        Gson gson = new Gson();
        try {
            String historyRequest = HiURL.readURL("https://api.mojang.com/user/profiles/"+uuid+"/names");
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(historyRequest).getAsJsonArray();
            ArrayList<ChangeName> changeNameList = new ArrayList<>();
            for (JsonElement user : jsonArray) {
                ChangeName changeName = gson.fromJson(user, ChangeName.class);
                changeNameList.add(changeName);
            }
            return changeNameList.get(changeNameList.size()-1).getName();
        } catch (Throwable throwable) {
        }
        return "";
    }
}
