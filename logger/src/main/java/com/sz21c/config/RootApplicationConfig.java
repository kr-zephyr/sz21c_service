package com.sz21c.config;

import com.sz21c.config.datasource.DatasourceForMysql;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(
    basePackages = "com.sz21c", useDefaultFilters = false,
    includeFilters = {
        @ComponentScan.Filter(RestController.class),
        @ComponentScan.Filter(Service.class),
        @ComponentScan.Filter(Repository.class),
        @ComponentScan.Filter(Bean.class)
    }
)
@Import(value = {DatasourceForMysql.class, PropertyEncryptConfig.class})
public class RootApplicationConfig {

    /**
     * CORS 처리.
     * log를 호출하는 사이트와 logger의 도메인이 다르기 때문에 Access-Control-Allow-Origin 오류 발생한다.
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(datasource);
        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:com/sz21c/**/dao/*.xml");
        sqlSessionFactoryBean.setMapperLocations(res);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate", destroyMethod = "clearCache")
    public SqlSessionTemplate sqlSessionTemplate(DataSource datasource) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory(datasource));
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource datasource) throws Exception {
        return new DataSourceTransactionManager(datasource);
    }

    @Value("${looger.server.ssl.key-store}")
    String keystoreFile;

    @Value("${logger.server.ssl.key-store-password}")
    String keystorePass;

    @Value("${logger.server.ssl.keyStoreType}")
    String keystoreType;

    @Value("${logger.server.ssl.keyAlias}")
    String keystoreAlias;

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {

        final String keystoreProvider = "SunJSSE";

        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) (Connector con) -> {
            con.setScheme("https");
            con.setSecure(true);
            Http11NioProtocol proto = (Http11NioProtocol) con.getProtocolHandler();
            proto.setSSLEnabled(true);
            proto.setKeystoreFile(keystoreFile);
            proto.setKeystorePass(keystorePass);
            proto.setKeystoreType(keystoreType);
            proto.setProperty("keystoreProvider", keystoreProvider);
            proto.setKeyAlias(keystoreAlias);
        });


        return factory;
    }
}
