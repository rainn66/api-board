package com.board.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity {

    @CreatedBy
    @Column(name = "reg_user_id", updatable = false)
    private String regUserId;

    @LastModifiedBy
    @Column(name = "chg_user_id")
    private String chgUserId;

    @CreatedDate
    @Column(name="reg_dt", updatable = false)
    private LocalDateTime regDt;

    @LastModifiedDate
    @Column(name="chg_dt")
    private LocalDateTime chgDt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        regDt = now;
        chgDt = now;
    }

    @PreUpdate
    public void preUpdate() {
        chgDt = LocalDateTime.now();
    }

}
