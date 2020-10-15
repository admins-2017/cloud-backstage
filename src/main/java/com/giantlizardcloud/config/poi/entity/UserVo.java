package com.giantlizardcloud.config.poi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author kang
 * @version 1.0
 * @date 2019/12/17 17:06
 */
@Data
public class UserVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6118059334688579641L;

    @Excel(name="昵称", width=15)
    private String nickName;

    @Excel(name="简介", width=20)
    private String userIntro;

    @Excel(name="性别", width=5, replace={"男_1", "女_2"})
    private Integer userSex;

    @Excel(name="注册时间", width=20, exportFormat="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
