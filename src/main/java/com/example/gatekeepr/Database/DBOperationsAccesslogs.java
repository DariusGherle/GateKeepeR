package com.example.gatekeepr.Database;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class DBOperationsAccesslogs {
    private DataSource dataSource;
    private  JdbcTemplate jdbcTemplateObject;
    public void setDataSource(DataSource dataSource){
        this.dataSource=dataSource;
        this.jdbcTemplateObject=new JdbcTemplate(dataSource);
    }
}
