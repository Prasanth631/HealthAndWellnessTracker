package com.healthwellness.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.healthwellness.dao.BMIDAO;
import com.healthwellness.model.BMI;

public class BMICalculatorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BMIDAO bmiDAO;

    public void init() {
        bmiDAO = new BMIDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/bmi-calculator.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            float weight = Float.parseFloat(request.getParameter("weight"));
            float height = Float.parseFloat(request.getParameter("height")) / 100; // Convert cm to meters

            int userId = 0;
            String userIdParam = request.getParameter("userId");
            if (userIdParam != null && !userIdParam.isEmpty()) {
                userId = Integer.parseInt(userIdParam);
            }

            float bmiValue = calculateBMI(weight, height);
            String bmiCategory = getBMICategory(bmiValue);

            BMI bmi = new BMI(userId, weight, height, bmiValue, bmiCategory);
            bmiDAO.saveBMI(bmi);

            request.setAttribute("bmi", bmi);
            request.getRequestDispatcher("/bmi-result.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input. Please enter valid numbers for weight and height.");
            request.getRequestDispatcher("/bmi-calculator.jsp").forward(request, response);
        }
    }

    private float calculateBMI(float weight, float height) {
        return weight / (height * height);
    }

    private String getBMICategory(float bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 25) {
            return "Normal";
        } else {
            return "Overweight";
        }
    }
}
