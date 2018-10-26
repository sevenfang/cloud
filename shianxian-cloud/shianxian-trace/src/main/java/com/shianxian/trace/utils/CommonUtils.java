package com.shianxian.trace.utils;


import com.shianxian.trace.common.pojo.BasePojo;
import com.shianxian.trace.sys.pojo.User;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/25 14:13
 * @Description: 通用工具类
 */
public class CommonUtils {


    /**
     * 设置创建人、修改时间、修改人、修改时间
     * @param basePojo
     * @param user
     */
    public static void setUserAndTime(BasePojo basePojo, User user) {
        basePojo.setCreateTime(new Date());
        basePojo.setCreateUser(user.getUsername());
        basePojo.setUpdateTime(new Date());
        basePojo.setUpdateUser(user.getUsername());
    }


    /**
     * 设置修改人跟修改时间
     * @param basePojo
     * @param user
     */
    public static void setUpdateUserAndUpdateTime(BasePojo basePojo, User user) {
        basePojo.setUpdateTime(new Date());
        basePojo.setUpdateUser(user.getUsername());
    }


    /**
     * 获取文件后缀
     * @param fileName 文件
     * @return
     */
    public static String getFileSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."),fileName.length());
    }


    /**
     * 获取订单号
     * 规则：年月日时分秒 + 4位随机数
     * @return
     */
    public static String getOrderNo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat format = new SimpleDateFormat("mmss");
        Date date = new Date();
        Integer dateNum = Integer.valueOf(format.format(date)) * 4 / 3;
        return dateFormat.format(date) + dateNum;
    }

}
