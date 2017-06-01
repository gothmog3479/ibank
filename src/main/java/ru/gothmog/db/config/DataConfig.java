package ru.gothmog.db.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author d.grushetskiy
 * @Configuration — говорит, что данный класс является Spring конфигурацией;
 * @EnableTransactionManagement — включает TransactionManager для управления транзакциями БД;
 * @ComponentScan(«ru.gothmog.db») — указываем Spring где нужно искать Entity, DAO, Service и т.п.;
 * @PropertySource(«classpath:db.properties») — подключаем файл свойств созданный выше;
 * @EnableJpaRepositories(«ru.gothmog.db.repository») — включаем возможность использования JPARepository
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"ru.gothmog.db.config"})
@PropertySource(value = {"classpath:db.properties"})
@EnableJpaRepositories(basePackages={"ru.gothmog.db.repository"}, entityManagerFactoryRef="entityManagerFactoryBean")
public class DataConfig {

    @Autowired
    private Environment environment;

    @Autowired
    private DataSource dataSource;


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty("db.entitimanager.packages.to.scan"));
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());
        return entityManagerFactoryBean;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("connection.provider_class", environment.getRequiredProperty("connection.provider_class"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));

        properties.put("hibernate.cache.region.factory_class", environment.getRequiredProperty("hibernate.cache.region.factory_class"));
        properties.put("hibernate.javax.cache.provider", environment.getRequiredProperty("hibernate.javax.cache.provider"));
        properties.put("hibernate.cache.use_second_level_cache", environment.getRequiredProperty("hibernate.cache.use_second_level_cache"));
        properties.put("hibernate.cache.use_query_cache", environment.getRequiredProperty("hibernate.cache.use_query_cache"));
        return properties;
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        if (dataSource == null) {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDriverClassName(environment.getRequiredProperty("db.hikari.dataSourceClassName"));
            hikariConfig.setJdbcUrl(environment.getRequiredProperty("db.hikari.dataSource.url"));
            hikariConfig.setUsername(environment.getRequiredProperty("db.hikari.username"));
            hikariConfig.setPassword(environment.getRequiredProperty("db.hikari.password"));
            hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getRequiredProperty("hikari.maximumPoolSize")));
            dataSource = new HikariDataSource(hikariConfig);
        }
        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
