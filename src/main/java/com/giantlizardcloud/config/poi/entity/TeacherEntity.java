package com.giantlizardcloud.config.poi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ExcelTarget("teacherEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherEntity {

    private String id;
    /** name */
    @Excel(name = "老师", orderNum = "1", width = 25,needMerge = true)
    private String name;

    @ExcelCollection(name = "学生", orderNum = "4")
    private List<StudentEntity> students;
}
