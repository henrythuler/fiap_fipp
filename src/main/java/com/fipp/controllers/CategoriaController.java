package com.fipp.controllers;

import com.fipp.dao.CategoriaDAOImpl;
import com.fipp.models.entities.Categoria;
import com.fipp.models.enums.Tipo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        int id = Integer.parseInt(req.getParameter("id"));
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        Tipo tipo = Tipo.valueById(Integer.parseInt(req.getParameter("tipo")));
        String descricao = req.getParameter("descricao");

        Categoria categoria = new Categoria(id,idUsuario,tipo,descricao);

        boolean success;
        if (id == 0)
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