package com.robocode.model.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public final class DBManager {
    private static DBManager instance;
    private final HikariDataSource dataSource;

    private DBManager() {
        Properties properties = getProps();
        HikariConfig config = new HikariConfig();

        config.setDriverClassName(properties.getProperty("driver"));

        config.setJdbcUrl(properties.getProperty("url"));
        config.setUsername(properties.getProperty("username"));
        config.setPassword(properties.getProperty("password"));

        dataSource = new HikariDataSource(config);
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            return new DBManager();
        }
        return instance;
    }

    private Properties getProps() {
        Properties properties = new Properties();

        try {
            properties.load(DBManager.class.getResourceAsStream("/database.properties"));
        } catch (IOException e) {
            log.error("Cannot load properties");
        }
        return properties;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void closeConnection() {
        dataSource.close();
    }

}
