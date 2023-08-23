package com.estaciones.demo.core.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfiguration {

  @Bean
  @ConfigurationProperties("flyway.datasource")
  public DataSourceProperties flywayDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @FlywayDataSource
  @ConfigurationProperties("flyway.datasource.hikari")
  public DataSource flywayDataSource() {
    final HikariDataSource dataSource = flywayDataSourceProperties()
        .initializeDataSourceBuilder()
        .type(HikariDataSource.class)
        .build();
    dataSource.setPoolName("flywayDataSource");
    dataSource.setMaximumPoolSize(2);
    dataSource.setMinimumIdle(1);
    return dataSource;
  }

  @Bean
  @Primary
  @ConfigurationProperties("application.datasource")
  public DataSourceProperties applicationDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @ConfigurationProperties("application.datasource.hikari")
  public DataSource applicationDataSource() {
    final HikariDataSource dataSource = applicationDataSourceProperties()
        .initializeDataSourceBuilder()
        .type(HikariDataSource.class)
        .build();
    dataSource.setPoolName("applicationDataSource");
    return new TenantAwareDataSource(dataSource);
  }

  ///////////////////////////////////TENANT ADMIN///////////////////////////////////////////
  @Bean
  @ConfigurationProperties(prefix = "tenant.admin.datasource")
  public DataSource tenantAdminDatasource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public DynamicDataSourceRouter dynamicDataSourceRouter() {
    DynamicDataSourceRouter dataSourceRouter = new DynamicDataSourceRouter();
    Map<Object, Object> targetDataSources = new HashMap<>();
    targetDataSources.put("applicationDataSource", applicationDataSource());
    targetDataSources.put("tenantAdminDatasource", tenantAdminDatasource());
    dataSourceRouter.setTargetDataSources(targetDataSources);
    dataSourceRouter.setDefaultTargetDataSource(applicationDataSource());
    return dataSourceRouter;
  }
}
