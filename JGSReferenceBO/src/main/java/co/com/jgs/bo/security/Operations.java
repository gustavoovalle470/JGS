/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.bo.security;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "operations")
@NamedQueries({
    @NamedQuery(name = "Operations.findAll", query = "SELECT o FROM Operations o"),
    @NamedQuery(name = "Operations.findById", query = "SELECT o FROM Operations o WHERE o.operationsPK.id = :id"),
    @NamedQuery(name = "Operations.findByServiceId", query = "SELECT o FROM Operations o WHERE o.operationsPK.serviceId = :serviceId"),
    @NamedQuery(name = "Operations.findByName", query = "SELECT o FROM Operations o WHERE o.name = :name"),
    @NamedQuery(name = "Operations.findByDesciption", query = "SELECT o FROM Operations o WHERE o.desciption = :desciption"),
    @NamedQuery(name = "Operations.findByActive", query = "SELECT o FROM Operations o WHERE o.active = :active"),
    @NamedQuery(name = "Operations.findByPath", query = "SELECT o FROM Operations o WHERE o.path = :path"),
    @NamedQuery(name = "Operations.findByAuditable", query = "SELECT o FROM Operations o WHERE o.auditable = :auditable")})
public class Operations implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OperationsPK operationsPK;
    @Size(max = 300)
    @Column(name = "name")
    private String name;
    @Size(max = 500)
    @Column(name = "desciption")
    private String desciption;
    @Column(name = "active")
    private Short active;
    @Size(max = 500)
    @Column(name = "path")
    private String path;
    @Column(name = "auditable")
    private Short auditable;
    @JoinColumn(name = "service_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Services services;

    public Operations() {
    }

    public Operations(OperationsPK operationsPK) {
        this.operationsPK = operationsPK;
    }

    public Operations(int id, int serviceId) {
        this.operationsPK = new OperationsPK(id, serviceId);
    }

    public OperationsPK getOperationsPK() {
        return operationsPK;
    }

    public void setOperationsPK(OperationsPK operationsPK) {
        this.operationsPK = operationsPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Short getAuditable() {
        return auditable;
    }

    public void setAuditable(Short auditable) {
        this.auditable = auditable;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (operationsPK != null ? operationsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operations)) {
            return false;
        }
        Operations other = (Operations) object;
        if ((this.operationsPK == null && other.operationsPK != null) || (this.operationsPK != null && !this.operationsPK.equals(other.operationsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.jgs.persistence.DAO.system.Operations[ operationsPK=" + operationsPK + " ]";
    }
    
}
