package com.matrosov.currencyexchange.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLConnectionFactory {
    private static String DRIVER;
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        Properties props = new Properties();
        InputStream is = SQLConnectionFactory.class.getClassLoader().getResourceAsStream("datasource.properties");

        try {
            props.load(is);
            DRIVER=props.getProperty("mysql.driver");
            URL=props.getProperty("mysql.url");
            USER=props.getProperty("mysql.user");
            PASSWORD=props.getProperty("mysql.password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
