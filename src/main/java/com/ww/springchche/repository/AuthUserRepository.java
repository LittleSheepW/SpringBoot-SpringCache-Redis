package com.ww.springchche.repository;

import com.ww.springchche.pojo.AuthUser;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @Program: cloud-study
 * @Description: AuthUserRepository 持久层
 * @Author: Sun
 * @Create: 2019-04-19 12:15
 * @Version: 1.0
 */
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    /**
     * 根据用户账号查询
     * condition：缓存对象的条件，非必需，也需使用SpEL表达式，只有满足表达式条件的内容才会被缓存，在调用函数之前执行
     * 比如：@Cacheable(key = "#p0", condition = "#p0.length() < 3")，表示只有当第一个参数的长度小于3的时候才会被缓存。
     * @param account
     * @return
     */
    @Cacheable(value = "authuser", key = "#account", condition = "#account.length() > 5")
    AuthUser findByAccount(String account);

    /**
     * 根据用户账号和密码查询
     * @param account
     * @param pwd
     * @return
     */
    AuthUser findByAccountAndPwd(String account, String pwd);

    /**
     * 查询id大于多少的用户
     * unless：另外一个缓存条件参数，非必需，需使用SpEL表达式。
     * 它不同于condition参数的地方在于它的判断时机，该条件是在函数被调用之后才做判断的，所以它可以通过对result进行判断。
     * @param id
     * @return
     */
    @Cacheable(value = "authuser", key = "#id", unless = "#result == null")
    List<AuthUser> findAllByIdGreaterThan(Long id, Pageable pageable);

    /**
     * 新增或修改用户
     * @param s
     * @param <S>
     * @return
     */
    @Override
    @CachePut(value = "authuser", keyGenerator = "objectId")
    <S extends AuthUser> S save(S s);

    /**
     * 根据id查询用户
     * @param aLong
     * @return
     */
    @Override
    @Cacheable(value = "authuser", keyGenerator = "simpleKey")
    Optional<AuthUser> findById(Long aLong);

    /**
     * 根据id删除用户
     * @param aLong
     */
    @Override
    @CacheEvict(value = "authuser", keyGenerator = "simpleKey")
    void deleteById(Long aLong);
}
