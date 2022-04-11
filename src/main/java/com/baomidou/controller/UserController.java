package com.baomidou.controller;
import com.baomidou.commonutils.R;
import com.baomidou.entity.User;
import com.baomidou.entity.vo.RegisterVo;
import com.baomidou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangyuexin
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;


    //用户名和密码登录
    @PostMapping("login")
    public R login(@RequestBody User user){
        String token =userService.login(user);
        return R.ok().data("token",token);
    }
    //注册
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        userService.register(registerVo);
        return R.ok();
    }
    //查询所有的用户信息
    @GetMapping("getAllusers")
    public  R getAllusers(){
        List<User> users = userService.list(null);
        return R.ok().data("result",users);
    }
    //根据id查询用户信息
    @GetMapping("getUser/{id}")
    public  R searchUser(@PathVariable String id){
        User user = userService.getById(id);
        return R.ok().data("user",user);
    }
    //删除用户
    @DeleteMapping("deleteUser/{id}")
    public R deleteUser(@PathVariable String id){
        boolean removeById = userService.removeById(id);
        if (removeById) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

