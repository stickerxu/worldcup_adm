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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    private String primaryTitle = "";
    @Column(columnDefinition = "varchar(100) not null default '' COMMENT '副标题'")
    private String secondTitle = "";
    @Column(columnDefinition = "text COMMENT '正文内容'")
    private String content;
    @Column(columnDefinition = "int(6) not null default 0 COMMENT 'pdf原始文件id'")
    private Integer pdfPrimaryFileId = 0;
    @Column(columnDefinition = "int(3) not null default 0 COMMENT 'pdf文章页码'")
    private Integer pdfPageNumber = 0;
    @Column(columnDefinition = "tinyint(1) not null default 0 COMMENT '文章状态：0、默认；1、已更新高频词汇；2、已使用'")
    private Integer status = 0;
    @Column(columnDefinition = "varchar(1000) not null default '' COMMENT '包含的高频词汇单词'")
    private String containWords = "";
    @Column(columnDefinition = "int(3) not null default 0 COMMENT '包含的高频词汇单词数量'")
    private Integer containWordSize = 0;
    @UpdateTimestamp
    private Date updateTime;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createTime;

    @OneToOne
    @JoinColumn(name = "pdfPrimaryFileId", referencedColumnName = "id", insertable = false, updatable = false)
    private EnglishArticleFile articleFile;

}
