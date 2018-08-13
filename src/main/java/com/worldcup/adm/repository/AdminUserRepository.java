package com.worldcup.adm.repository;

import com.worldcup.adm.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {
    AdminUser findFirstByUsername(String username);
}
