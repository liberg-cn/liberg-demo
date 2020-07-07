package cn.liberg.demo.misc;

import cn.liberg.demo.data.DBConfig;
import cn.liberg.demo.data.DBImpl;
import cn.liberg.database.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeRunner implements ApplicationRunner {
    private DBConfig dbConf;

    public InitializeRunner(@Autowired DBConfig dbConf) {
        this.dbConf = dbConf;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        DBHelper.self().init(new DBImpl(dbConf));
    }
}
