/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carsFinder.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xmrui_000
 */
@Entity
@Table(name = "record")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarInfo.findAll", query = "SELECT c FROM CarInfo c"),
    @NamedQuery(name = "CarInfo.findByRecordID", query = "SELECT c FROM CarInfo c WHERE c.recordID = :recordID")})
public class CarInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RecordID")
    private Integer recordID;
    @Lob
    @Size(max = 65535)
    @Column(name = "carName")
    private String carName;
    @Lob
    @Size(max = 65535)
    @Column(name = "year")
    private String year;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "price")
    private String price;
    @Lob
    @Size(max = 65535)
    @Column(name = "mileage")
    private String mileage;
    @Lob
    @Size(max = 65535)
    @Column(name = "engineDescription")
    private String engineDescription;
    @Lob
    @Size(max = 65535)
    @Column(name = "color")
    private String color;
    @Lob
    @Size(max = 65535)
    @Column(name = "doorCount")
    private String doorCount;
    @Lob
    @Size(max = 65535)
    @Column(name = "driveTrain")
    private String driveTrain;
    @Lob
    @Size(max = 65535)
    @Column(name = "bodyStyle")
    private String bodyStyle;
    @Lob
    @Size(max = 65535)
    @Column(name = "transmission")
    private String transmission;
    @Lob
    @Size(max = 65535)
    @Column(name = "imageUrl")
    private String imageUrl;
    @Lob
    @Size(max = 65535)
    @Column(name = "expertreview")
    private String expertreview;
    

    public CarInfo() {
    }

    public CarInfo(Integer recordID) {
        this.recordID = recordID;
    }

    public CarInfo(Integer recordID, String price) {
        this.recordID = recordID;
        this.price = price;
    }

    public Integer getRecordID() {
        return recordID;
    }

    public void setRecordID(Integer recordID) {
        this.recordID = recordID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getEngineDescription() {
        return engineDescription;
    }

    public void setEngineDescription(String engineDescription) {
        this.engineDescription = engineDescription;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDoorCount() {
        return doorCount;
    }

    public void setDoorCount(String doorCount) {
        this.doorCount = doorCount;
    }

    public String getDriveTrain() {
        return driveTrain;
    }

    public void setDriveTrain(String driveTrain) {
        this.driveTrain = driveTrain;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getExpertreview(){
        return expertreview;
    }
    
    public void setExpertreview(String expertreview_inner){
        this.expertreview=expertreview_inner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordID != null ? recordID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarInfo)) {
            return false;
        }
        CarInfo other = (CarInfo) object;
        if ((this.recordID == null && other.recordID != null) || (this.recordID != null && !this.recordID.equals(other.recordID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carsFinder.entity.CarInfo[ recordID=" + recordID + " ]";
    }
    
}
