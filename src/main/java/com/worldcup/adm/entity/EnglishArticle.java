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
public class EnglishArticle extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2633918315455388305L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(20) not null default '' COMMENT '文章类型'")
    private String type;
    @Column(columnDefinition = "varchar(50) not null default '' COMMENT '主标题'")
    private String primaryTitle;
    @Column(columnDefinition = "varchar(100) not null default '' COMMENT '副标题'")
    private String secondTitle;
    @Column(columnDefinition = "text COMMENT '正文内容'")
    private String content;
    @UpdateTimestamp
    private Date updateTime;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createTime;
}
