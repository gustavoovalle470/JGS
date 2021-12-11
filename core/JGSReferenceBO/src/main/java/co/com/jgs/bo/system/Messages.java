/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.bo.system;

import co.com.jgs.bo.security.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "messages")
@NamedQueries({
    @NamedQuery(name = "Messages.findAll", query = "SELECT m FROM Messages m"),
    @NamedQuery(name = "Messages.findById", query = "SELECT m FROM Messages m WHERE m.id = :id"),
    @NamedQuery(name = "Messages.findByType", query = "SELECT m FROM Messages m WHERE m.type = :type"),
    @NamedQuery(name = "Messages.findByTitle", query = "SELECT m FROM Messages m WHERE m.title = :title"),
    @NamedQuery(name = "Messages.findByMessage", query = "SELECT m FROM Messages m WHERE m.message = :message"),
    @NamedQuery(name = "Messages.findByLastChange", query = "SELECT m FROM Messages m WHERE m.lastChange = :lastChange"),
    @NamedQuery(name = "Messages.findByRowVersion", query = "SELECT m FROM Messages m WHERE m.rowVersion = :rowVersion")})
@JsonIgnoreProperties({"lastChange", "rowVersion", "lastUserChange"})
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "message")
    private String message;
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

    public Messages() {
    }

    public Messages(Integer id) {
        this.id = id;
    }

    public Messages(Integer id, String type, String title, String message, Date lastChange, int rowVersion) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.message = message;
        this.lastChange = lastChange;
        this.rowVersion = rowVersion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        if (!(object instanceof Messages)) {
            return false;
        }
        Messages other = (Messages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.jgs.bo.Messages[ id=" + id + " ]";
    }
    
    /**
     * Reemplaza todos los & por la data de usuario, esta vendra separada por coma.
     * @param allData String la data a reemplazar separada por coma.
     */
    public void replaceData(String allData){
        for(String data: allData.split(",")){
            setMessage(getMessage().replaceFirst("&", data));
        }
    }
}
