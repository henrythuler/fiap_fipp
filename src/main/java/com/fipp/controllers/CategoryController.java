package com.fipp.controllers;

import com.fipp.dao.CategoryDaoImpl;
import com.fipp.models.entities.Category;
import com.fipp.models.enums.Type;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/category")
public class CategoryController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
        var dao = new CategoryDaoImpl();

        HttpSession session = req.getSession();

        if (id == 0) {

            session.setAttribute("categories", dao.getAll());

            req.setAttribute("firstRequest", req.getServletPath());

            if(req.getParameter("load").equals("1")){
                req.getRequestDispatcher("subcategory").forward(req, res);
            }

            req.getRequestDispatcher("income-form.jsp").forward(req, res);

        }
        else {
            req.setAttribute("category", dao.getById(id));
            req.getRequestDispatcher("category-form.jsp").forward(req, res);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        var category = new Category(
                Integer.parseInt(req.getParameter("id")),
                Integer.parseInt(session.getAttribute("userId").toString()),
                Type.valueOf(req.getParameter("type")),
                req.getParameter("description")
        );

        var dao = new CategoryDaoImpl();
        boolean success = category.getId() == 0 ? dao.insert(category) : dao.update(category);

        if (success)
            req.getRequestDispatcher("subcategories.jsp").forward(req, res);
        else
            req.getRequestDispatcher("subcategoryError.jsp").forward(req, res);
    }

}