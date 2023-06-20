package com.xianju.demo.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xianju.demo.BaseResponse.Result;
import com.xianju.demo.Util.JwtUtil;
import com.xianju.demo.entity.Goods;
import com.xianju.demo.entity.Order;
import com.xianju.demo.service.IGoodsService;
import com.xianju.demo.service.IOrderService;
import com.xianju.demo.service.IWalletService;
import com.xianju.demo.vo.DifferentStateOrder;
import com.xianju.demo.vo.JwtUserInfo;
import com.xianju.demo.vo.OrderGood;
import com.xianju.demo.vo.OrderState;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author feige
 * @since 2023-04-04
 */
@RestController
@Api(tags = "订单管理模块")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IWalletService walletService;


    @PostMapping("/order")
    public Result<?> postOrder(@RequestHeader String token, @RequestBody OrderGood orderGood){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Goods goods = goodsService.getGood(orderGood.getGood_id());
                goods.setState("03");
                goodsService.saveOrUpdate(goods);
                int userid = keyWord.getClaim("userid").asInt();
                Order order = new Order();
                order.setUid((int) (Math.random()*100000000));
                order.setFromId(userid);
                order.setToId(goods.getSellerId());
                order.setGoodId(goods.getUid());
                order.setState("01");
                order.setPrice(orderGood.getPrice());
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(date);
                order.setCreateTime(dateString);
                orderService.save(order);
                HashMap<String, Object> data = new HashMap<>();
                data.put("uid",order.getUid());
                data.put("from_id",order.getFromId());
                data.put("to_id",order.getToId());
                data.put("good_id",order.getGoodId());
                data.put("state",order.getState());
                data.put("price",order.getPrice());
                data.put("create_time",order.getCreateTime());
                data.put("pay_time",order.getPayTime());
                return Result.success(data,"订单已生成");
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/order/pay/{order_id}")
    public Result<?> payOrder(@PathVariable int order_id){
       Order order = orderService.updateState(order_id);
       HashMap<String, Object> data = new HashMap<>();
       data.put("uid",order.getUid());
       data.put("from_id",order.getFromId());
       data.put("to_id",order.getToId());
       data.put("good_id",order.getGoodId());
       data.put("state",order.getState());
       data.put("price",order.getPrice());
       data.put("create_time",order.getCreateTime());
       data.put("pay_time",order.getPayTime());
       BigDecimal cost = order.getPrice();
       walletService.updateBalance(cost,order.getFromId());
       return Result.success(data,"支付成功");
    }


    @GetMapping("/order/{state}")
    public Result<?> getOrderByState(@RequestHeader String token,@PathVariable String state){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                List<DifferentStateOrder> list = orderService.getOrdersByState(state,keyWord.getClaim("userid").asInt());
                if(list!=null) {
                    return Result.success(list, "查询成功");
                }return Result.success("无相关数据");
            }return Result.fail(402,"账号被封锁无法查询订单情况");
        }return Result.fail(401,"未接收到token");
    }

    @PostMapping("/order/updateState")
    public Result<?> updateState(@RequestBody OrderState orderState){
        HashMap<String, Object> data = new HashMap<>();
        Order order = orderService.updateStates(orderState);
        if(orderState.getState().equals("06")){
            walletService.updateMoney(order.getPrice(),order.getToId());
        }
        data.put("uid",order.getUid());
        data.put("state",order.getState());
        return Result.success(data);
    }

}
