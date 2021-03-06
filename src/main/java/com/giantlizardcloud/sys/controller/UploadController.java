package com.giantlizardcloud.sys.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.security.until.SecurityUntil;
import com.giantlizardcloud.sys.entity.UserDetails;
import com.giantlizardcloud.sys.service.FileService;
import com.giantlizardcloud.sys.service.IUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kang
 * @version 1.0
 * @date 2020/2/24 12:24
 */
@RestController
@RequestMapping("/upload")
@Api(value = "上传图片模块",tags = "上传图片对应操作")
@Slf4j
public class UploadController {


    final FileService fileService;
    private final IUserDetailsService detailsService;

    @Value("${baseUploadUrl}")
    private String url;
    @Value("${qiniu.path}")
    private String path;

    public UploadController(FileService fileService, IUserDetailsService detailsService) {
        this.fileService = fileService;
        this.detailsService = detailsService;
    }

    @PostMapping(value = "/uploadImg")
    @ApiOperation(value = "单个图片上传到七牛云")
    public JSONResult uploadImg(@RequestParam(value = "file") MultipartFile upFile) {
        String fileName = upFile.getOriginalFilename();
        log.info("fileName:"+fileName);
        File file = new File(url + fileName);
        log.info("file:"+file);
        String url;
        try{
            //将MulitpartFile文件转化为file文件格式
            upFile.transferTo(file);
            String result = fileService.uploadFile(file);
            url="http://"+path+'/'+result;
            file.delete();
            return JSONResult.ok(url);
        }catch (Exception e){
            return JSONResult.errorException(e.getMessage());
        }
    }


    @PostMapping(value = "/uploadImgList")
    @ApiOperation(value = "多个图片上传到七牛云")
    public JSONResult uploadImgList(@RequestParam("files") MultipartFile[] files)  {
        log.info("长度，{}",files.length);

        List<String> list = new ArrayList<>();
        for (MultipartFile upFile:files) {
            String fileName = upFile.getOriginalFilename();
            File file = new File(url + fileName);
            String url;
            try{
                //将MulitpartFile文件转化为file文件格式
                upFile.transferTo(file);
                String result = fileService.uploadFile(file);
                url="http://"+path+'/'+result;
                list.add(url);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return JSONResult.ok(list);
    }


    @PostMapping(value = "/uploadUserAvatar")
    @ApiOperation(value = "用户头像上传到七牛云")
    public JSONResult uploadUserAvatar(@RequestParam(value = "file") MultipartFile upFile) {
        String fileName = upFile.getOriginalFilename();
        log.info("fileName:"+fileName);
        File file = new File(url + fileName);
        log.info("file:"+file);
        String url;
        try{
            //将MulitpartFile文件转化为file文件格式
            upFile.transferTo(file);
            String result = fileService.uploadFile(file);
            url="http://"+path+'/'+result;
            detailsService.update(new UpdateWrapper<UserDetails>().set("user_details_url",url).eq("user_id", SecurityUntil.getUserId()));
            file.delete();
            return JSONResult.ok(url);
        }catch (Exception e){
            return JSONResult.errorException(e.getMessage());
        }

    }
}


