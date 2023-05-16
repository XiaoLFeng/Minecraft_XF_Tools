package org.frontleaves.xf_tools;

import org.bukkit.plugin.java.JavaPlugin;
import org.frontleaves.xf_tools.Commands.MenuCommandExecutor;
import org.frontleaves.xf_tools.Commands.OpCheckCommandExecutor;
import org.frontleaves.xf_tools.Commands.XfDropCommandExecutor;
import org.frontleaves.xf_tools.Commands.XfOpCommandExecutor;
import org.frontleaves.xf_tools.Events.BlockEvent;
import org.frontleaves.xf_tools.Events.DropEvent;
import org.frontleaves.xf_tools.Events.PlayerActivityListener;
import org.frontleaves.xf_tools.Others.DataBaseCreate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class XF_Tools extends JavaPlugin {
    public String XF_Version = "1.0.0-Alpha";
    public String XF_Author = "筱锋xiao_lfeng";
    public String XF_Prefix = "§2XF§bTools";
    public String XF_PrefixArrow = "§7§l>>";
    // 初始化部分
    public List<Object[]> XF_Player = new ArrayList<>();
    public boolean UseSQL = false;
    public Connection SqlConn;
    public Statement Stmt;

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

        // 输出配置文件
        saveDefaultConfig();

        // 数据库配置部分
        if (getConfig().getBoolean("Mysql_Open")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                this.SqlConn = DriverManager.getConnection("jdbc:mysql://"+getConfig().getString("Mysql_Host")+":"+getConfig().getInt("Mysql_Port")+"/?useSSL="+getConfig().getString("Mysql_SSL"),getConfig().getString("Mysql_User"),getConfig().getString("Mysql_Password"));
                this.Stmt = this.SqlConn.createStatement();
                this.UseSQL = true;
                getLogger().info("数据库已成功连接！");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                getLogger().warning("数据库无法连接，已禁止使用数据库！");
            }
            DataBaseCreate DataCheck = new DataBaseCreate(this);
            if (DataCheck.Create()) {
                getLogger().info("创建数据表");
            }
        }

        // 信息输出
        getLogger().info(String.format("作者：%s", this.XF_Author));
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
