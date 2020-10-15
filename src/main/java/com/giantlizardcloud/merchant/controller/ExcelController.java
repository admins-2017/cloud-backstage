package com.giantlizardcloud.merchant.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.giantlizardcloud.config.poi.entity.UserVo;
import com.giantlizardcloud.config.poi.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kang
 * @version 1.0
 * @date 2019/12/17 17:09
 */

@RestController
@RequestMapping("/common/excel")
@Slf4j
public class ExcelController {

    /**
     * 导出
     * @author ershuai
     * @date 2019年7月23日 下午2:19:56
     * @param response
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void toExport(HttpServletResponse response) {
        List<UserVo> users = new ArrayList<UserVo>();
        //...
        UserVo user = new UserVo();
        user.setNickName("张三");
        user.setUserIntro("我是张三，认识下！");
        user.setUserSex(1);
        user.setCreateTime(new Date());
        users.add(user);
        user = new UserVo();
        user.setNickName("李四");
        user.setUserIntro("我是李四，认识下！");
        user.setUserSex(2);
        user.setCreateTime(new Date());
        users.add(user);
        user = new UserVo();
        user.setNickName("王五");
        user.setUserIntro("我是王五，认识下！");
        user.setUserSex(1);
        user.setCreateTime(new Date());
        users.add(user);
        FileUtils.exportExcel(users, "测试导出 title" , "测试导出 sheet", UserVo.class, "测试导出.xls", response);
    }

    /**
     * 导入
     * @author ershuai
     * @date 2019年7月23日 下午2:19:56
     * @param file
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public void toImport(@RequestParam("file") MultipartFile file) {
        //...
        ImportParams importParams = new ImportParams();
        importParams.setHeadRows(1);//表头行数,默认1
        importParams.setTitleRows(1);//表格标题行数,默认0
        importParams.setNeedVerify(false);// 需要验证
        try {
            List<UserVo> users = ExcelImportUtil.importExcel(file.getInputStream(), UserVo.class, importParams);
            log.info(JSON.toJSONString(users));
        } catch (IOException e) {
            log.error("io exception: {}", e);
        } catch (Exception e) {
            log.error("exception: {}", e);
        }
    }

}
