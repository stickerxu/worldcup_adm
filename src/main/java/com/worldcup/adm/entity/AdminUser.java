package com.worldcup.adm.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
public class AdminUser extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(10) not null default ''")
    private String username;
    @Column(columnDefinition = "varchar(20) not null default ''")
    private String password;
    @Column(columnDefinition = "varchar(10) not null default ''")
    private String role;
    @UpdateTimestamp
    private Date updateTime;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createTime;
}
