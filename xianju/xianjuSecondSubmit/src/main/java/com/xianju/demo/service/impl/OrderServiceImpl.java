package com.xianju.demo.service.impl;

import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xianju.demo.entity.Goods;
import com.xianju.demo.entity.Order;
import com.xianju.demo.entity.User;
import com.xianju.demo.entity.Usercomment;
import com.xianju.demo.mapper.OrderMapper;
import com.xianju.demo.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xianju.demo.vo.DifferentStateOrder;
import com.xianju.demo.vo.OrderGood;
import com.xianju.demo.vo.OrderState;
import com.xianju.demo.vo.UserCommentSelect;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Override
    @Transactional
    public Order updateState(int order_id) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getUid,order_id);
        Order order = this.baseMapper.selectOne(wrapper);
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        order.setState("02");
        order.setPayTime(dateString);
        saveOrUpdate(order);
        return order;
    }

    @Override
    @Transactional
    public List<DifferentStateOrder> getOrdersByState(String state, int userid) {
        MPJLambdaWrapper<Object> wrapper = new MPJLambdaWrapper<>()
                .selectAll(Order.class)
                .select(Goods::getGame,Goods::getTitle,Goods::getPic)
                .leftJoin(Goods.class, Goods::getSellerId, Order::getToId)
                .selectAs(User::getUsername,"sellername")
                .selectAs(User::getNickname,"sellernickname")
                .selectAs(User::getProfile,"sellerprofile")
                .leftJoin(User.class,User::getUid,Goods::getSellerId)
                .eq(Order::getState,state).eq(User::getUid,Goods::getSellerId)
                .eq(Order::getGoodId,Goods::getUid);
        return baseMapper.selectJoinList(DifferentStateOrder.class, wrapper);
    }

    @Override
    @Transactional
    public Order updateStates(OrderState orderState) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getUid,orderState.getUid());
        Order order = this.baseMapper.selectOne(wrapper);
        order.setState(orderState.getState());
        saveOrUpdate(order);
        return order;
    }
}
