package com.xianju.demo.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xianju.demo.BaseResponse.Result;
import com.xianju.demo.Util.JwtUtil;
import com.xianju.demo.entity.Goods;
import com.xianju.demo.entity.User;
import com.xianju.demo.entity.Usercomment;
import com.xianju.demo.mapper.UserMapper;
import com.xianju.demo.service.IGoodsService;
import com.xianju.demo.service.IUserService;
import com.xianju.demo.vo.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.UnsupportedEncodingException;
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
@Api(tags = "货物管理模块")
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IUserService userService;

    @GetMapping("/goods")
    public Result<List<Goods>> allGoods(){
        List<Goods> list = goodsService.getlist();
        return Result.success(list,"查询成功");
    }

    @GetMapping("/goods/{goods_id}")
    public Result<?> goodInfo(@PathVariable int goods_id){
        Map<String,Object> data = goodsService.getGoodInfo(String.valueOf(goods_id));
        int seller_id = goodsService.getGoodId(goods_id);
        Map<String,Object> data2 = userService.getUserPUN(seller_id);
        HashMap<String, Object> dataAll = new HashMap<>();
        dataAll.putAll(data);
        dataAll.putAll(data2);
        if(data!=null){
            return Result.success(dataAll);
        }return Result.fail(400,"查无此货物");
    }

    @DeleteMapping("/goods/{goods_id}")
    public Result<?> deleteGood(@PathVariable int goods_id){
        Map<String,Object> data = goodsService.removeByGoodID(goods_id);
        if(data!=null){
            return Result.success(data,"删除成功");
        }return Result.fail(400,"查无此货物");
    }

    @PostMapping("/goods")
    public Result<?> postGood(@RequestHeader String token,@RequestBody GoodInfo goodInfo){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Map<String,Object> data = goodsService.postGood(keyWord.getClaim("userid").asInt(),goodInfo);
                return Result.success(data,"发布成功，等待管理员审核");
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }

    @PutMapping("/goods/{goods_id}")
    public Result<?> updateGoodInfo(@PathVariable int goods_id,@RequestBody UpdateGoodInfo updateGoodInfo){
        Map<String,Object> data = goodsService.updateGoodInfo(goods_id,updateGoodInfo);
        if (data != null) {
            return Result.success(data,"修改发布成功，等待管理员审核");
        }
        return Result.fail(400,"发布失败");
    }

    @PatchMapping("/goods")
    public Result<?> updatePrice(@RequestBody UpdatePrice updatePrice){
        Map<String,Object> data = goodsService.updatePice(updatePrice);
        if (data != null) {
            return Result.success(data,"修改价格成功");
        }
        return Result.fail(400,"修改价格失败");
    }

    @GetMapping("/goods/state/{state}")
    public Result<?> myPublishedGoods(@RequestHeader String token,@PathVariable String state) {
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                MPJLambdaWrapper<Goods> wrapper = new MPJLambdaWrapper<Goods>()
                        .selectAll(Goods.class)
                        .eq(Goods::getSellerId,keyWord.getClaim("userid").asInt())
                        .eq(Goods::getState,state);
                List<Goods> list = goodsService.list(wrapper);
                return Result.success(list,"查询成功");
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");

    }


}
