package com.shianxian.trace.common.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/17 14:50
 * @Description: 基础javaBean
 */
@Data
public class BasePojo {

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateUser;

}
