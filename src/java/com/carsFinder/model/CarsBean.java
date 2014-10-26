/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carsFinder.model;

import com.carsFinder.dao.DBO;
import com.carsFinder.entity.CarInfo;
import com.carsFinder.entity.ModelInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xmrui_000
 */
public class CarsBean {

    private ResultSet rs = null;

    public List<ModelInfo> getMake() {
        String sql = "select distinct(make) from expertreview";
        DBO dbo = new DBO();
        dbo.open();
        List<ModelInfo> modelList = new ArrayList<ModelInfo>();
        try {
            rs = dbo.executeQuery(sql);
            while (rs.next()) {
                ModelInfo modelInfo = new ModelInfo();
                modelInfo.setMake(rs.getString(1));
                modelList.add(modelInfo);
            }

            return modelList;
        } catch (Exception e) {
            return null;
        } finally {
            dbo.close();
        }
    }

    public List<ModelInfo> getModel(String make) {
        String sql = "select distinct model from expertreview where make ='" + make + "'";
        DBO dbo = new DBO();
        dbo.open();
        List<ModelInfo> modelList = new ArrayList<ModelInfo>();
        try {
            rs = dbo.executeQuery(sql);
            while (rs.next()) {
                ModelInfo modelInfo = new ModelInfo();
                modelInfo.setModel(rs.getString(1));
                modelList.add(modelInfo);
            }

            return modelList;
        } catch (Exception e) {
            return null;
        } finally {
            dbo.close();
        }
    }

    public List getModel(String[] make) {

        DBO dbo = new DBO();
        dbo.open();
        List modelList = new ArrayList();
        try {
            for (int i = 0; i < make.length; i++) {
                rs = dbo.executeQuery("select distinct model from expertreview where make ='" + make[i] + "'");
                while (rs.next()) {

                    modelList.add(rs.getString("model"));
                }
            }
            return modelList;
        } catch (Exception e) {
            return null;
        } finally {
            dbo.close();
        }
    }

    public List getYear(String[] make, String[] model) {

        DBO dbo = new DBO();
        dbo.open();
        List yearList = new ArrayList();
        try {
            for (int i = 0; i < make.length; i++) {
                for (int j =0;j<model.length;j++) {
                    rs = dbo.executeQuery("select distinct year from expertreview where make ='" + make[i] + "' and model ='" + model[j] + "'");
                    while (rs.next()) {

                        yearList.add(rs.getString("year"));
                    }
                }
            }
            return yearList;
        } catch (Exception e) {
            return null;
        } finally {
            dbo.close();
        }
    }

    public String getExpertReview(String make, String model, String year) {
        String sql = "select expertreview from expertreview where make ='" + make + "' and model ='" + model + "' and year ='" + year + "'";
        DBO dbo = new DBO();
        dbo.open();
        String expertReview = null;
        try {
            rs = dbo.executeQuery(sql);
            rs.next();
            expertReview = rs.getString("expertreview");

            return expertReview;
        } catch (Exception e) {
            return null;
        } finally {
            dbo.close();
        }
    }

    private int EVERYPAGENUM = 12;

    public void setEVERYPAGENUM(int EVERYPAGENUM) {
        this.EVERYPAGENUM = EVERYPAGENUM;
    }

