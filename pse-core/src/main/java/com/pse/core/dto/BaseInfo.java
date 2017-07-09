package com.pse.core.dto;

import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

/**
 *
 * @author Mark Baldwin B. Martinez on 5 Jan 2017
 *
 */

public abstract class BaseInfo {

    private Long id;
    private String createdBy;
    private DateTime createdDate;
    private String updatedBy;
    private DateTime updatedDate;
    private Boolean deleted = false;

    public abstract String getType();

    @Override
    public final String toString() {
        return this.toStringCreator().toString();
    }

    protected ToStringCreator toStringCreator() {
        return new ToStringCreator(this)
                .append("id", id)
                .append("created", createdDate)
                .append("createdBy", createdBy)
                .append("updated", createdDate)
                .append("updatedBy", updatedBy)
                .append("deleted", deleted);
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
