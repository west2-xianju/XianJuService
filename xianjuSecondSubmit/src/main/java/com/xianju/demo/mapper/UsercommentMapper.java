package com.xianju.demo.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.xianju.demo.entity.Usercomment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.AllArgsConstructor;
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
public interface UsercommentMapper extends MPJBaseMapper<Usercomment> {
}
