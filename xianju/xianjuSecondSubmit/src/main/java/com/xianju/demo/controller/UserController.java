package com.xianju.demo.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.xianju.demo.BaseResponse.Result;
import com.xianju.demo.Util.JwtUtil;
import com.xianju.demo.entity.User;
import com.xianju.demo.entity.Wallet;
import com.xianju.demo.service.IUserService;
import com.xianju.demo.service.IUsercommentService;
import com.xianju.demo.service.IWalletService;
import com.xianju.demo.vo.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author feige
 * @since 2023-04-04
 */

@RestController
@Api(tags = "用户管理模块")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUsercommentService usercommentService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IWalletService walletService;


    @GetMapping("/user/all")//测试
    public Result<List<User>> getAllUser() {
        List<User> list = userService.list();
        return Result.success(list,"查询成功");
    }

    @PostMapping("/user/register")
    public Result<Map<String,Object>> addUser(@RequestBody UserVo userVo){
        Object sendCaptcha = redisTemplate.opsForValue().get(userVo.getEmail());
        if(Objects.nonNull(sendCaptcha) && sendCaptcha.equals(userVo.getCaptcha())){
            redisTemplate.delete(userVo.getEmail());
            User user = new User();
            user.setUsername(userVo.getUsername());
            user.setNickname(userVo.getNickname());
            user.setEmail(userVo.getEmail());
            user.setPassword(userVo.getPassword());
            user.setPassword(passwordEncoder.encode(userVo.getPassword()));

            Date date=new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(date);
            user.setRegisterTime(dateString);
            user.setBlocked(false);
            user.setUid((int) (Math.random()*100000000));
            user.setProfile("https://img9.doubanio.com/view/group_topic/l/public/p570571224.webp");
            userService.save(user);
            Wallet wallet = new Wallet();
            wallet.setUid((int) (Math.random()*100000000));
            wallet.setUserId(user.getUid());
            wallet.setBalance(BigDecimal.valueOf(0));
            wallet.setBlocked(false);
            walletService.save(wallet);
            Map<String, Object> data = new HashMap<>();
            data.put("uid",user.getUid());
            data.put("username",user.getUsername());
            data.put("register_time",date);
            return Result.success(data,"注册成功");
        } else {
            return Result.fail(400,"注册失败");
        }
    }

    @PostMapping("/user/login")
    public Result<Map<String,Object>> login(@RequestBody LoginUser user){
        Map<String, Object> data = userService.login(user);
        if(data!=null){
            return Result.success(data);
        }else {
            return Result.fail(400,"用户名或密码错误");
        }
    }

    @PutMapping("/user")
    public Result<Map<String,Object>> forgetpwd(@RequestBody ForgetUser forgetuser) {
        Object sendCaptcha = redisTemplate.opsForValue().get(forgetuser.getEmail());
        if (Objects.nonNull(sendCaptcha) && sendCaptcha.equals(forgetuser.getCaptcha())) {
            Map<String, Object> data = userService.forgetpwd(forgetuser);
            redisTemplate.delete(forgetuser.getEmail());
            if (data != null) {
                return Result.success(data);
            }return Result.fail(400, "验证码错误，修改失败");
        }return Result.fail(405,"请求错误");
    }

    @GetMapping("/user/{user_id}")
    public Result<?> userInfo(@PathVariable String user_id){
        Map<String,Object> data = userService.getUserInfo(user_id);
        if(data!=null){
            return Result.success(data);
        }return Result.fail(400,"查无此用户");
    }


    @PatchMapping("/user/updatenickname")
    public Result<?> updateNickname(@RequestHeader String token,@RequestBody String nickname){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                JwtUserInfo jwtUser = new JwtUserInfo();
                jwtUser.setBlocked(false);
                jwtUser.setUserid(keyWord.getClaim("userid").asInt());
                Map<String,Object> data = userService.updateUserNickname(nickname,jwtUser);
                return Result.success(data);
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }


    @PatchMapping("/user/updatepwd")
    public Result<?> updatePassword(@RequestHeader String token,@RequestBody String password){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                JwtUserInfo jwtUser = new JwtUserInfo();
                jwtUser.setBlocked(false);
                jwtUser.setUserid(keyWord.getClaim("userid").asInt());
                Map<String,Object> data = userService.updateUserpwd(password,jwtUser);
                return Result.success(data,"修改密码成功");
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/user/info")
    public Result<?> getUserInfo(@RequestHeader String token){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Map<String,Object> data = userService.getUserInformatiom(keyWord.getClaim("userid").asInt());
                return Result.success(data,"success");
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/user/comment")
    public Result<?> getUserComment(@RequestHeader String token){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                List<UserOwnComment> data = usercommentService.getCommentsInfo(keyWord.getClaim("userid").asInt());
                return Result.success(data,"success");
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }

    @PatchMapping("/user/identify")
    public Result<?> UserIdentify(@RequestHeader String token,@RequestBody Identify info){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Map<String, Object> data = userService.updateUserInfo(keyWord.getClaim("userid").asInt(),info);
                if(data!=null) {
                    return Result.success(data, "success");
                }return Result.fail(403,"不合法的身份证号");
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }
}
