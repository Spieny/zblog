package com.ziahh.zblog.service.impl;

import com.ziahh.zblog.pojo.Article;
import com.ziahh.zblog.mapper.ArticleMapper;
import com.ziahh.zblog.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章表，用于存储发布的文章 服务实现类
 * </p>
 *
 * @author Ziahh
 * @since 2024-09-19
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
