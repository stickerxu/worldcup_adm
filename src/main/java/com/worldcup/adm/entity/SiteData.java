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
public class SiteData implements Serializable {
    private static final long serialVersionUID = 791967260034585616L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(100) not null default ''")
    private String dataKey;
    @Column(columnDefinition = "text")
    private String dataValue;
}
