package com.xianju.demo.controller;


import com.xianju.demo.BaseResponse.Result;
import com.xianju.demo.entity.Goods;
import com.xianju.demo.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private IGoodsService goodsService;

    @GetMapping("/goods")
    public Result<?> searchGoods(String keyword) {
        List<Goods> list = goodsService.getGoodsByKeyword(keyword);
        if (list != null) {
            return Result.success(list, "查询成功");
        }return Result.fail(400,"未查到相关货物");
    }

}
