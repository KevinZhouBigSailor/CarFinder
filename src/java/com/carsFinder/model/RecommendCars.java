/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carsFinder.model;

import com.carsFinder.dao.DBO;
import com.carsFinder.entity.CarInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xmrui_000
 */
public class RecommendCars {
    private List list;
	private ResultSet rs = null;
        
        
        
    public List getName() {
        String sql = "select distinct(make) from vehiclemodelyear";
        DBO dbo = new DBO();
        dbo.open();
        list = new ArrayList();
        try {
            rs = dbo.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            
            return list;
        } catch (Exception e) {
            return null;
        } finally {
            dbo.close();
        }
    }

    public List getModel(String make) {
        String sql = "select distinct model from vehiclemodelyear where make ='" + make + "'";
        DBO dbo = new DBO();
        dbo.open();
        list = new ArrayList();
        try {
            rs = dbo.executeQuery(sql);

            while (rs.next()) {
                list.add(rs.getString(1));
            }

            return list;
        } catch (Exception e) {
            return null;
        } finally {
            dbo.close();
        }
    }
        
        
        
        
        
        
        
        private int EVERYPAGENUM = 2;
    public void setEVERYPAGENUM(int EVERYPAGENUM){
    	this.EVERYPAGENUM=EVERYPAGENUM;
    }
          public int getPageCount() { //µÃµ½¹²¶àÉÙÒ³£¨¸ù¾ÝÃ¿Ò³ÒªÏÔÊ¾¼¸ÌõÐÅÏ¢£©
    	 int count = -1;
        if (count % EVERYPAGENUM == 0) {
            return count / EVERYPAGENUM;
        } else {
            return count / EVERYPAGENUM + 1;
        }
    }
        public List getCarlistByPage(String make, String model,int page) { 
            DBO dbo = new DBO();
            dbo.open();
            List list = new ArrayList();
            int qq = 0;
          try {
            rs = dbo.executeQuery("select * from record where carName like '%"+make+"%' and carName like '%"+model+"%' order by RecordID asc");
            for (int i = 0; i < (page - 1) * EVERYPAGENUM; i++) {
                rs.next();
            }
            for (int t = 0; t < EVERYPAGENUM; t++) {
                if (rs.next()) {
                    qq++;
                    List list2=new ArrayList();
                    list2.add(rs.getInt("id"));
    				list2.add(rs.getString("username"));
    				list2.add(rs.getString("password"));
    				list.add(list2);
                } else {
                    break; 
                }
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            dbo.close();
        }
        }
        
        
        public List<CarInfo>  getCarlist(String make, String model, String year){
         //CarInfo carInfo = new CarInfo();
         DBO dbo = new DBO();
         dbo.open();
         String itemA=year+"/"+make+"/"+model;
         System.out.println("itemA:"+itemA);
         List<CarInfo> carList = new ArrayList<CarInfo>();
         try{
              rs = dbo.executeQuery("select * from collaborative where itemA='"+itemA+"' order by relation desc");
              while(rs.next()){
              CarInfo carInfo = new CarInfo();
              String itemB=rs.getString("itemB");
              System.out.println("itemB:"+itemB);
              String[] carName=itemB.split("/");
              String itemB_year=carName[0];
              String itemB_make=carName[1];
              String itemB_model=carName[2];
               carInfo.setCarName(itemB_make+" "+itemB_model);
               carInfo.setYear(itemB_year);
               ResultSet rs_review = dbo.executeQuery("select expertReview from expertreview where year="+itemB_year+" and make='"+itemB_make+"' and model='"+itemB_model+"'");
               ResultSet rs_imageurl=dbo.executeQuery("select imageUrl from record where carName like '%"+itemB_make+"%' and carName like '%"+itemB_model+"%'");
               if(rs_review.next()){
                   String[] temp=rs_review.getString("expertreview").split("\\.");
                   String expertreview="";
                   for(int i=0;i<temp.length;i++){
                       if(i<8){
                           expertreview+=temp[i];
                           break;
                       }
                   }
                   carInfo.setExpertreview(expertreview+"......");
               }
               if(rs_imageurl.next()){
                   System.out.println(rs_imageurl.getString("imageUrl"));
                   carInfo.setImageUrl(rs_imageurl.getString("imageUrl"));
               }
                carList.add(carInfo);
            }
             
             return carList;
         }catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            dbo.close();
        }

        }
        
        public List<CarInfo>  getCarlist(String make, String model){
         //CarInfo carInfo = new CarInfo();
         DBO dbo = new DBO();
         dbo.open();
         String itemA=make+"/"+model;
         itemA=itemA.toLowerCase();
         System.out.println(itemA);
         List<CarInfo> carList = new ArrayList<CarInfo>();
         try{
              rs = dbo.executeQuery("select * from collaborative where itemA like '%"+itemA+"%' order by relation desc");
              while(rs.next()){
              CarInfo carInfo = new CarInfo();
              String itemB=rs.getString("itemB");
              System.out.println("itemB:"+itemB);
              String[] carName=itemB.split("/");
              String itemB_year=carName[0];
              String itemB_make=carName[1];
              String itemB_model=carName[2];
               carInfo.setCarName(itemB_make+" "+itemB_model);
               carInfo.setYear(itemB_year);
               ResultSet rs_review = dbo.executeQuery("select expertReview from expertreview where year="+itemB_year+" and make='"+itemB_make+"' and model='"+itemB_model+"'");
               ResultSet rs_imageurl=dbo.executeQuery("select imageUrl from record where carName like '%"+itemB_make+"'% and carName like '%"+itemB_model+"%'");
               if(rs_review.next()){
                   String[] temp=rs_review.getString("expertreview").split(".");
                   String expertreview="";
                   for(int i=0;i<temp.length;i++){
                       if(i<8){
                           expertreview+=temp[i];
                           break;
                       }
                   }
                   carInfo.setExpertreview(expertreview);
               }
               if(rs_imageurl.next()){
                   carInfo.setImageUrl(rs_imageurl.getString("imageUrl"));
               }
               carList.add(carInfo);
              }
             
             return carList;
         }catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            dbo.close();
        }

        }
        
        
}
