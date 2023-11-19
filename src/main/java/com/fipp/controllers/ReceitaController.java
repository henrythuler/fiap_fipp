package com.fipp.controllers;

import com.fipp.dao.*;
import com.fipp.models.entities.Receita;
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

@WebServlet("/receita")
public class ReceitaController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
        var dao = new ReceitaDAOImpl();

        if (id == 0) {
            req.setAttribute("receitas", dao.getAll());
            req.getRequestDispatcher("receitas.jsp").forward(req, res);
        }
        else {
            req.setAttribute("receita", dao.getById(id));
            req.getRequestDispatcher("receitaForm.jsp").forward(req, res);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        var receita = new Receita(
                Integer.parseInt(req.getParameter("id")),
                Integer.parseInt(session.getAttribute("idUsuario").toString()), //TO DO: Check if it works
                Date.valueOf(req.getParameter("data")),
                new BigDecimal(req.getParameter("valor")),
                Metodo.valueById(Integer.parseInt(req.getParameter("metodo"))),
                req.getParameter("descricao"),
                new CategoriaDAOImpl().getById(Integer.parseInt(req.getParameter("categoria"))),
                new SubcategoriaDAOImpl().getById(Integer.parseInt(req.getParameter("subcategoria"))),
                Status.valueById(Integer.parseInt(req.getParameter("status"))),
                req.getParameter("pagador")
        );

        var dao = new ReceitaDAOImpl();
        boolean success = receita.getId() == 0 ? dao.inserir(receita) : dao.update(receita);

        if (success)
            req.getRequestDispatcher("receitas.jsp").forward(req, res);
        else
            req.getRequestDispatcher("receitaError.jsp").forward(req, res);
    }

}