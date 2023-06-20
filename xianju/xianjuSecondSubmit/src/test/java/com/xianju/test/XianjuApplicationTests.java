package com.xianju.test;

import com.xianju.demo.entity.User;
import com.xianju.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class XianjuApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void conMapper() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
