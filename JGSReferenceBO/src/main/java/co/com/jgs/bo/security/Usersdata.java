/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.bo.security;

import co.com.jgs.bo.system.Catalogs;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gusta
 */
@Entity
@Table(name = "usersdata")
@NamedQueries({
    @NamedQuery(name = "Usersdata.findAll", query = "SELECT u FROM Usersdata u"),
    @NamedQuery(name = "Usersdata.findByUsername", query = "SELECT u FROM Usersdata u WHERE u.username = :username"),
    @NamedQuery(name = "Usersdata.findByName", query = "SELECT u FROM Usersdata u WHERE u.name = :name"),
    @NamedQuery(name = "Usersdata.findBySurname", query = "SELECT u FROM Usersdata u WHERE u.surname = :surname"),
    @NamedQuery(name = "Usersdata.findByEmail", query = "SELECT u FROM Usersdata u WHERE u.email = :email"),
    @NamedQuery(name = "Usersdata.findByPhone", query = "SELECT u FROM Usersdata u WHERE u.phone = :phone"),
    @NamedQuery(name = "Usersdata.findByAddress", query = "SELECT u FROM Usersdata u WHERE u.address = :address"),
    @NamedQuery(name = "Usersdata.findByLastChange", query = "SELECT u FROM Usersdata u WHERE u.lastChange = :lastChange"),
    @NamedQuery(name = "Usersdata.findByRowVersion", query = "SELECT u FROM Usersdata u WHERE u.rowVersion = :rowVersion")})
public class Usersdata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "last_change")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChange;
    @Basic(optional = false)
    @Column(name = "row_version")
    private int rowVersion;
    @JoinColumns({
        @JoinColumn(name = "Catalogs_genders", referencedColumnName = "catalog"),
        @JoinColumn(name = "Catalogs_gender", referencedColumnName = "itemId")})
    @ManyToOne(optional = false)
    private Catalogs gender;
    @JoinColumns({
        @JoinColumn(name = "catalogs_idTypes", referencedColumnName = "catalog"),
        @JoinColumn(name = "catalogs_idType", referencedColumnName = "itemId")})
    @ManyToOne(optional = false)
    private Catalogs documentType;
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Users users;

    public Usersdata() {
    }

    public Usersdata(String username) {
        this.username = username;
    }

    public Usersdata(String username, String name, String surname, String email, String phone, String address, Date lastChange, int rowVersion) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.lastChange = lastChange;
        this.rowVersion = rowVersion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Catalogs getGender() {
        return gender;
    }

    public void setGender(Catalogs gender) {
        this.gender = gender;
    }

    public Catalogs getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Catalogs documentType) {
        this.documentType = documentType;
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
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usersdata)) {
            return false;
        }
        Usersdata other = (Usersdata) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.jgs.jgspersistence.Usersdata[ username=" + username + " ]";
    }
    
}
