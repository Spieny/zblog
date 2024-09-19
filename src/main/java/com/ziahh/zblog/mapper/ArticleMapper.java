package com.ziahh.zblog.mapper;

import com.ziahh.zblog.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章表，用于存储发布的文章 Mapper 接口
 * </p>
 *
 * @author Ziahh
 * @since 2024-09-19
 */
public interface ArticleMapper extends BaseMapper<Article> {

}
