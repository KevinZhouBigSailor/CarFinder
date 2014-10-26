/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carsFinder.action;

import com.carsFinder.entity.CarInfo;
import com.carsFinder.entity.ModelInfo;
import com.carsFinder.model.CarsBean;
import com.carsFinder.model.ContentBasedCars;
import com.carsFinder.model.RecommendCars;
import common.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import com.carsFinder.index.GetRecordId;
import com.carsFinder.twitter.MainAction;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;
import vo.StatusVO;

/**
 *
 * @author xmrui_000
 */
public class DetailServlet extends HttpServlet {

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
        BasicAction basicAction = new BasicAction();
        String item = basicAction.getCookieValue(Constants.ITEM, request, response);
        String cookieRecordid = basicAction.getCookieValue(Constants.COOKIERECORDID, request, response);
        try {
            
            String recordID = request.getParameter("recordID").trim();//ID!!!!!
            if(cookieRecordid!=null){
                String[] ids=cookieRecordid.split(" ");
                boolean newId=true;
                for(int x=0;x<ids.length;x++){
                    if(recordID.equals(ids[x]))
                    {newId=false;break;}
                }
                if(newId)
                    basicAction.saveCookie(Constants.COOKIERECORDID, cookieRecordid+" "+recordID, Constants.BROWSER_COOKIE_MAX_AGE, response);
            }else{
                basicAction.saveCookie(Constants.COOKIERECORDID, recordID, Constants.BROWSER_COOKIE_MAX_AGE, response);
            }
            
            GetRecordId getRecordId=new GetRecordId();
            List<CarInfo> recordIdsList=getRecordId.start(basicAction.getCookieValue(Constants.COOKIERECORDID, request, response));
            String carname = request.getParameter("carname").trim();
            String[] carName = carname.split(" ");
            String make = carName[0];
            String model = carName[1];
            String year = request.getParameter("year").trim();
            String itemA = year + "/" + make + "/" + model;
            RecommendCars recommendCar = new RecommendCars();
            ContentBasedCars contentBasedCars = new ContentBasedCars();
            CarsBean carsBean = new CarsBean();
            String yearCookie = "";
            String makeCookie = "";
            String modelCookie = "";
            if (item != null) {
                String[] items = item.split(" ");
                String[] itemSplit = items[items.length - 1].split("/");
                yearCookie = itemSplit[0];
                makeCookie = itemSplit[1];
                modelCookie = itemSplit[2];
            }else{
                item="";
            }
            
            MainAction mainA=new MainAction();
            mainA.setS1(make);
            mainA.setS2(model);
            mainA.excuteSearch();
            List<StatusVO> good_twitter=mainA.getTop5GoodStatus();
            List<StatusVO> bad_twitter=mainA.getTop5BadStatus();
            List<String> urls_flicter=mainA.getPhotoUrls();
            
            String expertReview = carsBean.getExpertReview(make, model, year);
            List<CarInfo> recomdCar = null;
            List<CarInfo> contntCar = null;
            List<CarInfo> contntCarCookie = null;
            if (year != null) {
                System.out.println("year:" + year);
                recomdCar = recommendCar.getCarlist(make, model, year);
                contntCar = contentBasedCars.getCarlist(make, model, year);
            } else {
                System.out.println("start recommend");
                recomdCar = recommendCar.getCarlist(make, model);
                contntCar = contentBasedCars.getCarlist(make, model);
            }
            contntCarCookie = contentBasedCars.getCarlist(makeCookie, modelCookie, yearCookie);

            request.setAttribute("contntCarCookie", contntCarCookie);
            request.setAttribute("expertReview", expertReview);
            request.setAttribute("recomdcar", recomdCar);
            request.setAttribute("contntCar", contntCar);
            request.setAttribute("itemCar", recordIdsList);
            request.setAttribute("goodtwitte", good_twitter);
            request.setAttribute("badtwitte", bad_twitter);
            basicAction.saveCookie(Constants.ITEM, item + " " + itemA, Constants.BROWSER_COOKIE_MAX_AGE, response);
            request.getRequestDispatcher("/recommendation.jsp").forward(request, response);

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
