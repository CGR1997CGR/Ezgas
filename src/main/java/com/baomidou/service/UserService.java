package com.baomidou.service;

import com.baomidou.entity.User;
import com.baomidou.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangyuexin
 * @since 2022-04-08
 */
public interface UserService extends IService<User> {
    String login(User user);
    void register(RegisterVo registerVo);
}
