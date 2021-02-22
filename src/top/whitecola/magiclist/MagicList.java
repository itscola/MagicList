package top.whitecola.magiclist;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import top.whitecola.HiPlugin;
import top.whitecola.commandhandler.HiCommand;
import top.whitecola.confighandler.HiConfig;
import top.whitecola.confighandler.HiMySql;
import top.whitecola.confighandler.IData;
import top.whitecola.magiclist.commands.*;
import top.whitecola.magiclist.configs.Config;
import top.whitecola.magiclist.data.Whitelist;
import top.whitecola.magiclist.i18n.Language;
import top.whitecola.magiclist.listener.PlayerListener;
import top.whitecola.magiclist.struct.Confirmer;
import top.whitecola.magiclist.utils.MSGUtil;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MagicList extends JavaPlugin {
    public static MagicList instance = null;
    {
        instance = this;
    }
    public HiCommand commands = new HiCommand(this,"ml");
    public HiConfig<Config> config = new HiConfig<>(this.getDataFolder()+"/Config.json",Config.class, Charset.forName("utf8"),instance);
    public HiConfig<Language> language = new HiConfig<>(this.getDataFolder()+"/Language.json",Language.class, Charset.forName("utf8"),instance);
    public IData<Whitelist> whitelist = null;
    public ConcurrentHashMap<Player,Confirmer> confirmList = new ConcurrentHashMap<>();

    @Override
    public void onLoad() {
        registerData();
        registerCommands();
    }

    @Override
    public void onEnable() {
        registerListener();
        HiPlugin.instance.registerPlugin(this);
        System.out.println("§f  __  __             _      _      _     _   ");
        System.out.println("§f |  \\/  |           (_)    | |    (_)   | |  ");
        System.out.println("§f | \\  / | __ _  __ _ _  ___| |     _ ___| |_ ");
        System.out.println("§f | |\\/| |/ _` |/ _` | |/ __| |    | / __| __|");
        System.out.println("§f | |  | | (_| | (_| | | (__| |____| \\__ \\ |_ ");
        System.out.println("§f |_|  |_|\\__,_|\\__, |_|\\___|______|_|___/\\__|");
        System.out.println("§f               __/ |                         ");
        System.out.println("§f              |___/                          ");

        MSGUtil.sendPluginMessage("MagicList已启动.");
        MSGUtil.sendPluginMessage("由White_cola制作.");

    }

    public void registerData(){
        if(config.getConfig().savingMode.equalsIgnoreCase("mysql")){
            this.whitelist = new HiMySql();
            MSGUtil.sendPluginMessage("已使用MySql储存模式!");
            return;
        }
        this.whitelist = new HiConfig<>(this.getDataFolder()+"/Whitelist.data",Whitelist.class, Charset.forName("utf8"),instance);
        MSGUtil.sendPluginMessage("已使用Json储存模式!");
        return;
    }

    public void registerCommands(){
        commands.addCommand(new Add());
        commands.addCommand(new Remove());
        commands.addCommand(new Get());
        commands.addCommand(new Reload());
        commands.addCommand(new NRemove());
        commands.addCommand(new QRemove());
        commands.addCommand(new Confirm());
    }

    public void registerListener(){
        Bukkit.getPluginManager().registerEvents(new PlayerListener(),this);
    }

    @Override
    public void onDisable() {
        MSGUtil.sendPluginMessage("MagicList已关闭.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("ml")){
            return commands.onCommand(sender,command,label,args);
        }
        return super.onCommand(sender, command, label, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(command.getName().equalsIgnoreCase("ml")){
            return commands.onTabComplete(sender,command,alias,args);
        }
        return super.onTabComplete(sender, command, alias, args);
    }


}
