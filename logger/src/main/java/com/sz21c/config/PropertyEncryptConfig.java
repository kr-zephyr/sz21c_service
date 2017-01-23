package com.sz21c.config;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Slf4j
@Configuration
public class PropertyEncryptConfig {

    @Bean
    public EnvironmentStringPBEConfig environmentVariablesConfiguration() {
        EnvironmentStringPBEConfig environmentVariablesConfiguration = new EnvironmentStringPBEConfig();
        environmentVariablesConfiguration.setAlgorithm("PBEWithMD5AndDES");
        //TODO PropertiesPlaceHolder가 jasypt의 영향을 받는 듯.
        // 전통적인 System.getProperty()를 사용하여 우선 해결하고, 향후 수정할 예정임
        environmentVariablesConfiguration.setPassword(System.getProperty("jasyptpwd"));
        return environmentVariablesConfiguration;
    }

    @Bean
    public StringEncryptor configurationEncryptor() {
        StandardPBEStringEncryptor configurationEncryptor = new StandardPBEStringEncryptor();
        configurationEncryptor.setConfig(environmentVariablesConfiguration());
        return configurationEncryptor;
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyConfigurer() {
        EncryptablePropertyPlaceholderConfigurer propertyConfigurer = new EncryptablePropertyPlaceholderConfigurer(configurationEncryptor());
        propertyConfigurer.setLocation(new ClassPathResource("application.properties"));
        return propertyConfigurer;
    }
}
