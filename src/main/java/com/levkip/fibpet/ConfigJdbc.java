package com.levkip.fibpet;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ConfigJdbc
{
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://mysql-server:3306/fibpetdb");
        dataSource.setUsername("fibpet");
        dataSource.setPassword("f1bp3t");

        return dataSource;
    }
}
