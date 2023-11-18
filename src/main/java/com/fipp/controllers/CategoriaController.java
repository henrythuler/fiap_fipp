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
import java.util.ArrayList;

@WebServlet("/categoria")
public class CategoriaController extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));

        if (id == 0) {
            req.setAttribute("categorias", getAll());
            req.getRequestDispatcher("subcategorias.jsp").forward(req, res);
        }
        else {
            req.setAttribute("categoria", getById(id));
            req.getRequestDispatcher("categoriaForm.jsp").forward(req, res);
        }
    }


    public Categoria getById(int id){
        return new CategoriaDAOImpl().getById(id);
    }


    public ArrayList<Categoria> getAll(){
        return new CategoriaDAOImpl().getAll(); //TO DO: Sorted by [descricao]
    }


    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        var categoria = new Categoria(
                Integer.parseInt(req.getParameter("id")),
                Integer.parseInt(session.getAttribute("idUsuario").toString()),
                Tipo.valueById(Integer.parseInt(req.getParameter("tipo"))),
                req.getParameter("descricao")
        );

        boolean success;
        if (categoria.getId() == 0)
            success = insert(categoria) > 0;
        else
            success = update(categoria);

        req.getRequestDispatcher("subcategorias.jsp").forward(req, res);
    }


    public int insert(Categoria categoria){
        var existsInDataBase = new CategoriaDAOImpl().getById(categoria.getId()) != null;

        if (!existsInDataBase)
            return new CategoriaDAOImpl().inserir(categoria);
        else
            return 0;
    }


    public boolean update(Categoria categoria){
        var existsInDataBase = new CategoriaDAOImpl().getById(categoria.getId()) != null;

        if (existsInDataBase)
            return new CategoriaDAOImpl().update(categoria);

        return false;
    }

}