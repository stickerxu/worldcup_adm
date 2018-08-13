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
    private Integer type;
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 1")
    private Integer status;
    private String title;
    private String fileName;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createTime;

}
