package com.sz21c.logger.config.datasource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;

@Configuration
public class DatasourceForMysql {

    @Value("${logger.mariadb.driverClassName}")
    String driverClassName;

    @Value("${logger.mariadb.url}")
    String url;

    @Value("${logger.mariadb.user}")
    String user;

    @Value("${logger.mariadb.pw}")
    String pw;

    @Bean(name = "datasource")
    public DataSource getOracleDatasource() {
        BasicDataSource datasource = new BasicDataSource();

        datasource.setDriverClassName(driverClassName);
        datasource.setUrl(url);
        datasource.setUsername(user);
        datasource.setPassword(pw);
        datasource.setValidationQuery("select 1 from dual");
        datasource.setDefaultAutoCommit(false);

        return new LazyConnectionDataSourceProxy(datasource);
    }
}
