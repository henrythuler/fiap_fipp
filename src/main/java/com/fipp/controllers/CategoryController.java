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

        if (id == 0) {
            req.setAttribute("categories", dao.getAll());
            req.getRequestDispatcher("subcategories.jsp").forward(req, res);
        }
        else {
            req.setAttribute("category", dao.getById(id));
            req.getRequestDispatcher("categoryForm.jsp").forward(req, res);
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