    public int getListCount(String[] makes, String[] models, String[] years) {
        DBO dbo = new DBO();
        dbo.open();
        int count = -1;
        try {
            if (makes.length == 1 && models.length == 1 && years.length == 1) {
                if (models[0].equals("Any Model")) {
                    rs = dbo.executeQuery("select count(*) from record where carName like '%" + makes[0] + "%' order by RecordID asc");
                    rs.next();
                    count += rs.getInt(1);
                } else if (years[0].equals("Any Year")) {
                    rs = dbo.executeQuery("select count(*) from record where carName like '%" + makes[0] + "%' and carName like '%" + models[0] + "%' order by RecordID asc");
                    rs.next();
                    count += rs.getInt(1);
                } else {
                    rs = dbo.executeQuery("select count(*) from record where carName like '%" + makes[0] + "%' and carName like '%" + models[0] + "%' and year = '" + years[0] + "' order by RecordID asc");
                    rs.next();
                    count += rs.getInt(1);
                }
            } else if (makes.length == 1 && models.length == 1 && years.length > 1) {
                for (int i = 0; i < years.length; i++) {
                    rs = dbo.executeQuery("select count(*) from record where carName like '%" + makes[0] + "%' and carName like '%" + models[0] + "%' and year = '" + years[i] + "' order by RecordID asc");
                    rs.next();
                    count += rs.getInt(1);
                }
            } else if (makes.length == 1 && models.length > 1 && years.length == 1) {
                for (int i = 0; i < models.length; i++) {
                    rs = dbo.executeQuery("select count(*) from record where carName like '%" + makes[0] + "%' and carName like '%" + models[i] + "%'and year = '" + years[0] + "' order by RecordID asc");
                    rs.next();
                    count += rs.getInt(1);
                }
            } else if (makes.length == 1 && models.length > 1 && years.length > 1) {
                for (int i = 0; i < models.length; i++) {
                    for (int j = 0; j < models.length; j++) {
                        rs = dbo.executeQuery("select count(*) from record where carName like '%" + makes[0] + "%' and carName like '%" + models[i] + "%'and year = '" + years[j] + "' order by RecordID asc");
                        rs.next();
                        count += rs.getInt(1);
                    }
                }
            } else if (makes.length > 1) {
                for (int i = 0; i < makes.length; i++) {
                    for (int j = 0; j < models.length; j++) {
                        rs = dbo.executeQuery("select count(*) from record where carName like '%" + makes[i] + "%' and carName like '%" + models[j] + "%'order by RecordID asc");
                        rs.next();
                        count += rs.getInt(1);
                    }
                }
            }
            return count;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            dbo.close();
        }
    }

    public int getPageCount(int listCount) {
        int count = listCount;
        if (count % EVERYPAGENUM == 0) {
            return count / EVERYPAGENUM;
        } else {
            return count / EVERYPAGENUM + 1;
        }
    }

