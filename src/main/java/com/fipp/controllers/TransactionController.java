package com.fipp.controllers;

import com.fipp.dao.TransacaoDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/transaction")
public class TransactionController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        var dao = new TransacaoDaoImpl();

        req.setAttribute("transactions", dao.getAll());

        req.getRequestDispatcher("home.jsp").forward(req, res);

    }

}
