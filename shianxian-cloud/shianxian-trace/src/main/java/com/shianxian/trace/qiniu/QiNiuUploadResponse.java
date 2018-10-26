package com.shianxian.trace.qiniu;

import lombok.Data;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/25 15:36
 * @Description: 七牛图片删除响应消息
 */
@Data
public class QiNiuUploadResponse {

    /**
     * 七牛上传返回成功的信息如下
     * {"hash":"FrQF5eX_kNsNKwgGNeJ4TbBA0Xzr","key":"aa1.jpg"}
     */
    private String hash;

    private String key;
}
