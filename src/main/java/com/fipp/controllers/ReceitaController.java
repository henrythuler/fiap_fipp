package com.fipp.controllers;

import com.fipp.dao.CategoriaDAOImpl;
import com.fipp.dao.ReceitaDAOImpl;
import com.fipp.dao.SubcategoriaDAOImpl;
import com.fipp.models.entities.Receita;
import com.fipp.models.enums.Metodo;
import com.fipp.models.enums.Status;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet("/receita")
public class ReceitaController extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));

        if (id == 0) {
            req.setAttribute("receitas", getAll());
            req.getRequestDispatcher("receitas.jsp").forward(req, res);
        }
        else {
            req.setAttribute("receita", getById(id));
            req.getRequestDispatcher("receitaForm.jsp").forward(req, res);
        }
    }


    public Receita getById(int id){
        return new ReceitaDAOImpl().getById(id);
    }


    public ArrayList<Receita> getAll(){
        return new ReceitaDAOImpl().getAll(); //TO DO: Sorted by [descricao]
    }


    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        var receita = new Receita(
                Integer.parseInt(req.getParameter("id")),
                Integer.parseInt(session.getAttribute("idUsuario").toString()),
                Date.valueOf(req.getParameter("data")),
                new BigDecimal(req.getParameter("valor")),
                Metodo.valueById(Integer.parseInt(req.getParameter("metodo"))),
                req.getParameter("descricao"),
                new CategoriaDAOImpl().getById(Integer.parseInt(req.getParameter("categoria"))),
                new SubcategoriaDAOImpl().getById(Integer.parseInt(req.getParameter("subcategoria"))),
                Status.valueById(Integer.parseInt(req.getParameter("status"))),
                req.getParameter("pagador")
        );

        boolean success;
        if (receita.getId() == 0)
            success = insert(receita) > 0;
        else
            success = update(receita);

        req.getRequestDispatcher("receitas.jsp").forward(req, res);
    }


    public int insert(Receita receita){
        var existsInDataBase = new ReceitaDAOImpl().getById(receita.getId()) != null;

        if (!existsInDataBase)
            return new ReceitaDAOImpl().inserir(receita);
        else
            return 0;
    }


    public boolean update(Receita receita){
        var existsInDataBase = new ReceitaDAOImpl().getById(receita.getId()) != null;

        if (existsInDataBase)
            return new ReceitaDAOImpl().update(receita);

        return false;
    }

}