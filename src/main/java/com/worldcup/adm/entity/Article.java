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
public class Article extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8234412686185630203L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "tinyint(2)")
    private Integer type;
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 1")
    private Integer status = 1;
    @Column(columnDefinition = "varchar(20) NOT NULL DEFAULT ''")
    private String author = "";
    private String title;
    private String fileName;
    @Column(columnDefinition = "tinyint(3) NOT NULL DEFAULT 0")
    private Integer weight = 0;
    private Date publishTime;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createTime;
}
