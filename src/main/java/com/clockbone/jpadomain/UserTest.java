package com.clockbone.jpadomain;

import javax.persistence.*;

/**
 * Created by qinjun on 2016/3/10.
 */
@Entity
@Table(name = "t_test")
public class UserTest  {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    String mkey;

    String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
