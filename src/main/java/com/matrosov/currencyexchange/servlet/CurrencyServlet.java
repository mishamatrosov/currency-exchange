package com.matrosov.currencyexchange.servlet;

import com.matrosov.currencyexchange.dao.CurrencyDao;
import com.matrosov.currencyexchange.model.Currency;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CurrencyServlet", value = "/currency/*")
public class CurrencyServlet extends HttpServlet {
    private final CurrencyDao currencyDao = CurrencyDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getPathInfo().replaceAll("/", "");

        Currency currency = currencyDao.findByCode(code);

        PrintWriter writer = resp.getWriter();

        writer.println(currency.toString());
    }
}
