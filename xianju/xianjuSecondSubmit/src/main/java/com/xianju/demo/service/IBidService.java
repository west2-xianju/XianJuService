package com.xianju.demo.service;

import com.xianju.demo.entity.Bid;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xianju.demo.vo.BidPrice;

import java.math.BigDecimal;
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
public interface IBidService extends IService<Bid> {

    Map<String, Object> postBid(Integer userid, BidPrice bidPrice);

    Map<String, Object> patchBid(int bid_id, BigDecimal toPrice);

    Map<String, Object> removeByGoodID(int bid_id);

    List<Bid> getListByGoodId(int goodId);
}
