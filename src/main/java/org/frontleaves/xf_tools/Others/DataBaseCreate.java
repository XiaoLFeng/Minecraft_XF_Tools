package org.frontleaves.xf_tools.Others;

import org.frontleaves.xf_tools.XF_Tools;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DataBaseCreate {

    private final XF_Tools Tools;

    public DataBaseCreate(XF_Tools Tools) {
        this.Tools = Tools;
    }

    public void CreateTable() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Tools.SqlConn = DriverManager.getConnection("jdbc:mysql://" + Tools.getConfig().getString("Mysql_Host") + ":" + Tools.getConfig().getInt("Mysql_Port") + "/?useSSL=" + Tools.getConfig().getString("Mysql_SSL"), Tools.getConfig().getString("Mysql_User"), Tools.getConfig().getString("Mysql_Password"));
            Tools.Stmt = Tools.SqlConn.createStatement();
            Tools.UseSQL = true;
            Tools.getLogger().info("数据库已成功连接！");
        } catch (ClassNotFoundException e) {
            Tools.UseSQL = false;
        } catch (SQLException e) {
            Tools.getLogger().warning("数据库无法连接，已禁止使用数据库！");
            Tools.UseSQL = false;
        }

        if (this.Create()) {
            Tools.getLogger().info("创建数据表");
            Tools.UseSQL = true;
        } else Tools.UseSQL = false;
    }

    public boolean Create() {
        try {
            if (Tools.Stmt.executeQuery("SELECT TABLE_NAME,TABLE_SCHEMA FROM information_schema.TABLES WHERE TABLE_SCHEMA='xf_tools' AND TABLE_NAME LIKE '%xftls%'").next()) {
                Tools.getLogger().info("2");
                ResultSet Result_Table = Tools.Stmt.executeQuery("SELECT TABLE_NAME,TABLE_SCHEMA FROM  information_schema.TABLES WHERE TABLE_SCHEMA='xf_tools' AND TABLE_NAME LIKE '%xftls%'");
                while (Result_Table.next()) {
                    if (!Objects.equals(Result_Table.getString("TABLE_NAME"), TABLE_NAME.xftls_commandslogs.name())) {
                        if (!CreateCommandsLogs()) Tools.getLogger().warning("数据表 xftls_commandslogs 创建失败");
                        return true;
                    }
                }
            } else {
                Tools.getLogger().info("20");
                if (!Tools.Stmt.executeQuery("SELECT * FROM information_schema.SCHEMATA WHERE SCHEMA_NAME='xf_tools'").next()) {
                    if (Tools.Stmt.execute("CREATE SCHEMA xf_tools")) Tools.getLogger().info("数据库 xf_tools 创建完毕");
                    else {
                        Tools.getLogger().info("数据库创建失败，数据库禁用");
                        Tools.UseSQL = false;
                        return false;
                    }
                }
                if (!CreateCommandsLogs()) {
                    Tools.getLogger().warning("数据表 xftls_commandslogs 创建失败，数据库禁用");
                    Tools.UseSQL = false;
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    
    private boolean CreateCommandsLogs() {
        /*Tools.getLogger().info("1");
        // 如果不存在这个数据库，需要创建一个数据库
        try {
            Tools.getLogger().info("2");
            Tools.Stmt.execute("CREATE TABLE xf_tools.xftls_commandslogs (`id` INT NOT NULL AUTO_INCREMENT," +
                    "`player` VARCHAR(16) NOT NULL," +
                    "`command` TEXT NOT NULL, " +
                    "PRIMARY KEY (`id`)) ENGINE = MyISAM;");
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
            return false;
        }*/
        return false;
    }

    public enum TABLE_NAME {
        xftls_commandslogs,
    }
}