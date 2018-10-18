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

@Getter
@Setter
@Entity
public class EnglishArticleFile extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8849086998108365792L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(50) not null default '' COMMENT '文件名称'")
    private String fileName;
    @Column(columnDefinition = "varchar(50) not null default '' COMMENT '原文件名称'")
    private String originalName;
    @Column(columnDefinition = "tinyint(1) not null default 0 COMMENT '文件状态：0、未处理（默认）；1、已处理'")
    private Integer status = 0;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createTime;
}
