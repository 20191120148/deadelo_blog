package com.deadelo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deadelo.domain.entity.Tag;
import com.deadelo.mapper.TagMapper;
import com.deadelo.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2022-08-10 17:59:49
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}

