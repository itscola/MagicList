package top.whitecola.magiclist.struct;

import top.whitecola.magiclist.data.Whitelist;


public class Confirmer {
    Whitelist.WLPlayer wlp;
    int type;
//    String name;  type 1 changeName
//    UUID uuid;    type 2 changeUUID

    public Confirmer(Whitelist.WLPlayer wlp,int type){
        this.wlp = wlp;
        this.type = type;
    }

    public Whitelist.WLPlayer getWlp() {
        return wlp;
    }

//    public void setWlp(Whitelist.WLPlayer wlp) {
//        this.wlp = wlp;
//    }

    public int getType() {
        return type;
    }
}
