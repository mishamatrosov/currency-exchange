package com.matrosov.currencyexchange.dao;

import com.matrosov.currencyexchange.model.Currency;
import com.matrosov.currencyexchange.utils.SQLConnectionFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {

    private static CurrencyDao INSTANCE = null;

    private CurrencyDao() {

    }

    public static CurrencyDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CurrencyDao();
        }
        return INSTANCE;
    }

    public void create(Currency currency) {

    }

    public List<Currency> findAll() {
        String query = "select * from currencies";

        Connection connection = SQLConnectionFactory.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        List<Currency> currencies = new ArrayList<>();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currencies;
    }

    public Currency findByCode(String code) {
        String query = "select * from currencies where currency_code = ?";

        Connection connection = SQLConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        Currency currency = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                currency = new Currency(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currency;
    }
}
