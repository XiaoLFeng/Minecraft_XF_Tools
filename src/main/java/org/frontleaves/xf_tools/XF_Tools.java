package org.frontleaves.xf_tools;

import org.bukkit.plugin.java.JavaPlugin;
import org.frontleaves.xf_tools.Commands.MenuCommandExecutor;
import org.frontleaves.xf_tools.Commands.OpCheckCommandExecutor;
import org.frontleaves.xf_tools.Commands.XfDropCommandExecutor;
import org.frontleaves.xf_tools.Commands.XfOpCommandExecutor;
import org.frontleaves.xf_tools.Events.BlockEvent;
import org.frontleaves.xf_tools.Events.CommandEvent;
import org.frontleaves.xf_tools.Events.DropEvent;
import org.frontleaves.xf_tools.Events.PlayerActivityListener;
import org.frontleaves.xf_tools.Others.DataBaseCreate;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 筱锋xiao_lfeng
 * @since v1.0.0-Alpha
 */
public final class XF_Tools extends JavaPlugin {
    public String xfVersion = "1.0.0-Alpha";
    public String xfAuthor = "筱锋xiao_lfeng";
    public String xfPrefix = "§2XF§bTools";
    public String xfPrefixArrow = "§7§l>>";
    // 初始化部分
    public List<Object[]> xfPlayer = new ArrayList<>();
    public boolean useSql = false;
    public Connection sqlConn;
    public Statement stmt;
    public List<String> blockBanList = new ArrayList<>();
    @Override
    public void onEnable() {
        // 载入指令
        getCommand("op").setExecutor(new OpCheckCommandExecutor(this));
        getCommand("deop").setExecutor(new OpCheckCommandExecutor(this));
        getCommand("xftool").setExecutor(new MenuCommandExecutor(this));
        getCommand("xftl").setExecutor(new MenuCommandExecutor(this));
        getCommand("xf-tool").setExecutor(new MenuCommandExecutor(this));
        getCommand("xf-tl").setExecutor(new MenuCommandExecutor(this));
        getCommand("xf-op").setExecutor(new XfOpCommandExecutor(this));
        getCommand("xf-drop").setExecutor(new XfDropCommandExecutor(this));

        // 载入监听
        getServer().getPluginManager().registerEvents(new PlayerActivityListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockEvent(this), this);
        getServer().getPluginManager().registerEvents(new DropEvent(this), this);
        getServer().getPluginManager().registerEvents(new CommandEvent(this),this);

        // 输出配置文件
        saveDefaultConfig();
        // 获取配置文件
        this.blockBanList = getConfig().getStringList("BanBlock_List");

        // 数据库配置部分
        if (getConfig().getBoolean("Mysql_Open")) {
            DataBaseCreate dataBaseCreate = new DataBaseCreate(this);
            dataBaseCreate.createTable();
        }

        // 信息输出
        getLogger().info(String.format("作者：%s", this.xfAuthor));
        getLogger().info("瞄，希望我的插件对你有所帮助~");

        super.onEnable();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("插件已卸载");

        super.onDisable();
    }
}
