package com.worldcup.adm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class ArticleType extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2745328377650682885L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(20) NOT NULL DEFAULT ''")
    private String name;
    //1 不显示；2 显示
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 0")
    private Integer status = 1;
    @Column(columnDefinition = "tinyint(3) NOT NULL DEFAULT 0")
    private Integer weight = 0;
}
