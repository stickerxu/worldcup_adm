package com.worldcup.adm.service;

import com.worldcup.adm.entity.AdminUser;

public interface AdminUserService {
    AdminUser findFirstByUsername(String username);
}