    public List<CarInfo> getCarlistByPage(String[] makes, String[] models, String[] years, int page) {
        DBO dbo = new DBO();
        dbo.open();
        List<CarInfo> carList = new ArrayList<CarInfo>();

        try {
            if (models[0].equals("Any Model") && years[0].equals("Any Year")) {
                rs = dbo.executeQuery("select * from record where  carName like '%" + makes[0] + "%' order by RecordID asc");
                for (int n = 0; n < (page - 1) * (EVERYPAGENUM / models.length); n++) {
                    rs.next();
                }
                for (int t = 0; t < (EVERYPAGENUM / models.length); t++) {
                    if (rs.next()) {

                        CarInfo carInfo = new CarInfo();
                        carInfo.setRecordID(Integer.parseInt(rs.getString("RecordID")));
                        carInfo.setCarName(rs.getString("carName"));
                        carInfo.setYear(rs.getString("year"));
                        if (rs.getString("bodyStyle") != null) {
                            carInfo.setBodyStyle(rs.getString("bodyStyle"));
                        } else {
                            carInfo.setBodyStyle("NA");
                        }
                        if (rs.getString("mileage") != null) {
                            carInfo.setMileage(rs.getString("mileage"));
                        } else {
                            carInfo.setMileage("NA");
                        }
                        if (rs.getString("doorCount") != null) {
                            carInfo.setDoorCount(rs.getString("doorCount"));
                        } else {
                            carInfo.setDoorCount("NA");
                        }
                        if (rs.getString("engineDescription") != null) {
                            carInfo.setEngineDescription(rs.getString("engineDescription"));
                        } else {
                            carInfo.setEngineDescription("NA");
                        }
                        if (rs.getString("driveTrain") != null) {
                            carInfo.setDriveTrain(rs.getString("driveTrain"));
                        } else {
                            carInfo.setDriveTrain("NA");
                        }
                        if (rs.getString("color") != null) {
                            carInfo.setColor(rs.getString("color"));
                        } else {
                            carInfo.setColor("NA");
                        }
                        carInfo.setImageUrl(rs.getString("imageUrl"));
                        carList.add(carInfo);
                    } else {
                        break;
                    }
                }
            }else if ((!models[0].equals("Any Model")) && years[0].equals("Any Year")) {
                for (int i = 0; i < makes.length; i++) {
                    for (int j = 0; j < models.length; j++) {

                        rs = dbo.executeQuery("select * from record where  carName like '%" + makes[i] + "%' and carName like '%" + models[j] + "%' order by RecordID asc");
                        for (int n = 0; n < (page - 1) * (EVERYPAGENUM / models.length); n++) {
                            rs.next();
                        }
                        for (int t = 0; t < (EVERYPAGENUM / models.length); t++) {
                            if (rs.next()) {

                                CarInfo carInfo = new CarInfo();
                                carInfo.setRecordID(Integer.parseInt(rs.getString("RecordID")));
                                carInfo.setCarName(rs.getString("carName"));
                                carInfo.setYear(rs.getString("year"));
                                if (rs.getString("bodyStyle") != null) {
                                    carInfo.setBodyStyle(rs.getString("bodyStyle"));
                                } else {
                                    carInfo.setBodyStyle("NA");
                                }
                                if (rs.getString("mileage") != null) {
                                    carInfo.setMileage(rs.getString("mileage"));
                                } else {
                                    carInfo.setMileage("NA");
                                }
                                if (rs.getString("doorCount") != null) {
                                    carInfo.setDoorCount(rs.getString("doorCount"));
                                } else {
                                    carInfo.setDoorCount("NA");
                                }
                                if (rs.getString("engineDescription") != null) {
                                    carInfo.setEngineDescription(rs.getString("engineDescription"));
                                } else {
                                    carInfo.setEngineDescription("NA");
                                }
                                if (rs.getString("driveTrain") != null) {
                                    carInfo.setDriveTrain(rs.getString("driveTrain"));
                                } else {
                                    carInfo.setDriveTrain("NA");
                                }
                                if (rs.getString("color") != null) {
                                    carInfo.setColor(rs.getString("color"));
                                } else {
                                    carInfo.setColor("NA");
                                }

                                carInfo.setImageUrl(rs.getString("imageUrl"));
                                carList.add(carInfo);
                            } else {
                                break;
                            }

                        }
                    }
                }
            } else {
                for (int i = 0; i < makes.length; i++) {
                    for (int j = 0; j < models.length; j++) {
                        for (int k = 0; k < years.length; k++) {
                            rs = dbo.executeQuery("select * from record where  carName like '%" + makes[i] + "%' and carName like '%" + models[j] + "%' and year = '" + years[k] + "' order by RecordID asc");
                            for (int n = 0; n < (page - 1) * (EVERYPAGENUM / models.length); n++) {
                                rs.next();
                            }
                            for (int t = 0; t < (EVERYPAGENUM / models.length); t++) {
                                if (rs.next()) {

                                    CarInfo carInfo = new CarInfo();
                                    carInfo.setRecordID(Integer.parseInt(rs.getString("RecordID")));
                                    carInfo.setCarName(rs.getString("carName"));
                                    carInfo.setYear(rs.getString("year"));
                                    if (rs.getString("bodyStyle") != null) {
                                        carInfo.setBodyStyle(rs.getString("bodyStyle"));
                                    } else {
                                        carInfo.setBodyStyle("NA");
                                    }
                                    if (rs.getString("mileage") != null) {
                                        carInfo.setMileage(rs.getString("mileage"));
                                    } else {
                                        carInfo.setMileage("NA");
                                    }
                                    if (rs.getString("doorCount") != null) {
                                        carInfo.setDoorCount(rs.getString("doorCount"));
                                    } else {
                                        carInfo.setDoorCount("NA");
                                    }
                                    if (rs.getString("engineDescription") != null) {
                                        carInfo.setEngineDescription(rs.getString("engineDescription"));
                                    } else {
                                        carInfo.setEngineDescription("NA");
                                    }
                                    if (rs.getString("driveTrain") != null) {
                                        carInfo.setDriveTrain(rs.getString("driveTrain"));
                                    } else {
                                        carInfo.setDriveTrain("NA");
                                    }
                                    if (rs.getString("color") != null) {
                                        carInfo.setColor(rs.getString("color"));
                                    } else {
                                        carInfo.setColor("NA");
                                    }

                                    carInfo.setImageUrl(rs.getString("imageUrl"));
                                    carList.add(carInfo);
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            return carList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            dbo.close();
        }
    }

}
