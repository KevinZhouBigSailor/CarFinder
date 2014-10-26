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
@Table(name = "vehiclemodelyear")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModelSearch.findAll", query = "SELECT m FROM ModelSearch m"),
    @NamedQuery(name = "ModelSearch.findById", query = "SELECT m FROM ModelSearch m WHERE m.id = :id"),
    @NamedQuery(name = "ModelSearch.findByYear", query = "SELECT m FROM ModelSearch m WHERE m.year = :year"),
    @NamedQuery(name = "ModelSearch.findByMake", query = "SELECT m FROM ModelSearch m WHERE m.make = :make"),
    @NamedQuery(name = "ModelSearch.findByModel", query = "SELECT m FROM ModelSearch m WHERE m.model = :model")})
public class ModelSearch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year")
    private int year;
    @Size(max = 50)
    @Column(name = "make")
    private String make;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "model")
    private String model;

    public ModelSearch() {
    }

    public ModelSearch(Integer id) {
        this.id = id;
    }

    public ModelSearch(Integer id, int year, String model) {
        this.id = id;
        this.year = year;
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModelSearch)) {
            return false;
        }
        ModelSearch other = (ModelSearch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carsFinder.entity.ModelSearch[ id=" + id + " ]";
    }
    
}
