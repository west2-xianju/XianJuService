package com.xianju.demo.service;

import com.auth0.jwt.interfaces.Claim;
import com.xianju.demo.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xianju.demo.vo.GoodInfo;
import com.xianju.demo.vo.GoodsVo;
import com.xianju.demo.vo.UpdateGoodInfo;
import com.xianju.demo.vo.UpdatePrice;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author feige
 * @since 2023-04-04
 */
public interface IGoodsService extends IService<Goods> {

    Map<String, Object> getGoodInfo(String goods_id);

    int getGoodId(int goods_id);

    Map<String, Object> removeByGoodID(int goods_id);

    Map<String, Object> postGood(int userid, GoodInfo goodInfo);

    Map<String, Object> updateGoodInfo(int goods_id, UpdateGoodInfo updateGoodInfo);

    Map<String, Object> updatePice(UpdatePrice updatePrice);

    List<Goods> getlist();

    Goods getGood(int good_id);

    List<Goods> getGoodsByKeyword(String keyword);
}
