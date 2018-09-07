package com.worldcup.adm.repository;

import com.worldcup.adm.entity.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Integer>, JpaSpecificationExecutor<LoginUser> {

}
