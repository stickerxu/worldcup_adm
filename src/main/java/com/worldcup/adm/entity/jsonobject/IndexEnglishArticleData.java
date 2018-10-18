package com.worldcup.adm.entity.jsonobject;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class IndexEnglishArticleData implements Serializable {
    private static final long serialVersionUID = 8653473269791570522L;

    private Integer todayNewFile = 0;
    private Integer todayNewArticle = 0;
    private Integer todayNewWord = 0;

    private Integer historyTotalFile = 0;
    private Integer historyTotalArticle = 0;
    private Integer historyTotalWord = 0;
}
