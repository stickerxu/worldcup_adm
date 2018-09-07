package com.worldcup.adm.service;

import com.worldcup.adm.entity.LoginUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface LoginUserService {
    Page<LoginUser> listUserByCriteria(LoginUser loginUser, Sort sorts);
}
