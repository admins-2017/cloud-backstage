package com.giantlizardcloud.merchant.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.giantlizardcloud.config.poi.entity.UserVo;
import com.giantlizardcloud.config.poi.util.FileUtils;
import com.giantlizardcloud.merchant.service.IInventoryService;
import com.giantlizardcloud.merchant.vo.InventoryGetCommodityClassVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/excel")
@Slf4j
@Api(value = "excel管理", tags = "excel对应操作")
public class ExcelController {

    private final IInventoryService inventoryService;

    public ExcelController(IInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * 导出
     * @author ershuai
     * @date 2019年7月23日 下午2:19:56
     * @param response
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void toExport(HttpServletResponse response,Long shopId) {
        List<InventoryGetCommodityClassVo> vos = inventoryService.getInventoryCommodity(shopId);
        FileUtils.exportExcel(vos, " 商品种类明细" , "", InventoryGetCommodityClassVo.class, "商品种类明细.xls", response);
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
