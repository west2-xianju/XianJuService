package com.xianju.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.base.MPJBaseMapper;
import com.xianju.demo.entity.Goods;
import com.xianju.demo.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author feige
 * @since 2023-04-04
 */
@Mapper
@Service
public interface OrderMapper extends MPJBaseMapper<Order> {

}
