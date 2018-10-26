package com.shianxian.trace.config.shiro;

import com.shianxian.trace.sys.pojo.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 加密用户密码
 */
public class PasswordHelper {


    private String algorithmName = "md5";

    private int hashIterations = 2;

    public void encryptPassword(User user) {

        //String salt=randomNumberGenerator.nextBytes().toHex();

        String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user.getUsername()), hashIterations).toHex();
        //String newPassword = new SimpleHash(algorithmName, user.getPassword()).toHex();
        user.setPassword(newPassword);

    }


}