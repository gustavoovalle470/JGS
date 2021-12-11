/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.bo.subscription;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "modules")
@NamedQueries({
    @NamedQuery(name = "Modules.findAll", query = "SELECT m FROM Modules m"),
    @NamedQuery(name = "Modules.findById", query = "SELECT m FROM Modules m WHERE m.id = :id"),
    @NamedQuery(name = "Modules.findByName", query = "SELECT m FROM Modules m WHERE m.name = :name"),
    @NamedQuery(name = "Modules.findByDescripcion", query = "SELECT m FROM Modules m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Modules.findByActive", query = "SELECT m FROM Modules m WHERE m.active = :active"),
    @NamedQuery(name = "Modules.findByLastChange", query = "SELECT m FROM Modules m WHERE m.lastChange = :lastChange"),
    @NamedQuery(name = "Modules.findByRowVersion", query = "SELECT m FROM Modules m WHERE m.rowVersion = :rowVersion")})
public class Modules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private short active;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_change")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChange;
    @Basic(optional = false)
    @NotNull
    @Column(name = "row_version")
    private int rowVersion;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "moduleforlicense", 
        joinColumns = @JoinColumn(name = "modules_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "licenses_id", referencedColumnName = "id"))
    private List<Licenses> licenses;

    public Modules() {
    }

    public Modules(String id) {
        this.id = id;
    }

    public Modules(String id, String name, short active, Date lastChange, int rowVersion) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.lastChange = lastChange;
        this.rowVersion = rowVersion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
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

    public List<Licenses> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<Licenses> licenses) {
        this.licenses = licenses;
    }

    @Override
    public String toString() {
        return "co.com.jgs.bo.Modules[ id=" + id + " ]";
    }
    
}
