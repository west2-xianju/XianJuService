package com.xianju.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xianju.demo.Util.IDUtil;
import com.xianju.demo.Util.JwtUtil;
import com.xianju.demo.entity.User;
import com.xianju.demo.mapper.UserMapper;
import com.xianju.demo.service.IUserService;
import com.xianju.demo.vo.ForgetUser;
import com.xianju.demo.vo.Identify;
import com.xianju.demo.vo.JwtUserInfo;
import com.xianju.demo.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author feige
 * @since 2023-04-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional
    public Map<String, Object> login(LoginUser loginUser) {

        //根据用户名和密码进行查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,loginUser.getUsername());
        User login=this.baseMapper.selectOne(wrapper);
        //结果不为空并且密码和传入密码匹配则生成token，并将用户名信息存在redis
        if(login != null&&passwordEncoder.matches(loginUser.getPassword(),login.getPassword())){

            //jwt
            String key = JwtUtil.getToken(login);

            //存入redis
            redisTemplate.opsForValue().set(key,login,240, TimeUnit.MINUTES);

            //返回数据token
            Map<String,Object> data = new HashMap<>();
            data.put("token",key);
            data.put("uid",login.getUid());
            data.put("blocked",login.getBlocked());
            return data;
        }else {
            return null;
        }
    }

    @Override
    @Transactional
    public Map<String, Object> forgetpwd(ForgetUser forgetuser) {
        HashMap<String, Object> data = new HashMap<>();
        if(Objects.nonNull(forgetuser.getSend_captcha()) && forgetuser.getSend_captcha().equals(forgetuser.getCaptcha())){
            LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(User::getUsername,forgetuser.getUsername());
            User user = new User();
            user.setPassword(passwordEncoder.encode(forgetuser.getPassword()));
            baseMapper.update(user,wrapper);
            User find=this.baseMapper.selectOne(wrapper);
            data.put("uid",find.getUid());
            data.put("username",find.getUsername());
            data.put("nickname",find.getNickname());
            data.put("email",find.getEmail());
            data.put("blocked",find.getBlocked());
        }
        return data;
    }

    @Override
    @Transactional
    public Map<String, Object> getUserInfo(String user_id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUid,user_id);
        User user=this.baseMapper.selectOne(wrapper);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",user.getUid());
        data.put("username",user.getUsername());
        data.put("nickname",user.getNickname());
        data.put("register_time",user.getRegisterTime());
        data.put("blocked",user.getBlocked());
        data.put("profile",user.getProfile());
        return data;
    }

    @Override
    @Transactional
    public Map<String, Object> getUserPUN(int seller_id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUid,seller_id);
        User user=this.baseMapper.selectOne(wrapper);
        HashMap<String, Object> data = new HashMap<>();
        data.put("username",user.getUsername());
        data.put("nickname",user.getNickname());
        data.put("profile",user.getProfile());
        return data;
    }

    @Override
    @Transactional
    public Map<String, Object> updateUserNickname(String nickname, JwtUserInfo jwtUser) {
        JSONObject jsonObject = JSON.parseObject(nickname);
        String ToNickname = jsonObject.getString("nickname");
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUid,jwtUser.getUserid());
        User user=this.baseMapper.selectOne(wrapper);
        user.setNickname(ToNickname);
        saveOrUpdate(user);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",user.getUid());
        data.put("username",user.getUsername());
        data.put("nickname",user.getNickname());
        data.put("blocked",user.getBlocked());
        return data;
    }

    @Override
    @Transactional
    public Map<String, Object> updateUserpwd(String password, JwtUserInfo jwtUser) {
        JSONObject jsonObject = JSON.parseObject(password);
        String ToPassword = jsonObject.getString("password");
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUid,jwtUser.getUserid());
        User user=this.baseMapper.selectOne(wrapper);
        user.setPassword(passwordEncoder.encode(ToPassword));
        saveOrUpdate(user);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",user.getUid());
        data.put("username",user.getUsername());
        data.put("password",user.getPassword());
        data.put("blocked",user.getBlocked());
        return data;
    }

    @Override
    @Transactional
    public Map<String, Object> getUserInformatiom(Integer userid) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUid,userid);
        User user=this.baseMapper.selectOne(wrapper);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",user.getUid());
        data.put("username",user.getUsername());
        data.put("nickname",user.getNickname());
        data.put("email",user.getEmail());
        data.put("realname",user.getRealname());
        data.put("id_number",user.getIdNumber());
        data.put("register_time",user.getRegisterTime());
        data.put("blocked",user.getBlocked());
        data.put("profile",user.getProfile());
        return data;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Map<String, Object> updateUserInfo(Integer userid, Identify info) {
        if (IDUtil.isIDNumber(info.getId_number())) {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getUid, userid);
            User user = this.baseMapper.selectOne(wrapper);
            user.setRealname(info.getRealname());
            user.setIdNumber(info.getId_number());
            saveOrUpdate(user);
            HashMap<String, Object> data = new HashMap<>();
            data.put("uid", user.getUid());
            data.put("username", user.getUsername());
            data.put("realname", user.getRealname());
            data.put("id_number", user.getIdNumber());
            data.put("blocked", user.getBlocked());
            return data;
        }else {
            return null;
        }
    }


}
