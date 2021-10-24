package com.melih.sinik.springbootaop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.melih.sinik.springbootaop.util.SessionContext;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author Melih ŞİNİK
 * @since 13.10.2021
 */
@Getter
@Setter
@MappedSuperclass
@ToString
public abstract class BaseEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "cre_user", nullable = false, length = 36)
    private String creUser;

    @Column(name = "cre_date", nullable = false)
    private Date creDate;

    @Column(name = "upd_user", length = 36)
    private String updUser;

    @Column(name = "upd_date")
    private Date updDate;

    @PrePersist
    public void onPrePersist() {
        this.id = UUID.randomUUID().toString();
        this.creDate = new Date();
        this.setCreUser(SessionContext.getSessionData().getUserId());
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updDate = new Date();
        this.setUpdUser(SessionContext.getSessionData().getUserId());
    }
}
