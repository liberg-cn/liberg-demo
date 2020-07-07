package cn.liberg.demo.data.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class Company {
    @JSONField(name="id") 
    public long id;
    @JSONField(name="n") 
    public String name;
    @JSONField(name="ct") 
    public long createTime;

}