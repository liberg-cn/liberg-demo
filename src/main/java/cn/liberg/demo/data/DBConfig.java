package cn.liberg.demo.data;

import cn.liberg.database.IDataBaseConf;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DBConfig implements IDataBaseConf {
    private String driverName = "com.mysql.cj.jdbc.Driver";
    @Value(value = "${spring.datasource.url}")
    private String url = "";
    @Value(value = "${spring.datasource.name}")
    private String dbName = "";
    @Value(value = "${spring.datasource.username}")
    private String userName = "root";
    @Value(value = "${spring.datasource.password}")
    private String password = "";
    private String charset = "utf8";
    private String collation = "utf8_general_ci";


    @Override
    public String getDriverName() {
        return driverName;
    }

    @Override
    public String getDbName() {
        return dbName;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getCharset() {
        return charset;
    }

    @Override
    public String getCollation() {
        return collation;
    }
}

