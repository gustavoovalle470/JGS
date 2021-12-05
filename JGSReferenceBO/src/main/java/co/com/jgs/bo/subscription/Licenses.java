/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.bo.subscription;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "licenses")
@NamedQueries({
    @NamedQuery(name = "Licenses.findAll", query = "SELECT l FROM Licenses l"),
    @NamedQuery(name = "Licenses.findById", query = "SELECT l FROM Licenses l WHERE l.id = :id"),
    @NamedQuery(name = "Licenses.findByStart", query = "SELECT l FROM Licenses l WHERE l.start = :start"),
    @NamedQuery(name = "Licenses.findByFinish", query = "SELECT l FROM Licenses l WHERE l.finish = :finish"),
    @NamedQuery(name = "Licenses.findByAutoRenoval", query = "SELECT l FROM Licenses l WHERE l.autoRenoval = :autoRenoval"),
    @NamedQuery(name = "Licenses.findByLastChange", query = "SELECT l FROM Licenses l WHERE l.lastChange = :lastChange"),
    @NamedQuery(name = "Licenses.findByRowVersion", query = "SELECT l FROM Licenses l WHERE l.rowVersion = :rowVersion")})
public class Licenses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    @Basic(optional = false)
    @NotNull
    @Column(name = "finish")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finish;
    @Basic(optional = false)
    @NotNull
    @Column(name = "auto_renoval")
    private short autoRenoval;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_change")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChange;
    @JoinTable(name = "moduleforlicense", joinColumns = {
        @JoinColumn(name = "Modules_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "licenses_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Modules> modules;
    @Basic(optional = false)
    @NotNull
    @Column(name = "row_version")
    private int rowVersion;
    @JoinColumn(name = "company", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Companies company;
    
    public Licenses() {
    }

    public Licenses(String id) {
        this.id = id;
    }

    public Licenses(String id, Date start, Date finish, short autoRenoval, Date lastChange, int rowVersion) {
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.autoRenoval = autoRenoval;
        this.lastChange = lastChange;
        this.rowVersion = rowVersion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public short getAutoRenoval() {
        return autoRenoval;
    }

    public void setAutoRenoval(short autoRenoval) {
        this.autoRenoval = autoRenoval;
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

    public Companies getCompany() {
        return company;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public List<Modules> getModules() {
        return modules;
    }

    public void setModules(List<Modules> modules) {
        this.modules = modules;
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
        if (!(object instanceof Licenses)) {
            return false;
        }
        Licenses other = (Licenses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.jgs.bo.Licenses[ id=" + id + " ]";
    }
    
}
