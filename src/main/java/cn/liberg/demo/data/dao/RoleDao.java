/*
 * First Created by LibergCoder@1.2.0
 */
package cn.liberg.demo.data.dao;

import cn.liberg.demo.data.entity.Role;
import cn.liberg.database.BaseDao;
import cn.liberg.database.query.*;
import cn.liberg.demo.data.entity.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends BaseDao<Role> {
    private static volatile RoleDao selfInstance;
    public static final StringColumn columnName = new StringColumn("_name");
    public static final StringColumn columnPermissions = new StringColumn("_permissions");

    private RoleDao() {
        super("role");
        init();
    }

    private void init() {
    }

    @Override
    public void setEntityId(Role entity, long id) {
        entity.id = id;
    }

    @Override
    public long getEntityId(Role entity) {
        return entity.id;
    }

    @Override
    public Role buildEntity(ResultSet rs) throws SQLException {
        Role entity = new Role();
        entity.id = rs.getLong(1);
        entity.name = rs.getString(2);
        entity.permissions = rs.getString(3);
        return entity;
    }

    @Override
    protected void fillPreparedStatement(Role entity, PreparedStatement ps) throws SQLException {
        ps.setString(1, entity.name);
        ps.setString(2, entity.permissions);
    }

    @Override
    public List<Column> getColumns() {
        if(columns == null) {
            columns = new ArrayList<>(4);
            columns.add(columnName);
            columns.add(columnPermissions);
        }
        return columns;
    }

    public static RoleDao self() {
		if (selfInstance == null) {
		    synchronized (RoleDao.class) {
                if (selfInstance == null) {
                    selfInstance = new RoleDao();
                }
            }
		}
		return selfInstance;
    }

}