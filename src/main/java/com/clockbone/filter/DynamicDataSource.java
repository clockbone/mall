package com.clockbone.filter;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by qinjun on 2016/3/23.
 * 动态数据源配置类
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        //返回数据库源key值
        return DBContextHolder.getDBType();
    }
}
