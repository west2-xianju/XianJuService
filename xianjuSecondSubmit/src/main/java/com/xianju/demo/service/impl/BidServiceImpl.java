package com.xianju.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xianju.demo.entity.Bid;
import com.xianju.demo.entity.Goods;
import com.xianju.demo.mapper.BidMapper;
import com.xianju.demo.service.IBidService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xianju.demo.vo.BidPrice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author feige
 * @since 2023-04-04
 */

@Service
public class BidServiceImpl extends ServiceImpl<BidMapper, Bid> implements IBidService {

    @Override
    @Transactional
    public Map<String, Object> postBid(Integer userid, BidPrice bidPrice) {
        Bid bid = new Bid();
        bid.setUid((int) (Math.random()*100000000));
        bid.setFromUser(userid);
        bid.setGoodId(bidPrice.getGood_id());
        bid.setPrice(bidPrice.getPrice());
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        bid.setTime(dateString);
        bid.setDeleted(false);
        bid.setRejected(false);
        save(bid);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",bid.getUid());
        data.put("from_user",bid.getFromUser());
        data.put("good_id",bid.getGoodId());
        data.put("price",bid.getPrice());
        data.put("time",bid.getTime());
        data.put("deleted",bid.getDeleted());
        data.put("rejected",bid.getRejected());
        return data;
    }

    @Override
    @Transactional
    public Map<String, Object> patchBid(int bid_id, BigDecimal toPrice) {
        LambdaQueryWrapper<Bid> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bid::getUid,bid_id);
        Bid bid = this.baseMapper.selectOne(wrapper);
        bid.setPrice(toPrice);
        saveOrUpdate(bid);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",bid.getUid());
        data.put("from_user",bid.getFromUser());
        data.put("good_id",bid.getGoodId());
        data.put("price",bid.getPrice());
        data.put("time",bid.getTime());
        data.put("deleted",bid.getDeleted());
        data.put("rejected",bid.getRejected());
        return data;
    }

    @Override
    @Transactional
    public Map<String, Object> removeByGoodID(int bid_id) {
        LambdaQueryWrapper<Bid> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bid::getUid,bid_id);
        Bid bid=this.baseMapper.selectOne(wrapper);
        this.baseMapper.delete(wrapper);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",bid.getUid());
        data.put("from_user",bid.getFromUser());
        data.put("good_id",bid.getGoodId());
        data.put("price",bid.getPrice());
        data.put("time",bid.getTime());
        data.put("deleted",bid.getDeleted());
        data.put("rejected",bid.getRejected());
        return data;
    }

    @Override
    @Transactional
    public List<Bid> getListByGoodId(int goodId) {
        LambdaQueryWrapper<Bid> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bid::getGoodId,goodId);
        List<Bid> list=this.baseMapper.selectList(wrapper);
        return list;
    }
}
