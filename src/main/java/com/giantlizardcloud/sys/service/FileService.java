package com.giantlizardcloud.sys.service;

import com.qiniu.common.QiniuException;

import java.io.File;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/24 12:22
 */
public interface FileService {

    /**
     * 文件上传
     * @param file
     * @return
     * @throws QiniuException
     */
    String uploadFile(File file) throws QiniuException;

}

