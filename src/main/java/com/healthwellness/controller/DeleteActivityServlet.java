package com.healthwellness.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete-activity")
public class DeleteActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        
        if (id != null && !id.isEmpty()) {
            System.out.println("Deleting activity with id: " + id);
            response.sendRedirect("activity-tracker");
        } else {
            response.sendRedirect("activity-tracker");
        }
    }
}