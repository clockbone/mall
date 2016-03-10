package com.clockbone.jpadomain;

import javax.persistence.*;

/**
 * Created by qinjun on 2016/3/10.
 */
@Entity
@Table(name = "t_test")
@AttributeOverrides({
        @AttributeOverride(name="key", column=@Column(name="mKey",unique=true))
})
public class Test {

    private Test(){

    }

    public Test(String key,String name){
        this.key=key;
        this.name=name;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(length = 11,name="mKey")
    private String key;

    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
