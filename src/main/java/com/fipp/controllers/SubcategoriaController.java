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

@WebServlet("/subcategoria")
public class SubcategoriaController extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));

        if (id == 0) {
            req.setAttribute("subcategorias", getAll());
            req.getRequestDispatcher("subcategorias.jsp").forward(req, res);
        }
        else {
            req.setAttribute("subcategoria", getById(id));
            req.getRequestDispatcher("subcategoriaForm.jsp").forward(req, res);
        }
    }


    public Subcategoria getById(int id){
        return new SubcategoriaDAOImpl().getById(id);
    }


    public ArrayList<Subcategoria> getAll(){
        return new SubcategoriaDAOImpl().getAll(); //TO DO: Sorted by [descricao]
    }


    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        var subcategoria = new Subcategoria(
                Integer.parseInt(req.getParameter("id")),
                Integer.parseInt(req.getParameter("id")),
                Integer.parseInt(req.getParameter("id")),
                Tipo.valueById(Integer.parseInt(req.getParameter("tipo"))),
                req.getParameter("descricao")
        );

        boolean success;
        if (subcategoria.getId() == 0)
            success = insert(subcategoria) > 0;
        else
            success = update(subcategoria);

        req.getRequestDispatcher("subcategorias.jsp").forward(req, res);
    }


    public int insert(Subcategoria subcategoria){
        var existsInDataBase = new SubcategoriaDAOImpl().getById(subcategoria.getId()) != null;

        if (!existsInDataBase)
            return new SubcategoriaDAOImpl().inserir(subcategoria);
        else
            return 0;
    }


    public boolean update(Subcategoria subcategoria){
        var existsInDataBase = new SubcategoriaDAOImpl().getById(subcategoria.getId()) != null;

        if (existsInDataBase)
            return new SubcategoriaDAOImpl().update(subcategoria);

        return false;
    }

}