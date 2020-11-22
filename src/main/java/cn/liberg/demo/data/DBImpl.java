package cn.liberg.demo.data;

import cn.liberg.database.IDataBase;
import cn.liberg.database.IDataBaseConf;
import cn.liberg.database.TableBuilder;
import cn.liberg.demo.data.dao.CompanyDao;
import cn.liberg.demo.data.dao.RoleDao;
import cn.liberg.demo.data.dao.UserDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.SQLException;
import java.sql.Statement;

public class DBImpl implements IDataBase {
    private Log logger = LogFactory.getLog(getClass());
    protected final IDataBaseConf dbConf;
    protected final int dbVersion = 1;
    protected final String dbName;

    public DBImpl(IDataBaseConf dbConf) {
        this.dbConf = dbConf;
        dbName = dbConf.getDbName();
    }

    @Override
    public int upgrade(Statement stat, int dbVersion, int newVersion) throws SQLException {
        DBUpgrader dbUpgrader = new DBUpgrader(dbConf);
        return dbUpgrader.upgrade(stat, dbVersion, newVersion);
    }

    @Override
    public void initData() {
        try {
            DBInitializer initor = new DBInitializer();
            initor.initData();
        } catch (Exception e) {
            logger.error("initData failed...", e);
        }
    }

    @Override
    public String getName() {
        return dbName;
    }

    @Override
    public int getCurrentVersion() {
        return dbVersion;
    }

    @Override
    public IDataBaseConf getConfig() {
        return dbConf;
    }

    @Override
    public void createTable(Statement stat) throws SQLException {
        createTableUser(stat);
        createTableRole(stat);
        createTableCompany(stat);
    }

    protected void createTableUser(Statement stat) throws SQLException {
        TableBuilder tb = new TableBuilder(UserDao.self().getTableName());
        tb.add(UserDao.columnName, true, typeString());
        tb.add(UserDao.columnPassword, typeString());
        tb.add(UserDao.columnRoleId, typeLong());
        tb.add(UserDao.columnCreateTime, typeLong());
        stat.executeUpdate(tb.build());
    }

    protected void createTableRole(Statement stat) throws SQLException {
        TableBuilder tb = new TableBuilder(RoleDao.self().getTableName());
        tb.add(RoleDao.columnName, typeString(31));
        tb.add(RoleDao.columnPermissions, typeText());
        stat.executeUpdate(tb.build());
    }

    protected void createTableCompany(Statement stat) throws SQLException {
        TableBuilder tb = new TableBuilder(CompanyDao.self().getTableName());
        tb.add(CompanyDao.columnName, typeString());
        tb.add(CompanyDao.columnCreateTime, typeLong());
        stat.executeUpdate(tb.build());
    }

}