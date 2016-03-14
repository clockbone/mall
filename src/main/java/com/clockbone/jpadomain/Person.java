package com.clockbone.jpadomain;

import javax.persistence.*;

/**
 * Created by qinjun on 2016/3/11.
 * 注意：
 * 1、这里的一定要注明表名@Table,
 * 2、注明主键 其中：@Id 如果是自增一定要注明是自增 @GeneratedValue
 */
@Entity
@Table(name = "role")
public class Person  {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Integer id;
    private String name;
    private String roleKey;
    private String description;
    private Integer enable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
