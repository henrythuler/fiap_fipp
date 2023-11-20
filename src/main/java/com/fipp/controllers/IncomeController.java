package com.fipp.controllers;

import com.fipp.dao.*;
import com.fipp.models.entities.Income;
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

@WebServlet("/income")
public class IncomeController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
        var dao = new IncomeDaoImpl();

        if (id == 0) {
            req.setAttribute("incomes", dao.getAll());
            req.getRequestDispatcher("incomes.jsp").forward(req, res);
        }
        else {
            req.setAttribute("income", dao.getById(id));
            req.getRequestDispatcher("income-form.jsp").forward(req, res);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));

        var income = new Income(
                1,
                Date.valueOf(req.getParameter("date")),
                new BigDecimal(req.getParameter("value")),
                Method.valueByDescription(req.getParameter("method")),
                req.getParameter("description"),
                new CategoryDaoImpl().getById(Integer.parseInt(req.getParameter("category-id"))),
                new SubcategoryDaoImpl().getById(Integer.parseInt(req.getParameter("subcategory-id"))),
                Status.valueByDescription(req.getParameter("status")),
                req.getParameter("payer")
        );

        var dao = new IncomeDaoImpl();
        boolean success = id == 0 ? dao.insert(income) : dao.update(income);

        if (success)
            req.getRequestDispatcher("income-form.jsp").forward(req, res);
        else
            req.getRequestDispatcher("income-error.jsp").forward(req, res);
    }

}