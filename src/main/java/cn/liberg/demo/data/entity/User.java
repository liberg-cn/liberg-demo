package cn.liberg.demo.data.entity;

import cn.liberg.annotation.dbmap;
import com.alibaba.fastjson.annotation.JSONField;

public class User {
    @JSONField(name="id") 
    public long id;
    @JSONField(name="n") @dbmap(isIndex=true) 
    public String name;
    @JSONField(name="p") 
    public String password;
    @JSONField(name="ri") 
    public long roleId;
    @JSONField(name="ct") 
    public long createTime;

    @JSONField(name="r") @dbmap(isMap=false) 
    public Role role;

}