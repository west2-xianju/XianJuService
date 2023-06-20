package com.xianju.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xianju.demo.BaseResponse.Result;
import com.xianju.demo.Util.JwtUtil;
import com.xianju.demo.entity.Bid;
import com.xianju.demo.service.IBidService;
import com.xianju.demo.service.IUserService;
import com.xianju.demo.vo.BidPrice;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author feige
 * @since 2023-04-04
 */
@RestController
@Api(tags = "报价管理模块")
public class BidController {

    @Autowired
    private IBidService bidService;

    @Autowired
    private IUserService userService;

    @PostMapping("/bid")
    public Result<?> postBid(@RequestHeader String token, @RequestBody BidPrice bidPrice){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Map<String,Object> data = bidService.postBid(keyWord.getClaim("userid").asInt(),bidPrice);
                return Result.success(data,"报价发送成功");
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }

    @PatchMapping("/bid/{bid_id}")
    public Result<?> patchBid(@PathVariable int bid_id, @RequestBody String price) {
        JSONObject jsonObject = JSON.parseObject(price);
        BigDecimal ToPrice = jsonObject.getBigDecimal("price");
        Map<String, Object> data = bidService.patchBid(bid_id, ToPrice);
        if (data != null) {
            return Result.success(data, "修改报价成功");
        }return Result.fail(400,"修改失败");
    }

    @DeleteMapping("/bid/{bid_id}")
    public Result<?> putBid(@PathVariable int bid_id){
        Map<String,Object> data = bidService.removeByGoodID(bid_id);
        if(data!=null){
            return Result.success(data,"删除成功");
        }return Result.fail(400,"查无此货物");
    }

    @GetMapping("/bid/{good_id}")
    public Result<?> getBidList(@PathVariable int good_id) {
        List<Bid> list = bidService.getListByGoodId(good_id);
        if (list != null) {
            return Result.success(list,"查询成功");
        }return Result.fail(400,"未查到该商品有报价");
    }
}
