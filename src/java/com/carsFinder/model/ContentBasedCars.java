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
 * @author zhouyizhou
 */
public class ContentBasedCars {
    private List list;
	private ResultSet rs = null;
        
    public List<CarInfo>  getCarlist(String make, String model, String year){
         //CarInfo carInfo = new CarInfo();
         DBO dbo = new DBO();
         dbo.open();
         String itemA=year+"/"+make+"/"+model;
         System.out.println("itemA:"+itemA);
         List<CarInfo> carList = new ArrayList<CarInfo>();
         try{
              rs = dbo.executeQuery("select * from contentBased where itemA='"+itemA+"' order by corr desc");
              while(rs.next()){
                    double corr = rs.getDouble("corr");// I will make sure there is no bug here.
                    if(corr!=0){
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
              rs = dbo.executeQuery("select * from contentBased where itemA like '%"+itemA+"%' order by corr desc");
              while(rs.next()){
                  double corr = rs.getDouble("corr");
                  if(corr!=0){
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
