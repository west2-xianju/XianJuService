package com.xianju.test;

import com.xianju.demo.Util.SnowFlakeID;

public class SnowFlakeIDtest {
    /**
     * <h2>测试类</h2>
     */
    public static void main(String[] args) {
        SnowFlakeID worker1 = new SnowFlakeID(1,1);
        SnowFlakeID worker2 = new SnowFlakeID(2,1);
        SnowFlakeID worker3 = new SnowFlakeID(3,1);
        for (int i = 0; i < 30; i++){
            System.out.println("数据中心1，雪花算法 ID：" + worker1.nextId());
            System.out.println("数据中心2，雪花算法 ID：" + worker2.nextId());
            System.out.println("数据中心3，雪花算法 ID：" + worker3.nextId());
        }
    }
}
