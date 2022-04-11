package com.baomidou.entity.vo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangyuexin
 * @since 2022-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterVo implements Serializable {
    private String username;
    private String password;
    private String email;




}
