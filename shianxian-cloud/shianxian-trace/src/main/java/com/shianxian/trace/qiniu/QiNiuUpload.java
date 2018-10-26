package com.shianxian.trace.qiniu;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.shianxian.common.utils.DateUtils;
import com.shianxian.common.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;


/**
 * 七牛云上传图片
 */
@Slf4j
public class QiNiuUpload {

    /**
     * 账号的ACCESS_KEY和SECRET_KEY
     */
    private static final String ACCESS_KEY = "1XwHZOxhCF9gaBz9wbrqPQJN542_lih40LUQUfk0";

    private static final String SECRET_KEY = "u-bBWNXpcwRWNgUdbJEB_f48OVPrcjg1PJfhtFR_";

    /**
     * 要上传的空间
     */
    private static final String bucketname = "saxupdata";

    /**
     * 密钥配置
     */
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);


    /**
     * 简单上传，使用默认策略，只需要设置上传的空间名就可以了
     *
     * @return
     */
    public static String getUpToken() {
        return auth.uploadToken(bucketname);
    }


    /**
     * 指定保存到七牛的文件名--同名上传会报错  {"error":"file exists"}
     * 正常返回:{"hash":"FrQF5eX_kNsNKwgGNeJ4TbBA0Xzr","key":"aa1.jpg"}
     * key为七牛空间地址 http:/xxxx.com/aa1.jpg
     *
     * 七牛访问域名对应关系 ：http://image1.shianxian.cn  对应上传空间为saxupdata  用户上传图片使用
     *                     http://imageqn.shianxian.cn 对应上传空间为ssax   页面图片存储使用
     * @param file
     * @param suffix
     * @return fileName 图片名称
     */
    public static String upload(File file, String suffix) {
        try {
            // 获取文件名称
            String timestamp = DateUtils.getTimestamp();
            String md5 = MD5Utils.Md5(DateUtils.getTimestamp());
            // 上传对象
            UploadManager uploadManager = new UploadManager(new Configuration());
            // 调用put方法上传
            Response res = uploadManager.put(file, timestamp + md5 + suffix, getUpToken());
            // 打印返回的信息
            QiNiuUploadResponse qiNiuUploadResponse = JSON.parseObject(res.bodyString(), QiNiuUploadResponse.class);
            return qiNiuUploadResponse.getKey();
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            log.error(r.toString(), e);
            try {
                //响应的文本信息
                log.error(r.bodyString(), e);
            } catch (QiniuException e1) {
                log.error("上传图片错误", e1);
                return null;
            }
        } catch (Exception e) {
            log.error("上传图片错误，未知异常", e);
            return null;
        }
        return null;
    }


    /**
     * 删除图片
     * @param key
     * @return
     */
    public static void delete(String key) {
        try {
            Configuration config = new Configuration(Zone.autoZone());
            BucketManager bucketMgr = new BucketManager(auth, config);
            Response response = bucketMgr.delete(bucketname, key);
            String s = response.bodyString();
            System.out.println(s);
        } catch (QiniuException e) {
            log.error("七牛删除图片错误", e);
        }
    }

}
