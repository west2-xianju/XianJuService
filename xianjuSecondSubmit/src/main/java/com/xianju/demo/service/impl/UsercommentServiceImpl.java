package com.xianju.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.xianju.demo.entity.User;
import com.xianju.demo.entity.Usercomment;
import com.xianju.demo.mapper.UsercommentMapper;
import com.xianju.demo.service.IUsercommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xianju.demo.vo.Identify;
import com.xianju.demo.vo.UpdateComment;
import com.xianju.demo.vo.UserCommentSelect;
import com.xianju.demo.vo.UserOwnComment;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UsercommentServiceImpl extends ServiceImpl<UsercommentMapper, Usercomment> implements IUsercommentService {


    @Override
    @Transactional
    public List<UserCommentSelect> getlistById(int to_id) {
        //和Mybatis plus一致，MPJLambdaWrapper的泛型必须是主表的泛型，并且要用主表的Mapper来调用
        MPJLambdaWrapper<Usercomment> wrapper = new MPJLambdaWrapper<Usercomment>()
                .selectAll(Usercomment.class)//查询UserComment表全部字段
                .select(User::getProfile,User::getUsername,User::getNickname)//查询User profile,username,nickname 字段
                .leftJoin(User.class, User::getUid, Usercomment::getFromId)
                .eq(Usercomment::getToId,to_id);
        return baseMapper.selectJoinList(UserCommentSelect.class, wrapper);
    }

    @Override
    @Transactional
    public Map<String, Object> getCommentInfo(int uid) {
        LambdaQueryWrapper<Usercomment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Usercomment::getUid,uid);
        Usercomment usercomment = this.baseMapper.selectOne(wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("uid",usercomment.getUid());
        data.put("from_id",usercomment.getFromId());
        data.put("to_id",usercomment.getToId());
        data.put("good_id",usercomment.getGoodId());
        data.put("detail",usercomment.getDetail());
        data.put("time",usercomment.getTime());
        data.put("star",usercomment.getStar());
        data.put("modified",usercomment.isModified());
        data.put("modified_time",usercomment.getModifiedTime());
        data.put("comment_pic",usercomment.getCommentPic());
        return data;
    }

    @Override
    @Transactional
    public Map<String, Object> updatecomment(int comment_id, UpdateComment updateComment) {
        LambdaQueryWrapper<Usercomment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Usercomment::getUid,comment_id);
        Usercomment usercomment=this.baseMapper.selectOne(wrapper);
        if(!usercomment.isModified()) {
            usercomment.setDetail(updateComment.getDetail());
            usercomment.setStar(updateComment.getStar());
            usercomment.setCommentPic(updateComment.getComment_pic());
            saveOrUpdate(usercomment);
            Map<String, Object> data = new HashMap<>();
            data.put("uid", usercomment.getUid());
            data.put("from_id", usercomment.getFromId());
            data.put("to_id", usercomment.getToId());
            data.put("good_id", usercomment.getGoodId());
            data.put("detail", usercomment.getDetail());
            data.put("time", usercomment.getTime());
            data.put("star", usercomment.getStar());
            data.put("modified", usercomment.isModified());
            data.put("modified_time", usercomment.getModifiedTime());
            data.put("comment_pic", usercomment.getCommentPic());
            return data;
        }
       return null;
    }

    @Override
    @Transactional
    public List<UserOwnComment> getCommentsInfo(Integer userid) {
        //和Mybatis plus一致，MPJLambdaWrapper的泛型必须是主表的泛型，并且要用主表的Mapper来调用
        MPJLambdaWrapper<Usercomment> wrapper = new MPJLambdaWrapper<Usercomment>()
                .selectAll(Usercomment.class)//查询UserComment表全部字段
                .eq(Usercomment::getFromId,userid);
        return baseMapper.selectJoinList(UserOwnComment.class, wrapper);
    }



}
