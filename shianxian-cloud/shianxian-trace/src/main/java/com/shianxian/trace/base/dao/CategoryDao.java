package com.shianxian.trace.base.dao;

import com.shianxian.trace.base.pojo.Category;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/25 10:35
 * @Description: 物料类别持久层接口
 */
@Repository
public interface CategoryDao extends Mapper<Category> {
}