package com.worldcup.adm.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
public class EnglishWord extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -3200516722512167059L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(30) not null default ''")
    private String word;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createTime;
}
