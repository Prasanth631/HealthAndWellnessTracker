package com.healthwellness.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.healthwellness.dao.DietRecommendationDAO;
import com.healthwellness.model.DietRecommendation;

public class DietRecommendationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DietRecommendationDAO dietRecommendationDAO;

    public void init() {
        dietRecommendationDAO = new DietRecommendationDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bmiCategory = request.getParameter("bmiCategory");
        DietRecommendation recommendation = dietRecommendationDAO.getDietRecommendation(bmiCategory);
        request.setAttribute("recommendation", recommendation);
        request.getRequestDispatcher("/diet-recommendation.jsp").forward(request, response);
    }
}