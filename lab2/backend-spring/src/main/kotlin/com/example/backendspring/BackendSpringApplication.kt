package com.example.backendspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

@SpringBootApplication
class BackendSpringApplication

fun main(args: Array<String>) {
    runApplication<BackendSpringApplication>(*args)
}

@Configuration
@ComponentScan(basePackages = ["com.example.backendspring"])
class AppConfig {

    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("org.postgresql.Driver")
        dataSource.url = "jdbc:postgresql://localhost:5432/your_database"
        dataSource.username = "your_database_user"
        dataSource.password = "your_database_password"
        return dataSource
    }
}

