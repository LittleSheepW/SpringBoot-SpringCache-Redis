package com.ww.springchche.service;

import com.ww.springchche.pojo.AuthUser;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;


/**
 * @Program: springboot-springcache-redis
 * @Description: AuthUserService 业务层接口
 * @Author: Sun
 * @Create: 2019-04-19 13:21
 * @Version: 1.0
 **/
public interface AuthUserService {

    List<AuthUser> findAll(Sort account);

    AuthUser findById(Long id);

    void save(AuthUser authUser);

    void deleteById(Long id);

    AuthUser findByAccount(String account);

    AuthUser findByAccountAndPwd(String account, String pwd);

    List<AuthUser> findAllByIdGreaterThan(Long id, PageRequest pageable);
}
