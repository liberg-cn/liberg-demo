/*
 * First Created by LibergCoder@1.2.0
 */
package cn.liberg.demo.data.dao;

import cn.liberg.core.Column;
import cn.liberg.core.LongColumn;
import cn.liberg.core.StringColumn;
import cn.liberg.database.BaseDao;
import cn.liberg.demo.data.entity.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao extends BaseDao<Company> {
    private static volatile CompanyDao selfInstance;
    public static final Column<String> columnName = new StringColumn("name", "n");
    public static final Column<Long> columnCreateTime = new LongColumn("createTime", "ct");

    private CompanyDao() {
        super("company");
        init();
    }

    private void init() {
    }

    @Override
    public void setEntityId(Company entity, long id) {
        entity.id = id;
    }

    @Override
    public long getEntityId(Company entity) {
        return entity.id;
    }

    @Override
    public Company buildEntity(ResultSet rs) throws SQLException {
        Company entity = new Company();
        entity.id = rs.getLong(1);
        entity.name = rs.getString(2);
        entity.createTime = rs.getLong(3);
        return entity;
    }

    @Override
    protected void fillPreparedStatement(Company entity, PreparedStatement ps) throws SQLException {
        ps.setString(1, entity.name);
        ps.setLong(2, entity.createTime);
    }

    @Override
    public List<Column> getColumns() {
        if(columns == null) {
            columns = new ArrayList<>(4);
            columns.add(columnName);
            columns.add(columnCreateTime);
        }
        return columns;
    }

    public static CompanyDao self() {
		if (selfInstance == null) {
		    synchronized (CompanyDao.class) {
                if (selfInstance == null) {
                    selfInstance = new CompanyDao();
                }
            }
		}
		return selfInstance;
    }

}