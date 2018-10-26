package com.shianxian.trace.common.controller;

import com.shianxian.common.utils.ResultUtils;
import com.shianxian.trace.qiniu.QiNiuUpload;
import com.shianxian.trace.utils.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


/**
 * @Auther: 赵明明
 * @Date: 2018/9/25 16:11
 * @Description: 通用上传类
 */
@RestController
@RequestMapping("upload")
@Slf4j
@Api(description = "通用上传控制器")
public class UploadController {


    /**
     * 上传图片
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("uploadImages")
    @ApiOperation(value="通用上传接口", notes="上传到七牛云，请到七牛云查看！上传到的七牛空间名称：saxupdata")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="body", name = "multipartFile", value = "文件", required = true),
            @ApiImplicitParam(paramType="body", name = "key", value = "文件名，可传可不传，如果文件名在七牛云存在会删除该文件，然后存储。", required = false)
    })
    public ResponseEntity<Object> uploadImages(MultipartFile multipartFile, @RequestParam(value = "key", required = false) String key, HttpServletRequest request) {
        try {
            if (multipartFile == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultUtils.setMsg("没有文件！"));
            }
            // 在根目录下创建一个tempfileDir的临时文件夹
            String contextpath = request.getSession().getServletContext().getRealPath("/") + "/tempfileDir";
            File f = new File(contextpath);
            if (!f.exists()) {
                f.mkdirs();
            }
            // 在tempfileDir的临时文件夹下创建一个新的和上传文件名一样的文件
            String filename = multipartFile.getOriginalFilename();
            String filepath = contextpath + "/" + filename;
            File newFile = new File(filepath);
            multipartFile.transferTo(newFile);

            // 如果是修改图片，先把之前的图片删除
            if (StringUtils.isNotEmpty(key)) {
                QiNiuUpload.delete(key);
            }
            String fileSuffix = CommonUtils.getFileSuffix(newFile.getName());
            String fileName = QiNiuUpload.upload(newFile, fileSuffix);
            if (fileName != null) {
                // 删除临时文件
                if (newFile.exists()) {
                    newFile.delete();
                }
                return ResponseEntity.ok(ResultUtils.setMsg(fileName));
            }
        } catch (Exception e) {
            log.error("上传图片错误！", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultUtils.errorMsg());
    }
}
