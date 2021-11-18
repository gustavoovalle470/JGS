/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.bo.security;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "audit_logs")
@NamedQueries({
    @NamedQuery(name = "AuditLogs.findAll", query = "SELECT a FROM AuditLogs a"),
    @NamedQuery(name = "AuditLogs.findById", query = "SELECT a FROM AuditLogs a WHERE a.id = :id"),
    @NamedQuery(name = "AuditLogs.findByWsinvoked", query = "SELECT a FROM AuditLogs a WHERE a.wsinvoked = :wsinvoked"),
    @NamedQuery(name = "AuditLogs.findByMethodInvoked", query = "SELECT a FROM AuditLogs a WHERE a.methodInvoked = :methodInvoked"),
    @NamedQuery(name = "AuditLogs.findByParametersInvoked", query = "SELECT a FROM AuditLogs a WHERE a.parametersInvoked = :parametersInvoked"),
    @NamedQuery(name = "AuditLogs.findBySuccesOperation", query = "SELECT a FROM AuditLogs a WHERE a.succesOperation = :succesOperation"),
    @NamedQuery(name = "AuditLogs.findByResponseMessage", query = "SELECT a FROM AuditLogs a WHERE a.responseMessage = :responseMessage"),
    @NamedQuery(name = "AuditLogs.findByOperationDate", query = "SELECT a FROM AuditLogs a WHERE a.operationDate = :operationDate")})
public class AuditLogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "wsinvoked")
    private String wsinvoked;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "methodinvoked")
    private String methodInvoked;
    @Size(max = 5000)
    @Column(name = "parametersinvoked")
    private String parametersInvoked;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "succesoperation")
    private String succesOperation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "responsemessage")
    private String responseMessage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "operationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operationDate;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne
    private Users username;

    public AuditLogs() {
    }

    public AuditLogs(Integer id) {
        this.id = id;
    }

    public AuditLogs(Integer id, String wsinvoked, String methodInvoked, String succesOperation, String responseMessage, Date operationDate) {
        this.id = id;
        this.wsinvoked = wsinvoked;
        this.methodInvoked = methodInvoked;
        this.succesOperation = succesOperation;
        this.responseMessage = responseMessage;
        this.operationDate = operationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWsinvoked() {
        return wsinvoked;
    }

    public void setWsinvoked(String wsinvoked) {
        this.wsinvoked = wsinvoked;
    }

    public String getMethodInvoked() {
        return methodInvoked;
    }

    public void setMethodInvoked(String methodInvoked) {
        this.methodInvoked = methodInvoked;
    }

    public String getParametersInvoked() {
        return parametersInvoked;
    }

    public void setParametersInvoked(String parametersInvoked) {
        this.parametersInvoked = parametersInvoked;
    }

    public String getSuccesOperation() {
        return succesOperation;
    }

    public void setSuccesOperation(String succesOperation) {
        this.succesOperation = succesOperation;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
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
        if (!(object instanceof AuditLogs)) {
            return false;
        }
        AuditLogs other = (AuditLogs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.jgs.bo.security.AuditLogs[ id=" + id + " ]";
    }
    
}
