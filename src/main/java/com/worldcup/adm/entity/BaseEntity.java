package com.worldcup.adm.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity {
    private Integer page;
    private Integer size = 1;
}
