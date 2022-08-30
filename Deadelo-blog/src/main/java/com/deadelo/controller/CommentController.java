package com.deadelo.controller;/*
 *@program:Deadelo
 *@author: Deadelo
 *@Time: 2022/7/24  21:44
 */

import com.deadelo.constants.SystemConstants;
import com.deadelo.domain.dto.AddCommentDto;
import com.deadelo.domain.entity.Comment;
import com.deadelo.service.CommentService;
import com.deadelo.utils.BeanCopyUtils;
import com.deadelo.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论",description = "评论相关接口")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMMENT,articleId,pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody AddCommentDto comment){

        return commentService.addComment(BeanCopyUtils.copyBean(comment,Comment.class));
    }


    @GetMapping("/linkCommentList")
    @ApiOperation(value = "友链评论列表",notes = "获取一页友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    })
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMMENT, null,pageNum,pageSize);
    }
}
