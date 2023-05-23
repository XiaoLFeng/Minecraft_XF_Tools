package org.frontleaves.xf_tools.Others;

import org.frontleaves.xf_tools.XF_Tools;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author 筱锋xiao_lfeng
 * @since v1.0.0-Alpha
 */
public class DataBaseCreate {

    private final XF_Tools tools;

    public DataBaseCreate(XF_Tools plugins) {
        this.tools = plugins;
    }

    public void createTable() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            tools.sqlConn = DriverManager.getConnection("jdbc:mysql://" + tools.getConfig().getString("Mysql_Host") + ":" + tools.getConfig().getInt("Mysql_Port") + "/?useSSL=" + tools.getConfig().getString("Mysql_SSL"), tools.getConfig().getString("Mysql_User"), tools.getConfig().getString("Mysql_Password"));
            tools.stmt = tools.sqlConn.createStatement();
            tools.useSql = true;
            tools.getLogger().info("数据库已成功连接！");
        } catch (ClassNotFoundException e) {
            tools.useSql = false;
        } catch (SQLException e) {
            tools.getLogger().warning("数据库无法连接，已禁止使用数据库！");
            tools.useSql = false;
        }

        if (this.create()) {
            tools.getLogger().info("创建数据表");
            tools.useSql = true;
        } else {
            tools.useSql = false;
        }
    }

    public boolean create() {
        try {
            if (tools.stmt.executeQuery("SELECT TABLE_NAME,TABLE_SCHEMA FROM information_schema.TABLES WHERE TABLE_SCHEMA='xf_tools' AND TABLE_NAME LIKE '%xftls%'").next()) {
                tools.getLogger().info("2");
                ResultSet resultTable = tools.stmt.executeQuery("SELECT TABLE_NAME,TABLE_SCHEMA FROM  information_schema.TABLES WHERE TABLE_SCHEMA='xf_tools' AND TABLE_NAME LIKE '%xftls%'");
                while (resultTable.next()) {
                    if (!Objects.equals(resultTable.getString("TABLE_NAME"), TABLE_NAME.xftls_commandslogs.name())) {
                        if (!createCommandsLogs()) {
                            tools.getLogger().warning("数据表 xftls_commandslogs 创建失败");
                        }
                        return true;
                    }
                }
            } else {
                tools.getLogger().info("20");
                if (!tools.stmt.executeQuery("SELECT * FROM information_schema.SCHEMATA WHERE SCHEMA_NAME='xf_tools'").next()) {
                    if (tools.stmt.execute("CREATE SCHEMA xf_tools")) {
                        tools.getLogger().info("数据库 xf_tools 创建完毕");
                    } else {
                        tools.getLogger().info("数据库创建失败，数据库禁用");
                        tools.useSql = false;
                        return false;
                    }
                }
                if (!createCommandsLogs()) {
                    tools.getLogger().warning("数据表 xftls_commandslogs 创建失败，数据库禁用");
                    tools.useSql = false;
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    
    private boolean createCommandsLogs() {
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
