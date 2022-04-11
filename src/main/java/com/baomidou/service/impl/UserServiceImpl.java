package com.baomidou.service.impl;

import com.baomidou.commonutils.JwtUtils;
import com.baomidou.commonutils.MD5;
import com.baomidou.commonutils.exceptionhandler.GuliException;
import com.baomidou.entity.User;
import com.baomidou.entity.vo.RegisterVo;
import com.baomidou.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangyuexin
 * @since 2022-04-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public String login(User user) {
        //用户名和密码登录
        //1、获取用户名和密码
        String username = user.getUsername();
        String password = user.getPassword();
        //2、如过用户名和密码为空，直接返回登录失败
        if (ObjectUtils.isEmpty(username)||ObjectUtils.isEmpty(password)){
            throw new GuliException(201,"用户名和密码为空");
        }
        //判断用户名是否正确
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user1 = baseMapper.selectOne(queryWrapper);
        if (user1 == null){
            throw new GuliException(201,"用户不存在");
        }
        //判断密码是否相等
        //数据库密码进行加密，不能直接对比
        //MD5对密码进行加密，再与数据库进行比较
        String password1 = user1.getPassword();
        if (!MD5.encrypt(password).equals(password1)){
            throw new GuliException(201,"密码错误");
        }
        //登录成功
        //按照jwt生产token返回
        String token = JwtUtils.getJwtToken(user1.getId().toString(), user1.getUsername());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
       //获取注册用户信息
        String registername = registerVo.getUsername();
        String registerpassword = registerVo.getPassword();
        String registeremail = registerVo.getEmail();
        //判断是否为空
        if (ObjectUtils.isEmpty(registername)||ObjectUtils.isEmpty(registerpassword)||ObjectUtils.isEmpty(registeremail)){
            throw new GuliException(201,"注册失败");
        }
        //判断用户名在数据库中是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",registername);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0){
            throw new GuliException(201,"用户名重复");
        }
        //将数据添加到数据库
        User user = new User();
        user.setUsername(registername);
        user.setPassword(MD5.encrypt(registerpassword));
        user.setEmail(registeremail);
        baseMapper.insert(user);
    }


}
