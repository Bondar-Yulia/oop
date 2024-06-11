package com.example.servlets

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object SqlService {

    private const val JDBC_URL = "jdbc:your_database_url"
    private const val JDBC_USER = "your_database_user"
    private const val JDBC_PASSWORD = "your_database_password"

    fun getConnection(): Connection {
        return try {
            DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)
        } catch (e: SQLException) {
            throw RuntimeException("Unable to connect to database", e)
        }
    }
}
