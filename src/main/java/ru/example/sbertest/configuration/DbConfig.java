package ru.example.sbertest.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class DbConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("dbDriver"));
        dataSource.setUrl(env.getProperty("dbUrl"));
        dataSource.setUsername(env.getProperty("dbUser"));
        dataSource.setPassword(env.getProperty("dbPassword"));
        return dataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean(name="transactionManager")
    public PlatformTransactionManager txManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(getDataSource());
        return transactionManager;
    }
}
