package com.deadelo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deadelo.constants.SystemConstants;
import com.deadelo.domain.Enum.AppHttpCodeEnum;
import com.deadelo.domain.entity.Comment;
import com.deadelo.exception.SystemException;
import com.deadelo.mapper.CommentMapper;
import com.deadelo.service.CommentService;
import com.deadelo.service.UserService;
import com.deadelo.utils.BeanCopyUtils;
import com.deadelo.vo.CommentVo;
import com.deadelo.vo.PageVo;
import com.deadelo.vo.ResponseResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-07-24 21:28:49
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    UserService userService;


    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对于文章的根评论


        //对articleId判断
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(SystemConstants.ARTICLE_COMMMENT.equals(commentType),Comment::getArticleId, articleId);
        //根评论id为-1
        queryWrapper.eq(Comment::getRootId, SystemConstants.ROOT_COMMENT_ID);
        //评论类型
        queryWrapper.eq(Comment::getType, commentType);
        //分页查询
        Page<Comment> commentPage = new Page(pageNum,pageSize);
        page(commentPage,queryWrapper);


        List<CommentVo> commentVoList = toCommentVoList(commentPage.getRecords());

        //查询所有根评论的子评论集合，并赋值相应的属性
        for (CommentVo commentVo : commentVoList) {
            //查询对应子评论
            commentVo.setChildren(getChildren(commentVo.getId()));
            //赋值
        }
        return ResponseResult.okResult(new PageVo(commentVoList,commentPage.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.COMMENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();

    }

    /**
     * 根据根评论id查询子评论集合
     * @param id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);

        List<Comment> childrenCommentList = list(queryWrapper);
        return toCommentVoList(childrenCommentList);
    }

    private List<CommentVo> toCommentVoList (List<Comment> list){
        List<CommentVo> commentVoList = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //遍历Vo集合
        //commentVoList.stream().map(commentVo -> userService.getById(commentVo.getCreateBy()).getNickName());
        //通过CreateBy查询用户昵称并赋值
        //通过toCommentUserId查询用户昵称并赋值
        for (CommentVo commentVo : commentVoList) {
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            if (commentVo.getToCommentId()!=-1L){
                commentVo.setToCommentUsername(userService.getById(commentVo.getToCommentUserId()).getNickName());
            }
        }

        return commentVoList;
    }
}

