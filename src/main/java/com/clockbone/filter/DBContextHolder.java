package com.clockbone.filter;

/**
 * Created by qinjun on 2016/3/23.
 * 设置和获取数据库源
 */
public class DBContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * 设置数据库源方法，在程序运行时调用
     * @param dbType
     */
    public static void setDBType(String dbType) {
        contextHolder.set(dbType);
    }

    /**
     * 动态获取数据库源
     * @return
     */
    public static String getDBType() {
        return contextHolder.get();
    }

    public static void clearDBType() {
        contextHolder.remove();
    }
}
