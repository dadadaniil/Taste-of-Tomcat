package com.example.firsttomcat.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "multiplyServlet", value = "/multiply-servlet")
public class MultiplyServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(MultiplyServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String param = request.getParameter("digit");
        int digit = Integer.parseInt(param);
        int result = digit * 2;
        logger.info("Result of multiplication " + digit + " is " + result);

        request.setAttribute("result", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/result.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error("Error while forwarding request", e);
        }

//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>Result: " + result + "</h1>");
//        out.println("</body></html>");
    }
}