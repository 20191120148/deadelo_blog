package com.deadelo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deadelo.domain.entity.Comment;
import com.deadelo.vo.ResponseResult;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-07-24 21:28:48
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);

}
