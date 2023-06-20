package com.xianju.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xianju.demo.entity.Goods;
import com.xianju.demo.mapper.GoodsMapper;
import com.xianju.demo.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xianju.demo.vo.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author feige
 * @since 2023-04-04
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    @Transactional
    public Map<String, Object> getGoodInfo(String goods_id) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getUid,goods_id);
        Goods goods=this.baseMapper.selectOne(wrapper);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",goods.getUid());
        data.put("seller_id",goods.getSellerId());
        data.put("state",goods.getState());
        data.put("game",goods.getGame());
        data.put("title",goods.getTitle());
        data.put("detail",goods.getDetail());
        data.put("pic",goods.getPic());
        data.put("price",goods.getPrice());
        data.put("publish_time",goods.getPublish_time());
        return data;
    }

    @Override
    @Transactional
    public int getGoodId(int goods_id) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getUid,goods_id);
        Goods goods=this.baseMapper.selectOne(wrapper);
        return goods.getSellerId();
    }

    @Override
    @Transactional
    public Map<String, Object> removeByGoodID(int goods_id) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getUid,goods_id);
        Goods goods=this.baseMapper.selectOne(wrapper);
        this.baseMapper.delete(wrapper);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",goods.getUid());
        data.put("seller_id",goods.getSellerId());
        data.put("state",goods.getState());
        data.put("game",goods.getGame());
        data.put("title",goods.getTitle());
        data.put("detail",goods.getDetail());
        data.put("pic",goods.getPic());
        data.put("price",goods.getPrice());
        return data;
    }

    @Override
    @Transactional
    public Map<String, Object> postGood(int userid, GoodInfo goodInfo) {
        Goods goods = new Goods();
        goods.setUid((int) (Math.random()*100000000));
        goods.setSellerId(userid);
        goods.setGame(goodInfo.getGame());
        goods.setTitle(goodInfo.getTitle());
        goods.setDetail(goodInfo.getDetail());
        goods.setPic(goodInfo.getPic());
        goods.setPrice(goodInfo.getPrice());
        goods.setState("01");
        save(goods);
        Map<String, Object> data = new HashMap<>();
        data.put("uid",goods.getUid());
        data.put("seller_id",goods.getSellerId());
        data.put("state",goods.getState());
        data.put("game",goods.getGame());
        data.put("title",goods.getTitle());
        data.put("detail",goods.getDetail());
        data.put("pic",goods.getPic());
        data.put("price",goods.getPrice());
        return data;
    }

    @Override
    @Transactional
    public Map<String, Object> updateGoodInfo(int goods_id, UpdateGoodInfo updateGoodInfo) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getUid,goods_id);
        Goods goods=this.baseMapper.selectOne(wrapper);
        goods.setGame(updateGoodInfo.getGame());
        goods.setTitle(updateGoodInfo.getTitle());
        goods.setDetail(updateGoodInfo.getDetail());
        goods.setPic(updateGoodInfo.getPic());
        goods.setPrice(updateGoodInfo.getPrice());
        goods.setState("01");
        saveOrUpdate(goods);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",goods.getUid());
        data.put("seller_id",goods.getSellerId());
        data.put("state",goods.getState());
        data.put("game",goods.getGame());
        data.put("title",goods.getTitle());
        data.put("detail",goods.getDetail());
        data.put("pic",goods.getPic());
        data.put("price",goods.getPrice());
        return data;
    }

    @Override
    @Transactional
    public Map<String, Object> updatePice(UpdatePrice updatePrice) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getUid,updatePrice.getUid());
        Goods goods=this.baseMapper.selectOne(wrapper);
        goods.setPrice(updatePrice.getPrice());
        saveOrUpdate(goods);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",goods.getUid());
        data.put("seller_id",goods.getSellerId());
        data.put("game",goods.getGame());
        data.put("title",goods.getTitle());
        data.put("price",goods.getPrice());
        return data;
    }

    @Override
    @Transactional
    public List<Goods> getlist() {
        MPJLambdaWrapper<Goods> wrapper = new MPJLambdaWrapper<Goods>()
                .selectAll(Goods.class)//查询UserComment表全部字段
                .eq(Goods::getState,"02");
        List<Goods> list = this.baseMapper.selectList(wrapper);
        return list;
    }

    @Override
    @Transactional
    public Goods getGood(int good_id) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getUid,good_id);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    @Transactional
    public List<Goods> getGoodsByKeyword(String keyword) {
        MPJLambdaWrapper<Goods> wrapper = new MPJLambdaWrapper<Goods>()
                .selectAll(Goods.class)
                .like(Goods::getGame,keyword)
                .or().like(Goods::getDetail,keyword)
                .or().like(Goods::getTitle,keyword);
        return baseMapper.selectList(wrapper);
    }


}
