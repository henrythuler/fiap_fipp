package com.fipp.controllers;

import com.fipp.dao.CategoriaDAOImpl;
import com.fipp.models.entities.Categoria;
import com.fipp.models.enums.Tipo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/categoria")
public class CategoriaController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
        var dao = new CategoriaDAOImpl();

        if (id == 0) {
            req.setAttribute("categorias", dao.getAll());
            req.getRequestDispatcher("subcategorias.jsp").forward(req, res);
        }
        else {
            req.setAttribute("categoria", dao.getById(id));
            req.getRequestDispatcher("categoriaForm.jsp").forward(req, res);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        var categoria = new Categoria(
                Integer.parseInt(req.getParameter("id")),
                Integer.parseInt(session.getAttribute("idUsuario").toString()), //TO DO: Check if it works
                Tipo.valueOf(req.getParameter("tipo")),
                req.getParameter("descricao")
        );

        var dao = new CategoriaDAOImpl();
        boolean success = categoria.getId() == 0 ? dao.inserir(categoria) : dao.update(categoria);

        if (success)
            req.getRequestDispatcher("subcategorias.jsp").forward(req, res);
        else
            req.getRequestDispatcher("subcategoriaError.jsp").forward(req, res);
    }

}