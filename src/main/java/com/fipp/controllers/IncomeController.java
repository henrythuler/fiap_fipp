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
            req.getRequestDispatcher("incomeForm.jsp").forward(req, res);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        var income = new Income(
                Integer.parseInt(req.getParameter("id")),
                Integer.parseInt(session.getAttribute("userId").toString()),
                Date.valueOf(req.getParameter("date")),
                new BigDecimal(req.getParameter("value")),
                Method.valueById(Integer.parseInt(req.getParameter("method"))),
                req.getParameter("description"),
                new CategoryDaoImpl().getById(Integer.parseInt(req.getParameter("category"))),
                new SubcategoryDaoImpl().getById(Integer.parseInt(req.getParameter("subcategory"))),
                Status.valueById(Integer.parseInt(req.getParameter("status"))),
                req.getParameter("payer")
        );

        var dao = new IncomeDaoImpl();
        boolean success = income.getId() == 0 ? dao.insert(income) : dao.update(income);

        if (success)
            req.getRequestDispatcher("incomes.jsp").forward(req, res);
        else
            req.getRequestDispatcher("incomeError.jsp").forward(req, res);
    }

}