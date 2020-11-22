package cn.liberg.demo.data.dao;

import cn.liberg.core.Column;
import cn.liberg.core.LongColumn;
import cn.liberg.core.OperatorException;
import cn.liberg.core.StringColumn;
import cn.liberg.database.BaseDao;
import cn.liberg.database.select.PreparedSelectExecutor;
import cn.liberg.database.select.PreparedSelectWhere;
import cn.liberg.demo.data.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * user表的数据访问对象
 */
public class UserDao extends BaseDao<User> {
    private static volatile UserDao selfInstance;
    public static final Column<String> columnName = new StringColumn("name", "n");
    public static final Column<String> columnPassword = new StringColumn("password", "p");
    public static final Column<Long> columnRoleId = new LongColumn("roleId", "ri");
    public static final Column<Long> columnCreateTime = new LongColumn("createTime", "ct");

    private UserDao() {
        super("user");
        init();
    }

    public User getByName(String name) throws OperatorException {
        final PreparedSelectWhere<User> psw = prepareSelect().whereEq$(columnName);
        try (final PreparedSelectExecutor<User> prepared = psw.prepare()) {
            prepared.setParameter(columnName, name);
            User user = prepared.one();
            if (user != null) {
                user.role = RoleDao.self().getById(user.roleId);
            }
            return user;
        }
    }

    /**
     * 另一种写法
     */
    public User getByName2(String name) throws OperatorException {
        final User user = select()
                .whereEq(columnName, name)
                .one();
        if (user != null) {
            user.role = RoleDao.self().getById(user.roleId);
        }
        return user;
    }

    /**
     * 单个条件查询，可以直接调用geXxx系列方法
     */
    public User getByName3(String name) throws OperatorException {
        final User user = getEq(columnName, name);
        if (user != null) {
            user.role = RoleDao.self().getById(user.roleId);
        }
        return user;
    }

    private void init() {
    }

    @Override
    public void setEntityId(User entity, long id) {
        entity.id = id;
    }

    @Override
    public long getEntityId(User entity) {
        return entity.id;
    }

    @Override
    public User buildEntity(ResultSet rs) throws SQLException {
        User entity = new User();
        entity.id = rs.getLong(1);
        entity.name = rs.getString(2);
        entity.password = rs.getString(3);
        entity.roleId = rs.getLong(4);
        entity.createTime = rs.getLong(5);
        return entity;
    }

    @Override
    protected void fillPreparedStatement(User entity, PreparedStatement ps) throws SQLException {
        ps.setString(1, entity.name);
        ps.setString(2, entity.password);
        ps.setLong(3, entity.roleId);
        ps.setLong(4, entity.createTime);
    }

    @Override
    public List<Column> getColumns() {
        if (columns == null) {
            columns = new ArrayList<>(8);
            columns.add(columnName);
            columns.add(columnPassword);
            columns.add(columnRoleId);
            columns.add(columnCreateTime);
        }
        return columns;
    }

    public static UserDao self() {
        if (selfInstance == null) {
            synchronized (UserDao.class) {
                if (selfInstance == null) {
                    selfInstance = new UserDao();
                }
            }
        }
        return selfInstance;
    }

}