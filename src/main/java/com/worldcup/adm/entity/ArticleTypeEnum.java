package com.worldcup.adm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArticleTypeEnum {
    BLOCKCHAIN(1, "区块链"), OTHER(99, "其他");

    private Integer type;
    private String name;
}
