/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.bo.system;

import co.com.jgs.bo.security.Users;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "moduleconfigurations")
@NamedQueries({
    @NamedQuery(name = "Moduleconfigurations.findAll", query = "SELECT m FROM Moduleconfigurations m"),
    @NamedQuery(name = "Moduleconfigurations.findById", query = "SELECT m FROM Moduleconfigurations m WHERE m.id = :id"),
    @NamedQuery(name = "Moduleconfigurations.findByName", query = "SELECT m FROM Moduleconfigurations m WHERE m.name = :name"),
    @NamedQuery(name = "Moduleconfigurations.findByType", query = "SELECT m FROM Moduleconfigurations m WHERE m.type = :type"),
    @NamedQuery(name = "Moduleconfigurations.findByValue", query = "SELECT m FROM Moduleconfigurations m WHERE m.value = :value"),
    @NamedQuery(name = "Moduleconfigurations.findByLastChange", query = "SELECT m FROM Moduleconfigurations m WHERE m.lastChange = :lastChange"),
    @NamedQuery(name = "Moduleconfigurations.findByRowVersion", query = "SELECT m FROM Moduleconfigurations m WHERE m.rowVersion = :rowVersion")})
public class Moduleconfigurations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_change")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChange;
    @Basic(optional = false)
    @NotNull
    @Column(name = "row_version")
    private int rowVersion;
    @JoinColumn(name = "last_user_change", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Users lastUserChange;

    public Moduleconfigurations() {
    }

    public Moduleconfigurations(Integer id) {
        this.id = id;
    }

    public Moduleconfigurations(Integer id, String name, String type, String value, Date lastChange, int rowVersion) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.lastChange = lastChange;
        this.rowVersion = rowVersion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public int getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(int rowVersion) {
        this.rowVersion = rowVersion;
    }

    public Users getLastUserChange() {
        return lastUserChange;
    }

    public void setLastUserChange(Users lastUserChange) {
        this.lastUserChange = lastUserChange;
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
        if (!(object instanceof Moduleconfigurations)) {
            return false;
        }
        Moduleconfigurations other = (Moduleconfigurations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.jgs.bo.Moduleconfigurations[ id=" + id + " ]";
    }
    
}
