package com.deadelo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deadelo.constants.SystemConstants;
import com.deadelo.domain.entity.Link;
import com.deadelo.mapper.LinkMapper;
import com.deadelo.service.LinkService;
import com.deadelo.utils.BeanCopyUtils;
import com.deadelo.vo.LinkListVo;
import com.deadelo.vo.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2022-07-19 19:51:25
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过

        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Link::getStatus,SystemConstants.LINK_STATUS_NORMAL);
        List<Link> linkList = list(queryWrapper);
        //转换为Vo
        List<LinkListVo> LinkListVos = BeanCopyUtils.copyBeanList(linkList, LinkListVo.class);
        //封装返回
        return ResponseResult.okResult(LinkListVos);
    }
}

