package com.show.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com.show"})
@PropertySource("classpath:jdbc.properties")
@Import({JDBCConfig.class,MybaitsConfig.class})
@EnableTransactionManagement
public class SpringConfig {
}
