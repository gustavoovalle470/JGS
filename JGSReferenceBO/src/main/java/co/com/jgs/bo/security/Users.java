/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.bo.security;

import co.com.jgs.bo.system.Catalogs;
import co.com.jgs.bo.subscription.Companies;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByLastPasswordChange", query = "SELECT u FROM Users u WHERE u.lastPasswordChange = :lastPasswordChange"),
    @NamedQuery(name = "Users.findByLastLogin", query = "SELECT u FROM Users u WHERE u.lastLogin = :lastLogin"),
    @NamedQuery(name = "Users.findByTriesToConnect", query = "SELECT u FROM Users u WHERE u.triesToConnect = :triesToConnect"),
    @NamedQuery(name = "Users.findByLastChange", query = "SELECT u FROM Users u WHERE u.lastChange = :lastChange"),
    @NamedQuery(name = "Users.findByRowVersion", query = "SELECT u FROM Users u WHERE u.rowVersion = :rowVersion")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_password_change")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordChange;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tries_to_connect")
    private int triesToConnect;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_change")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChange;
    @Basic(optional = false)
    @NotNull
    @Column(name = "row_version")
    private int rowVersion;
    @JoinColumns({
        @JoinColumn(name = "catalog_user_status", referencedColumnName = "catalog"),
        @JoinColumn(name = "item_user_status", referencedColumnName = "itemId")})
    @ManyToOne(optional = false)
    @JsonProperty("userStatus")
    private Catalogs userStatus;
    @JoinColumn(name = "companyid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonProperty("companIdy")
    private Companies companyId;

    public Users() {
    }

    public Users(String username) {
        this.username = username;
    }

    public Users(String username, String password, Date lastPasswordChange, Date lastLogin, int triesToConnect, Date lastChange, int rowVersion) {
        this.username = username;
        this.password = password;
        this.lastPasswordChange = lastPasswordChange;
        this.lastLogin = lastLogin;
        this.triesToConnect = triesToConnect;
        this.lastChange = lastChange;
        this.rowVersion = rowVersion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getTriesToConnect() {
        return triesToConnect;
    }

    public void setTriesToConnect(int triesToConnect) {
        this.triesToConnect = triesToConnect;
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

    public Catalogs getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Catalogs userStatus) {
        this.userStatus = userStatus;
    }

    public Companies getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Companies companyId) {
        this.companyId = companyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.jgs.bo.security.Users[ username=" + username + " ]";
    }
    
}
