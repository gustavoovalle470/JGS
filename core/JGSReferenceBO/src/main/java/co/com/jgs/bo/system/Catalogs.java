/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.bo.system;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "catalogs")
@NamedQueries({
    @NamedQuery(name = "Catalogs.findAll", query = "SELECT c FROM Catalogs c"),
    @NamedQuery(name = "Catalogs.findByCatalog", query = "SELECT c FROM Catalogs c WHERE c.catalogsPK.catalog = :catalog"),
    @NamedQuery(name = "Catalogs.findByItemId", query = "SELECT c FROM Catalogs c WHERE c.catalogsPK.itemId = :itemId"),
    @NamedQuery(name = "Catalogs.findByItem", query = "SELECT c FROM Catalogs c WHERE c.item = :item"),
    @NamedQuery(name = "Catalogs.findByDescription", query = "SELECT c FROM Catalogs c WHERE c.description = :description"),
    @NamedQuery(name = "Catalogs.findByStatus", query = "SELECT c FROM Catalogs c WHERE c.status = :status"),
    @NamedQuery(name = "Catalogs.findByLastChange", query = "SELECT c FROM Catalogs c WHERE c.lastChange = :lastChange"),
    @NamedQuery(name = "Catalogs.findByRowVersion", query = "SELECT c FROM Catalogs c WHERE c.rowVersion = :rowVersion")})
public class Catalogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CatalogsPK catalogsPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Item")
    private String item;
    @Size(max = 250)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private short status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_change")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChange;
    @Basic(optional = false)
    @NotNull
    @Column(name = "row_version")
    private int rowVersion;

    public Catalogs() {
    }

    public Catalogs(CatalogsPK catalogsPK) {
        this.catalogsPK = catalogsPK;
    }

    public Catalogs(CatalogsPK catalogsPK, String item, short status, Date lastChange, int rowVersion) {
        this.catalogsPK = catalogsPK;
        this.item = item;
        this.status = status;
        this.lastChange = lastChange;
        this.rowVersion = rowVersion;
    }

    public Catalogs(int catalog, int itemId) {
        this.catalogsPK = new CatalogsPK(catalog, itemId);
    }

    public CatalogsPK getCatalogsPK() {
        return catalogsPK;
    }

    public void setCatalogsPK(CatalogsPK catalogsPK) {
        this.catalogsPK = catalogsPK;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catalogsPK != null ? catalogsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catalogs)) {
            return false;
        }
        Catalogs other = (Catalogs) object;
        if ((this.catalogsPK == null && other.catalogsPK != null) || (this.catalogsPK != null && !this.catalogsPK.equals(other.catalogsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.jgs.bo.security.Catalogs[ catalogsPK=" + catalogsPK + " ]";
    }
    
}
