package com.shianxian.trace.common.pojo;

import lombok.Data;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/20 14:52
 * @Description: 分页
 */
@Data
public class Page {

    /**
     * 第几页
     */
    private Integer pageNum = 1;

    /**
     * 每页查询数量
     */
    private Integer pageSize = 10;
}
