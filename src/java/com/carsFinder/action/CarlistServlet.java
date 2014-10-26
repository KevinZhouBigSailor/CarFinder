/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carsFinder.action;

import com.carsFinder.entity.CarInfo;
import com.carsFinder.model.CarsBean;
import com.carsFinder.model.RecommendCars;
import com.carsFinder.twitter.MainAction;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vo.StatusVO;

/**
 *
 * @author xmrui_000
 */
public class CarlistServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String method = request.getParameter("method").trim();
            String page = request.getParameter("page").trim();
            String first = "1";
            if(page.equals("first")){
                page="1";
                first = "first";
            }
            CarsBean carsBean = new CarsBean();
            RecommendCars recommendCar = new RecommendCars();
            //HttpSession session = request.getSession();
            if (method.equals("searchCar")) {

                String[] makes = request.getParameterValues("make");
                String[] models = request.getParameterValues("model");
                String[] years = request.getParameterValues("year");
                List modelList = carsBean.getModel(makes);
                List yearList = carsBean.getYear(makes, models);
                int  pageN = carsBean.getListCount(makes, models, years);
                List tags = new ArrayList();
                for(int i=0;i<makes.length;i++){tags.add(makes[i]);}
                for(int j=0;j<models.length;j++){tags.add(models[j]);}
                for(int k=0;k<years.length;k++){tags.add(years[k]);}
                int pageCount = carsBean.getPageCount(pageN);
                    String pageNum = String.valueOf(pageCount);
                
                 List<CarInfo> carList = carsBean.getCarlistByPage(makes, models,years,Integer.parseInt(page));

                    request.setAttribute("make", makes);
                    request.setAttribute("model", models);
                    request.setAttribute("year", years);
                    request.setAttribute("resultCount", String.valueOf(pageN+1));
                    //request.setAttribute("price", price);
                    request.setAttribute("tags", tags);
                    request.setAttribute("pageNum", pageNum);
                    request.setAttribute("currentPage", page);
                    request.setAttribute("carList", carList);
                    request.setAttribute("modelList", modelList);
                    request.setAttribute("yearList", yearList);
                     if(first.equals("first")){
                        request.getRequestDispatcher("/listBoard.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("/carList.jsp").forward(request, response);}
 
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
