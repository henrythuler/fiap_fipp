package com.fipp.controllers;

import com.fipp.dao.*;
import com.fipp.models.entities.Despesa;
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

@WebServlet("/despesa")
public class DespesaController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
        var dao = new DespesaDAOImpl();

        if (id == 0) {
            req.setAttribute("despesas", dao.getAll());
            req.getRequestDispatcher("despesas.jsp").forward(req, res);
        }
        else {
            req.setAttribute("despesa", dao.getById(id));
            req.getRequestDispatcher("despesaForm.jsp").forward(req, res);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        var despesa = new Despesa(
                Integer.parseInt(req.getParameter("id")),
                Integer.parseInt(session.getAttribute("idUsuario").toString()), //TO DO: Check if it works
                Date.valueOf(req.getParameter("data")),
                new BigDecimal(req.getParameter("valor")),
                Metodo.valueById(Integer.parseInt(req.getParameter("metodo"))),
                req.getParameter("descricao"),
                new CategoriaDAOImpl().getById(Integer.parseInt(req.getParameter("categoria"))),
                new SubcategoriaDAOImpl().getById(Integer.parseInt(req.getParameter("subcategoria"))),
                Status.valueById(Integer.parseInt(req.getParameter("status"))),
                req.getParameter("beneficiario")
        );

        var dao = new DespesaDAOImpl();
        boolean success = despesa.getId() == 0 ? dao.inserir(despesa) : dao.update(despesa);

        if (success)
            req.getRequestDispatcher("despesas.jsp").forward(req, res);
        else
            req.getRequestDispatcher("despesaError.jsp").forward(req, res);
    }

}