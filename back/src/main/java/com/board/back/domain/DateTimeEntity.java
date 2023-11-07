package com.board.back.domain;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class DateTimeEntity {

    @CreatedDate
    @Column(name="reg_dt")
    private LocalDateTime regDt;

    @LastModifiedDate
    @Column(name="chg_dt")
    private LocalDateTime chgDt;

}
