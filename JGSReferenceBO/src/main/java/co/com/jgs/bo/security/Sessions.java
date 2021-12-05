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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "sessions")
@NamedQueries({
    @NamedQuery(name = "Sessions.findAll", query = "SELECT s FROM Sessions s"),
    @NamedQuery(name = "Sessions.findByUsername", query = "SELECT s FROM Sessions s WHERE s.sessionsPK.username = :username"),
    @NamedQuery(name = "Sessions.findBySessionId", query = "SELECT s FROM Sessions s WHERE s.sessionsPK.sessionId = :sessionId"),
    @NamedQuery(name = "Sessions.findByStartIn", query = "SELECT s FROM Sessions s WHERE s.startIn = :startIn")})
public class Sessions implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SessionsPK sessionsPK;
    @Basic(optional = false)
    @Column(name = "start_in")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startIn;
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Sessions() {
    }

    public Sessions(SessionsPK sessionsPK) {
        this.sessionsPK = sessionsPK;
    }

    public Sessions(SessionsPK sessionsPK, Date startIn) {
        this.sessionsPK = sessionsPK;
        this.startIn = startIn;
    }

    public Sessions(String username, String sessionId) {
        this.sessionsPK = new SessionsPK(username, sessionId);
    }

    public SessionsPK getSessionsPK() {
        return sessionsPK;
    }

    public void setSessionsPK(SessionsPK sessionsPK) {
        this.sessionsPK = sessionsPK;
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
        hash += (sessionsPK != null ? sessionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sessions)) {
            return false;
        }
        Sessions other = (Sessions) object;
        if ((this.sessionsPK == null && other.sessionsPK != null) || (this.sessionsPK != null && !this.sessionsPK.equals(other.sessionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.jgs.dao.Sessions[ sessionsPK=" + sessionsPK + " ]";
    }
    
}
