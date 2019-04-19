package com.ww.springchche.service.impl;

import com.ww.springchche.pojo.AuthUser;
import com.ww.springchche.repository.AuthUserRepository;
import com.ww.springchche.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: springboot-springcache-redis
 * @Description: AuthUserService 业务层实现
 * @Author: Sun
 * @Create: 2019-04-19 13:22
 * @Version: 1.0
 **/
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Override
    public List<AuthUser> findAll(Sort account) {
        return authUserRepository.findAll(account);
    }

    @Override
    public AuthUser findById(Long id) {
        return authUserRepository.findById(id).get();
    }

    @Override
    public void save(AuthUser authUser) {
        authUserRepository.save(authUser);
    }

    @Override
    public void deleteById(Long id) {
        authUserRepository.deleteById(id);
    }

    @Override
    public AuthUser findByAccount(String account) {
        return authUserRepository.findByAccount(account);
    }

    @Override
    public AuthUser findByAccountAndPwd(String account, String pwd) {
        return authUserRepository.findByAccountAndPwd(account, pwd);
    }

    @Override
    public List<AuthUser> findAllByIdGreaterThan(Long id, PageRequest pageable) {
        return authUserRepository.findAllByIdGreaterThan(id, pageable);
    }
}
