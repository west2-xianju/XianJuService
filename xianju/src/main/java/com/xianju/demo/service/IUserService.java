package com.xianju.demo.service;

import com.xianju.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xianju.demo.vo.ForgetUser;
import com.xianju.demo.vo.Identify;
import com.xianju.demo.vo.JwtUserInfo;
import com.xianju.demo.vo.LoginUser;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author feige
 * @since 2023-04-04
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(LoginUser loginUser);

    Map<String, Object> forgetpwd(ForgetUser forgetuser);

    Map<String, Object> getUserInfo(String user_id);

    Map<String, Object> getUserPUN(int valueOf);

    Map<String, Object> updateUserNickname(String nickname, JwtUserInfo jwtUser);

    Map<String, Object> updateUserpwd(String password, JwtUserInfo jwtUser);

    Map<String, Object> getUserInformatiom(Integer userid);

    Map<String, Object> updateUserInfo(Integer userid, Identify info);
}
