package com.fipp.controllers;

import com.fipp.dao.SubcategoriaDAOImpl;
import com.fipp.models.enums.Tipo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.fipp.models.entities.Subcategoria;
import jakarta.servlet.http.HttpSession;

@WebServlet("/subcategoria")
public class SubcategoriaController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
        int categoriaId = req.getParameter("categoriaId") == null ? 0 : Integer.parseInt(req.getParameter("categoriaId"));
        var dao = new SubcategoriaDAOImpl();

        if (id == 0 && categoriaId == 0) {
            req.setAttribute("subcategorias", dao.getAll());
            req.getRequestDispatcher("subcategorias.jsp").forward(req, res);
        } else if (id == 0 && categoriaId > 0) {
            req.setAttribute("subcategorias", dao.getByCategoriaId(id));
            req.getRequestDispatcher("subcategoriaForm.jsp").forward(req, res);
        } else {
            req.setAttribute("subcategoria", dao.getById(id));
            req.getRequestDispatcher("subcategoriaForm.jsp").forward(req, res);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        var subcategoria = new Subcategoria(
                Integer.parseInt(req.getParameter("id")),
                Integer.parseInt(req.getParameter("categoriaId")),
                Integer.parseInt(session.getAttribute("idUsuario").toString()), //TO DO: Check if it works
                Tipo.valueOf(req.getParameter("tipo")),
                req.getParameter("descricao")
        );

        var dao = new SubcategoriaDAOImpl();
        boolean success = subcategoria.getId() == 0 ? dao.inserir(subcategoria) : dao.update(subcategoria);

        if (success)
            req.getRequestDispatcher("subcategorias.jsp").forward(req, res);
        else
            req.getRequestDispatcher("subcategoriaError.jsp").forward(req, res);
    }

}