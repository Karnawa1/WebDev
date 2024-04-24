package com.sdc.webdev.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

public class DatabaseConfig {
    private static final String PROP_FILE = "/database.properties";
    private static Properties properties = new Properties();

    static {
        try (InputStream input = DatabaseConfig.class.getResourceAsStream(PROP_FILE)) {
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error loading database properties", ex);
        }
    }

    public static DataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(properties.getProperty("database.url"));
        dataSource.setUser(properties.getProperty("database.user"));
        dataSource.setPassword(properties.getProperty("database.password"));

        return dataSource;
    }
}
