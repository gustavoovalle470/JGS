/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.bo.subscription;

import co.com.jgs.bo.utils.Cities;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "companies")
@NamedQueries({
    @NamedQuery(name = "Companies.findAll", query = "SELECT c FROM Companies c"),
    @NamedQuery(name = "Companies.findById", query = "SELECT c FROM Companies c WHERE c.id = :id"),
    @NamedQuery(name = "Companies.findByName", query = "SELECT c FROM Companies c WHERE c.name = :name"),
    @NamedQuery(name = "Companies.findByMailAddress", query = "SELECT c FROM Companies c WHERE c.mailAddress = :mailAddress"),
    @NamedQuery(name = "Companies.findByContactEmail", query = "SELECT c FROM Companies c WHERE c.contactEmail = :contactEmail"),
    @NamedQuery(name = "Companies.findByContactPhone", query = "SELECT c FROM Companies c WHERE c.contactPhone = :contactPhone"),
    @NamedQuery(name = "Companies.findByLastChange", query = "SELECT c FROM Companies c WHERE c.lastChange = :lastChange"),
    @NamedQuery(name = "Companies.findByRowVersion", query = "SELECT c FROM Companies c WHERE c.rowVersion = :rowVersion")})
public class Companies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "mail_address")
    private String mailAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contact_email")
    private String contactEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contact_phone")
    private String contactPhone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_change")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChange;
    @Basic(optional = false)
    @NotNull
    @Column(name = "row_version")
    private int rowVersion;
    @JoinColumn(name = "principal_city", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonProperty("principalCity")
    private Cities principalCity;

    public Companies() {
    }

    public Companies(Integer id) {
        this.id = id;
    }

    public Companies(Integer id, String name, String mailAddress, String contactEmail, String contactPhone, Date lastChange, int rowVersion) {
        this.id = id;
        this.name = name;
        this.mailAddress = mailAddress;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
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

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
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

    public Cities getPrincipalCity() {
        return principalCity;
    }

    public void setPrincipalCity(Cities principalCity) {
        this.principalCity = principalCity;
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
        if (!(object instanceof Companies)) {
            return false;
        }
        Companies other = (Companies) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.jgs.bo.security.Companies[ id=" + id + " ]";
    }
    
}
