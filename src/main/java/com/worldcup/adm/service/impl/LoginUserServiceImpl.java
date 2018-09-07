package com.worldcup.adm.service.impl;

import com.worldcup.adm.entity.LoginUser;
import com.worldcup.adm.repository.LoginUserRepository;
import com.worldcup.adm.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginUserServiceImpl implements LoginUserService {
    @Autowired
    private LoginUserRepository loginUserRepository;
    @Override
    public Page<LoginUser> listUserByCriteria(LoginUser loginUser, Sort sorts) {
        return loginUserRepository.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, PageRequest.of(loginUser.getPage(), loginUser.getSize(), sorts));
    }
}
