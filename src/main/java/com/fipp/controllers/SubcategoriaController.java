package com.fipp.controllers;

import com.fipp.dao.SubcategoriaDAOImpl;
import com.fipp.models.enums.Tipo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import com.fipp.models.entities.Subcategoria;
import jakarta.servlet.http.HttpSession;
import java.util.Comparator;

@WebServlet("/subcategoria")
public class SubcategoriaController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
        int categoriaId = req.getParameter("categoriaId") == null ? 0 : Integer.parseInt(req.getParameter("categoriaId"));

        if (id == 0 && categoriaId == 0) {
            req.setAttribute("subcategorias", getAll());
            req.getRequestDispatcher("subcategorias.jsp").forward(req, res);
        } else if (id == 0 && categoriaId > 0) {
            req.setAttribute("subcategorias", getByCategoriaId(categoriaId));
            req.getRequestDispatcher("subcategoriaForm.jsp").forward(req, res);
        } else {
            req.setAttribute("subcategoria", getById(id));
            req.getRequestDispatcher("subcategoriaForm.jsp").forward(req, res);
        }
    }


    public Subcategoria getById(int id){

        return new SubcategoriaDAOImpl().getById(id);
    }

    public ArrayList<Subcategoria> getByCategoriaId(int id){

        return new SubcategoriaDAOImpl().getByCategoriaId(id);
    }


    public ArrayList<Subcategoria> getAll(){

        ArrayList<Subcategoria> subcategorias = new SubcategoriaDAOImpl().getAll();

        subcategorias.sort(Comparator.comparing(Subcategoria::getDescricao, String.CASE_INSENSITIVE_ORDER));

        return subcategorias;
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

        boolean success = subcategoria.getId() == 0 ? insert(subcategoria) : update(subcategoria);

        if (success)
            req.getRequestDispatcher("subcategorias.jsp").forward(req, res);
        else
            req.getRequestDispatcher("subcategoriaError.jsp").forward(req, res);
    }


    public boolean insert(Subcategoria subcategoria){

        var existsInDataBase = new SubcategoriaDAOImpl().getById(subcategoria.getId()) != null;

        if (!existsInDataBase)
            return new SubcategoriaDAOImpl().inserir(subcategoria) > 0;

        return false;
    }


    public boolean update(Subcategoria subcategoria){

        var existsInDataBase = new SubcategoriaDAOImpl().getById(subcategoria.getId()) != null;

        if (existsInDataBase)
            return new SubcategoriaDAOImpl().update(subcategoria);

        return false;
    }

}