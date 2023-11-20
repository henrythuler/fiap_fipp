package com.fipp.controllers;

import com.fipp.dao.SubcategoryDaoImpl;
import com.fipp.models.entities.Subcategory;
import com.fipp.models.enums.Type;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import jakarta.servlet.http.HttpSession;

@WebServlet("/subcategory")
public class SubcategoryController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
        int categoryId = req.getParameter("categoryId") == null ? 0 : Integer.parseInt(req.getParameter("categoryId"));
        var dao = new SubcategoryDaoImpl();

        if (id == 0 && categoryId == 0) {

            session.setAttribute("subcategories", dao.getAll());

            req.getRequestDispatcher("home.jsp").forward(req, res);

        } else if (id == 0 && categoryId > 0) {
            req.setAttribute("subcategory", dao.getByCategoryId(id));
            req.getRequestDispatcher("subcategoryForm.jsp").forward(req, res);
        } else {
            req.setAttribute("subcategory", dao.getById(id));
            req.getRequestDispatcher("subcategory-form.jsp").forward(req, res);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        var subcategory = new Subcategory(
                Integer.parseInt(req.getParameter("id")),
                Integer.parseInt(req.getParameter("categoryId")),
                Integer.parseInt(session.getAttribute("userId").toString()),
                Type.valueOf(req.getParameter("type")),
                req.getParameter("description")
        );

        var dao = new SubcategoryDaoImpl();
        boolean success = subcategory.getId() == 0 ? dao.insert(subcategory) : dao.update(subcategory);

        if (success)
            req.getRequestDispatcher("subcategories.jsp").forward(req, res);
        else
            req.getRequestDispatcher("subcategoryError.jsp").forward(req, res);
    }

}