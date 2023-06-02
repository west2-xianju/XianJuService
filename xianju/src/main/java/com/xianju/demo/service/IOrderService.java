package com.xianju.demo.service;

import com.auth0.jwt.interfaces.Claim;
import com.xianju.demo.entity.Goods;
import com.xianju.demo.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xianju.demo.vo.DifferentStateOrder;
import com.xianju.demo.vo.OrderGood;
import com.xianju.demo.vo.OrderState;

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
public interface IOrderService extends IService<Order> {

    Order updateState(int order_id);

    List<DifferentStateOrder> getOrdersByState(String state, int userid);

    Order updateStates(OrderState orderState);

    //Map<String, Object> postOrder(Integer userid, Goods goods);
}
