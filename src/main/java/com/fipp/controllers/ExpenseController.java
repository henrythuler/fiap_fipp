package com.fipp.controllers;

import com.fipp.dao.*;
import com.fipp.models.entities.Expense;
import com.fipp.models.enums.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

@WebServlet("/expense")
public class ExpenseController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
        var dao = new ExpenseDaoImpl();

        if (id == 0) {
            req.setAttribute("expenses", dao.getAll());
            req.getRequestDispatcher("expenses.jsp").forward(req, res);
        }
        else {
            req.setAttribute("expense", dao.getById(id));
            req.getRequestDispatcher("expense-form.jsp").forward(req, res);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));

        var expense = new Expense(
                1,
                Date.valueOf(req.getParameter("date")),
                new BigDecimal(req.getParameter("value")),
                Method.valueByDescription(req.getParameter("method")),
                req.getParameter("description"),
                new CategoryDaoImpl().getById(Integer.parseInt(req.getParameter("category-id"))),
                new SubcategoryDaoImpl().getById(Integer.parseInt(req.getParameter("subcategory-id"))),
                Status.valueByDescription(req.getParameter("status")),
                req.getParameter("beneficiary")
        );

        var dao = new ExpenseDaoImpl();
        boolean success = id == 0 ? dao.insert(expense) : dao.update(expense);

        if (success)
            req.getRequestDispatcher("expense-form.jsp").forward(req, res);
        else
            req.getRequestDispatcher("expenseError.jsp").forward(req, res);
    }

}