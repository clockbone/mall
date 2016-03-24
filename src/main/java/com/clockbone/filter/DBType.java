package com.clockbone.filter;

/**
 * Created by qinjun on 2016/3/23.
 * 动态数据库源KEY值枚举类
 */
public enum DBType {
    DB_MAIN(1,"dataSourceMain"),
    DB_BACK(2,"dataSourceBack");

    private Integer type;
    private String dataSourceKey;
    private DBType(Integer type,String dataSourceKey){
        this.type=type;
        this.dataSourceKey=dataSourceKey;
    }

    /**
     * 通过type，获取value，即动态数据源的KEY值
     * @param type
     * @return
     */
    public static String getValue(Integer type) {
        for (DBType c : DBType.values()) {
            if (c.getType().equals(type)) {
                return c.dataSourceKey;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getDataSourceKey() {
        return dataSourceKey;
    }

    public void setDataSourceKey(String dataSourceKey) {
        this.dataSourceKey = dataSourceKey;
    }
}
