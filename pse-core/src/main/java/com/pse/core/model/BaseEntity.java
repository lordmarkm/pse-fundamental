package com.pse.core.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.pse.core.util.AuthenticationUtil;

/**
 *
 * @author Mark Baldwin B. Martinez on 5 Jan 2017
 *
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by", updatable = false, length = 250)
    private String createdBy;

    @Column(name = "created_date", updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDate;

    @Column(name = "updated_by", length = 250)
    private String updatedBy;

    @Column(name = "updated_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedDate;

    @Column(name = "deleted")
    @Type(type = "yes_no")
    private Boolean deleted = false;

    @PrePersist
    public void create() {
        this.createdDate = DateTime.now();
        this.updatedDate = DateTime.now();
        this.createdBy = AuthenticationUtil.getLoggedInUsername();
        this.updatedBy = AuthenticationUtil.getLoggedInUsername();
    }

    @PreUpdate
    public void update() {
        this.updatedDate = DateTime.now();
        this.updatedBy = AuthenticationUtil.getLoggedInUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public DateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(DateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
