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
@Table(name = "menulinks")
@NamedQueries({
    @NamedQuery(name = "Menulinks.findAll", query = "SELECT m FROM Menulinks m"),
    @NamedQuery(name = "Menulinks.findByMenuName", query = "SELECT m FROM Menulinks m WHERE m.menuName = :menuName"),
    @NamedQuery(name = "Menulinks.findByStatus", query = "SELECT m FROM Menulinks m WHERE m.status = :status"),
    @NamedQuery(name = "Menulinks.findByIsNode", query = "SELECT m FROM Menulinks m WHERE m.isNode = :isNode"),
    @NamedQuery(name = "Menulinks.findByParent", query = "SELECT m FROM Menulinks m WHERE m.parent = :parent"),
    @NamedQuery(name = "Menulinks.findByLink", query = "SELECT m FROM Menulinks m WHERE m.link = :link"),
    @NamedQuery(name = "Menulinks.findByIconExpression", query = "SELECT m FROM Menulinks m WHERE m.iconExpression = :iconExpression"),
    @NamedQuery(name = "Menulinks.findByLastChange", query = "SELECT m FROM Menulinks m WHERE m.lastChange = :lastChange"),
    @NamedQuery(name = "Menulinks.findByRowVersion", query = "SELECT m FROM Menulinks m WHERE m.rowVersion = :rowVersion")})
public class Menulinks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "menuName")
    private String menuName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private short status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isNode")
    private short isNode;
    @Size(max = 250)
    @Column(name = "parent")
    private String parent;
    @Size(max = 500)
    @Column(name = "link")
    private String link;
    @Size(max = 250)
    @Column(name = "iconExpression")
    private String iconExpression;
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

    public Menulinks() {
    }

    public Menulinks(String menuName) {
        this.menuName = menuName;
    }

    public Menulinks(String menuName, short status, short isNode, Date lastChange, int rowVersion) {
        this.menuName = menuName;
        this.status = status;
        this.isNode = isNode;
        this.lastChange = lastChange;
        this.rowVersion = rowVersion;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public short getIsNode() {
        return isNode;
    }

    public void setIsNode(short isNode) {
        this.isNode = isNode;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIconExpression() {
        return iconExpression;
    }

    public void setIconExpression(String iconExpression) {
        this.iconExpression = iconExpression;
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
        hash += (menuName != null ? menuName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menulinks)) {
            return false;
        }
        Menulinks other = (Menulinks) object;
        if ((this.menuName == null && other.menuName != null) || (this.menuName != null && !this.menuName.equals(other.menuName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.jgs.bo.Menulinks[ menuName=" + menuName + " ]";
    }
    
}
