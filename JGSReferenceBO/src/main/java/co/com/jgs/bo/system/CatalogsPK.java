/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jgs.bo.system;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gusta
 */
@Embeddable
public class CatalogsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "catalog")
    private int catalog;
    @Basic(optional = false)
    @NotNull
    @Column(name = "itemid")
    private int itemId;

    public CatalogsPK() {
    }

    public CatalogsPK(int catalog, int itemId) {
        this.catalog = catalog;
        this.itemId = itemId;
    }

    public int getCatalog() {
        return catalog;
    }

    public void setCatalog(int catalog) {
        this.catalog = catalog;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) catalog;
        hash += (int) itemId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogsPK)) {
            return false;
        }
        CatalogsPK other = (CatalogsPK) object;
        if (this.catalog != other.catalog) {
            return false;
        }
        if (this.itemId != other.itemId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.jgs.bo.security.CatalogsPK[ catalog=" + catalog + ", itemId=" + itemId + " ]";
    }
    
}
