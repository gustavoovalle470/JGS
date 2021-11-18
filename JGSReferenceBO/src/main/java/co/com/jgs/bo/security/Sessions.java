/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.bo.security;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "sessions")
@NamedQueries({
    @NamedQuery(name = "Sesions.findAll", query = "SELECT s FROM Sessions s"),
    @NamedQuery(name = "Sesions.findByUsername", query = "SELECT s FROM Sessions s WHERE s.sesionsPK.username = :username"),
    @NamedQuery(name = "Sesions.findBySessionId", query = "SELECT s FROM Sessions s WHERE s.sesionsPK.sessionId = :sessionId"),
    @NamedQuery(name = "Sesions.findByStartIn", query = "SELECT s FROM Sessions s WHERE s.startIn = :startIn")})
public class Sessions implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SessionsPK sesionsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startIn;
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonProperty("user")
    private Users users;

    public Sessions() {
    }

    public Sessions(SessionsPK sesionsPK) {
        this.sesionsPK = sesionsPK;
    }

    public Sessions(SessionsPK sesionsPK, Date startIn) {
        this.sesionsPK = sesionsPK;
        this.startIn = startIn;
    }

    public Sessions(String username, String sessionId) {
        this.sesionsPK = new SessionsPK(username, sessionId);
    }

    public SessionsPK getSesionsPK() {
        return sesionsPK;
    }

    public void setSesionsPK(SessionsPK sesionsPK) {
        this.sesionsPK = sesionsPK;
    }

    public Date getStartIn() {
        return startIn;
    }

    public void setStartIn(Date startIn) {
        this.startIn = startIn;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sesionsPK != null ? sesionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sessions)) {
            return false;
        }
        Sessions other = (Sessions) object;
        if ((this.sesionsPK == null && other.sesionsPK != null) || (this.sesionsPK != null && !this.sesionsPK.equals(other.sesionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.jgs.bo.Sesions[ sesionsPK=" + sesionsPK + " ]";
    }
    
}
