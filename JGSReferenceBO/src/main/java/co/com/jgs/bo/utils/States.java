/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.bo.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "states")
@NamedQueries({
    @NamedQuery(name = "States.findAll", query = "SELECT s FROM States s"),
    @NamedQuery(name = "States.findById", query = "SELECT s FROM States s WHERE s.statesPK.id = :id"),
    @NamedQuery(name = "States.findByIdCountry", query = "SELECT s FROM States s WHERE s.statesPK.idCountry = :idCountry"),
    @NamedQuery(name = "States.findByStateName", query = "SELECT s FROM States s WHERE s.stateName = :stateName")})
public class States implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StatesPK statesPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "statename")
    private String stateName;
    @JoinColumn(name = "idcountry", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonProperty("countries")
    private Countries countries;

    public States() {
    }

    public States(StatesPK statesPK) {
        this.statesPK = statesPK;
    }

    public States(StatesPK statesPK, String stateName) {
        this.statesPK = statesPK;
        this.stateName = stateName;
    }

    public States(int id, int idCountry) {
        this.statesPK = new StatesPK(id, idCountry);
    }

    public StatesPK getStatesPK() {
        return statesPK;
    }

    public void setStatesPK(StatesPK statesPK) {
        this.statesPK = statesPK;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
    
    public Countries getCountries() {
        return countries;
    }

    public void setCountries(Countries countries) {
        this.countries = countries;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statesPK != null ? statesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof States)) {
            return false;
        }
        States other = (States) object;
        if ((this.statesPK == null && other.statesPK != null) || (this.statesPK != null && !this.statesPK.equals(other.statesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.jgs.bo.security.States[ statesPK=" + statesPK + " ]";
    }
    
}
