package br.com.libraryapi.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    //COnfigurando um DataSource
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    //Implementando um DataSource
//    @Bean
//    public DataSource dataSource(){
//        //essa forma nao e boa para utilizar em prod onde temos muitas conexoes
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setUrl(url);
//        ds.setUsername(username);
//        ds.setPassword(password);
//        ds.setDriverClassName(driver);
//        return ds;
//    }

    //pool de conexoes = muitos usuarios de conexoes
    //por padrao o SPringBoot utiliza o Hikari
    @Bean
    public DataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);

        //setando que o pool de conexoes so ira liberar 10 conexoes (muito usado em aplicacoes muito grande)
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(1); //minimo liberado ja de inicio (1 a 10)
        config.setPoolName("library-db-pool"); //nome do pool (aparece no log)
        config.setMaxLifetime(600000); //600 mil ms = 10 min
        config.setConnectionTimeout(100000); //se passar desse tempo a app dara timeout

        return new HikariDataSource(config);
    }

}
