package com.shianxian.trace.sys.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/17 14:07
 * @Description: 用户
 */
@Data
@Table(name = "sys_user")
@JsonIgnoreProperties("password")
public class User implements Serializable {

    private static final long serialVersionUID = -4091652444259368024L;

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空！")
    private String username;

    /**
     * 密码
     */

    @NotEmpty(message = "密码不能为空！")
    private String password;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 所属企业id
     */
    @NotNull(message = "所属企业不能为空！")
    private Integer companyId;

    /**
     * 是否为管理员  1：有效  0：无效  2：超级管理员（此账号为数据库手动所建）
     */
    private Integer isAdmin;

    /**
     * 是否有效  1：有效  0：无效。默认有效
     */
    private Integer enable = 1;

}
