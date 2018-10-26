package com.shianxian.trace.sys.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/19 11:11
 * @Description: 权限
 */
@Table(name = "sys_permission")
@Data
public class Permission {


    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限代码
     */
    private String permissionCode;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 类型
     */
    private String type;

    /**
     * url
     */
    private String url;

    /**
     * 权限描述
     */
    private String description;

}
