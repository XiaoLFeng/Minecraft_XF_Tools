package org.frontleaves.xf_tools;

import org.bukkit.plugin.java.JavaPlugin;
import org.frontleaves.xf_tools.Commands.MenuCommandExecutor;
import org.frontleaves.xf_tools.Commands.OpCheckCommandExecutor;
import org.frontleaves.xf_tools.Commands.XfOpCommandExecutor;
import org.frontleaves.xf_tools.Events.PlayerActivityListener;

public final class XF_Tools extends JavaPlugin {
    public String XF_Version = "1.0.0-Alpha";
    public String XF_Author = "筱锋xiao_lfeng";
    public String XF_Prefix = "§2XF§bTools";
    public String XF_PrefixArrow = "§7§l>>";

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

        // 载入监听
        getServer().getPluginManager().registerEvents(new PlayerActivityListener(), this);

        // 输出配置文件
        saveDefaultConfig();

        // 信息输出
        getLogger().info(String.format("作者：%s",this.XF_Author));
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
