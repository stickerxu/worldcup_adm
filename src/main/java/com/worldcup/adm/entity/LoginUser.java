package com.worldcup.adm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
public class LoginUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5619835167434421102L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String realName; //注册时不填
    private String userEmail;
    private String userPhone;
    private String investCode; //注册时生成
    private Date registryTime;

}
