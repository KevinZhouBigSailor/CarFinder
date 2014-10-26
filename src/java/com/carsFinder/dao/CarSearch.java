/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carsFinder.dao;

import com.carsFinder.entity.ModelInfo;
import com.carsFinder.model.CarsBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xmrui_000
 */
public class CarSearch extends HttpServlet {

    private List list;
    private ResultSet rs;



    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        char[] cbuf = new char[500];
        int amtread;

        PrintWriter out = response.getWriter();
        BufferedReader in = request.getReader();
        amtread = in.read(cbuf);
        cbuf[amtread] = '\0';
        String invalue = new String(cbuf, 0, amtread);
        System.out.println(invalue);
        try {
            String temporary = "Any Model,";

            CarsBean carsBean = new CarsBean();
            List<ModelInfo> listin = carsBean.getModel(invalue);
            for (int i = 0; i < listin.size(); i++) {
                System.out.println(listin.get(i).getModel());
                
                    temporary += listin.get(i).getModel() + ",";
                
            }
            char[] temp = temporary.toCharArray();
            String result = new String(temp, 0, temp.length - 2);
            System.out.println(result);
            out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("list Error");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }

}
