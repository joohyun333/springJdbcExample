package com.example.springjdbc.config;


import com.example.springjdbc.service.ClientService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.example.springjdbc.dao",
        sqlSessionFactoryRef = "routeSqlSessionFactory")
public class RoutingTestConfiguration {

    @Bean(name = "clientDataBaseA")
    @ConfigurationProperties(prefix = "client-a")
    public DataSource clientDataBaseA(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "clientDataBaseB")
    @ConfigurationProperties(prefix = "client-b")
    public DataSource clientDataBaseB(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "clientDatasource")
    public DataSource clientDatasource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        DataSource clientADatasource = clientDataBaseA();
        DataSource clientBDatasource = clientDataBaseB();
        targetDataSources.put(ClientDatabase.CLIENT_A, clientADatasource);
        targetDataSources.put(ClientDatabase.CLIENT_B, clientBDatasource);

        ClientDataSourceRouter clientRoutingDatasource = new ClientDataSourceRouter();
        clientRoutingDatasource.setTargetDataSources(targetDataSources);
        clientRoutingDatasource.setDefaultTargetDataSource(clientADatasource);
        return clientRoutingDatasource;
    }

    @Bean(name = "routeSqlSessionFactory")
    public SqlSessionFactory sqlSession(@Qualifier("clientDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:mapper/*.xml"));
        return sessionFactoryBean.getObject();
    }


    @Bean(name = "routeSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("routeSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
