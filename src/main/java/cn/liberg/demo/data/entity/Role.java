package cn.liberg.demo.data.entity;

import cn.liberg.annotation.dbmap;
import com.alibaba.fastjson.annotation.JSONField;

public class Role {
    @JSONField(name="id") 
    public long id;
    @JSONField(name="n") @dbmap(length=31) 
    public String name;
    @JSONField(name="p") @dbmap(length=5000) 
    public String permissions;

}