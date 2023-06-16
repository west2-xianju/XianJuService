package com.xianju.demo.service;

import com.xianju.demo.entity.User;
import com.xianju.demo.entity.Usercomment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xianju.demo.vo.Identify;
import com.xianju.demo.vo.UpdateComment;
import com.xianju.demo.vo.UserCommentSelect;
import com.xianju.demo.vo.UserOwnComment;

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
public interface IUsercommentService extends IService<Usercomment> {

    List<UserCommentSelect> getlistById(int to_id);

    Map<String, Object> getCommentInfo(int uid);

    Map<String, Object> updatecomment(int comment_id, UpdateComment updateComment);

    List<UserOwnComment> getCommentsInfo(Integer userid);

}
