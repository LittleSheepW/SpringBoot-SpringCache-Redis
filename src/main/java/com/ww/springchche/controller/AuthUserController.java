package com.ww.springchche.controller;



import com.ww.springchche.pojo.AuthUser;
import com.ww.springchche.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Program: springboot-springcache-redis
 * @Description: AuthUserController 权限用户控制层
 *
 * SpringBoot + JPA + SpringCache + Redis进行集成
 * findUserById,addUser,updateUser,deleteUserById四个方法加入缓存，key的策略自定义生成,
 * 经过测试以上四个方法如果是对同一条数据操作时会对应对同一缓存进行操作,因为findUserById的参数就是id,
 * 所以simpleKey和objectKey两种生成策略生成的redis key一致。
 * @Author: Sun
 * @Create: 2019-04-19 12:04
 * @Version: 1.0
 **/
@RestController
@RequestMapping(value = "auth")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    @GetMapping(value = "test")
    public String test() {
        return "test";
    }

    /**
    * @Description: 获取所有用户
    * @Param: []
    * @Return: java.util.List<com.ww.com.ww.springchche.pojo.AuthUser>
    * @Exception
    * @Author: Sun
    * @Date: 2019-04-19 12:05
    */
    @GetMapping(value = "user")
    public List<AuthUser> user() {
        List<AuthUser> authUsers = authUserService.findAll(new Sort(Sort.Direction.DESC, "account"));
        return authUsers;
    }

    /**
    * @Description: 获取单个用户
    * @Param: [id]
    * @Return: com.ww.com.ww.springchche.pojo.AuthUser
    * @Exception
    * @Author: Sun
    * @Date: 2019-04-19 12:05
    */
    @GetMapping(value = "user/{id}")
    public AuthUser findUserById(@PathVariable Long id) {
        // authUserRepository.getOne(id)  存在问题
        return authUserService.findById(id);
    }

    /**
    * @Description: 保存单个用户(有则修改，无则添加)
    * @Param: [authUser]
    * @Return: void
    * @Exception
    * @Author: Sun
    * @Date: 2019-04-19 12:05
    */
    @PostMapping(value = "user")
    public void addUser(@RequestBody AuthUser authUser) {
        authUserService.save(authUser);
    }

    /**
    * @Description: 修改单个用户
    * @Param: [authUser]
    * @Return: void
    * @Exception
    * @Author: Sun
    * @Date: 2019-04-19 12:05
    */
    @PutMapping(value = "user")
    public void updateUser(@RequestBody AuthUser authUser) {
        authUserService.save(authUser);
    }

    /**
    * @Description: 删除单个用户
    * @Param: [id]
    * @Return: void
    * @Exception
    * @Author: Sun
    * @Date: 2019-04-19 12:05
    */
    @DeleteMapping(value = "user/{id}")
    public void deleteUserById(@PathVariable Long id) {
        authUserService.deleteById(id);
    }

    /**
    * @Description: 根据用户账号查询
    * @Param: [account]
    * @Return: com.ww.com.ww.springchche.pojo.AuthUser
    * @Exception
    * @Author: Sun
    * @Date: 2019-04-19 12:06
    */
    @GetMapping(value = "findUserByAccount/{account}")
    public AuthUser findUserByAccount(@PathVariable String account) {
        return authUserService.findByAccount(account);
    }

   /**
   * @Description: 根据用户账号和密码查询
   * @Param: [account, pwd]
   * @Return: com.ww.com.ww.springchche.pojo.AuthUser
   * @Exception
   * @Author: Sun
   * @Date: 2019-04-19 12:06
   */
    @GetMapping(value = "findUserByAccountAndPwd/{account}/{pwd}")
    public AuthUser findUserByAccountAndPwd(@PathVariable(value = "account") String account,
                                            @PathVariable(value = "pwd") String pwd) {
        return authUserService.findByAccountAndPwd(account, pwd);
    }

    /**
    * @Description: 查询id大于多少的用户账号，JPA中通过pageable对象进行分页(分页时前台传递页数至后台，后台要进行计算size*page-1)
    * @Param: [id]
    * @Return: java.util.List<com.ww.com.ww.springchche.pojo.AuthUser>
    * @Exception
    * @Author: Sun
    * @Date: 2019-04-19 12:06
    */
    @GetMapping(value = "findAllByIdGreaterThan/{id}")
    public List<AuthUser> findAllByIdGreaterThan(@PathVariable(value = "id") Long id) {
        PageRequest pageable = PageRequest.of(0, 3);
        return authUserService.findAllByIdGreaterThan(id, pageable);
    }

}
