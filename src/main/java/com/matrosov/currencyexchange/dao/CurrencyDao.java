package com.matrosov.currencyexchange.dao;

import com.matrosov.currencyexchange.model.Currency;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {
    public void create(Currency currency) {

    }

    public List<Currency> findAll() {
        String query = "select * from currencies";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<Currency> currencies = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/currency_exchange_schema",
                    "root",
                    "admin"
            );
            statement = connection.createStatement();
            ResultSet resultSet1 = statement.executeQuery(query);

            while (resultSet1.next()) {
                currencies.add(
                        new Currency(
                                resultSet1.getInt(1),
                                resultSet1.getString(2),
                                resultSet1.getString(3),
                                resultSet1.getString(4)
                        )
                );
            }
        } catch (SQLException | InvocationTargetException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return currencies;
    }

    public Currency findByCode(String code) {
        return null;
    }
}
