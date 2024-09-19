package com.ziahh.zblog.service.impl;

import com.ziahh.zblog.pojo.Category;
import com.ziahh.zblog.mapper.CategoryMapper;
import com.ziahh.zblog.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章分类表 服务实现类
 * </p>
 *
 * @author Ziahh
 * @since 2024-09-19
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
