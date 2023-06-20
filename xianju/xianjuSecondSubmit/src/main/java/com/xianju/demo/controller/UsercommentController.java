package com.xianju.demo.controller;

import com.xianju.demo.BaseResponse.Result;
import com.xianju.demo.entity.Usercomment;
import com.xianju.demo.service.IUsercommentService;
import com.xianju.demo.vo.UpdateComment;
import com.xianju.demo.vo.UserCommentSelect;
import com.xianju.demo.vo.UserCommentVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author feige
 * @since 2023-04-04
 */
@RestController
@Api(tags = "评价管理模块")
public class UsercommentController {

    @Autowired
    private IUsercommentService usercommentService;

    @GetMapping("/comments/{to_id}")
    public Result<List<UserCommentSelect>> getCommentList(@PathVariable int to_id){
        List<UserCommentSelect> list = usercommentService.getlistById(to_id);
        return Result.success(list,"查询成功");
    }

    //暂时无用
    @GetMapping("/comments/detail/{uid}")
    public Result<?> getCommentDetail(@PathVariable int uid){
        Map<String,Object> data = usercommentService.getCommentInfo(uid);
        if(data!=null){
            return Result.success(data);
        }return Result.fail(400,"查无此用户");

    }


    @PostMapping("/comments")
    public Result<?> postComments(@RequestBody UserCommentVo usercommentVo){
        Usercomment usercomment = new Usercomment();
        usercomment.setUid((int) (Math.random()*100000000));
        usercomment.setFromId(usercommentVo.getFrom_id());
        usercomment.setToId(usercommentVo.getTo_id());
        usercomment.setGoodId(usercommentVo.getGood_id());
        usercomment.setDetail(usercommentVo.getDetail());
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        usercomment.setTime(dateString);
        usercomment.setStar(usercommentVo.getStar());
        usercomment.setModified(false);
        usercomment.setModifiedTime(usercommentVo.getModified_time());
        usercomment.setCommentPic(usercommentVo.getComment_pic());
        usercommentService.save(usercomment);
        Map<String,Object> data = new HashMap<>();
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
        return Result.success(data,"评价信息发布成功");
    }


    @PutMapping("/comments/{comment_id}")
    public Result<?> updateComment(@PathVariable int comment_id,@RequestBody UpdateComment updateComment){
        Map<String,Object> data = usercommentService.updatecomment(comment_id,updateComment);
        if(data!=null) {
            return Result.success(data, "修改成功");
        }return Result.fail(402,"已修改过评价，无法修改");
    }


}
