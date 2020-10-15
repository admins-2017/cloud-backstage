package com.giantlizardcloud.config.poi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ExcelTarget("courseEntity")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseEntity {
    /**
     * 主键
     */
    private String id;
    /**
     * 课程名称
     */
    @Excel(name = "课程名称", orderNum = "1", width = 25,needMerge = true)
    private String name;
    /**
     * 老师主键
     */
    @ExcelEntity(id = "absent")
    private List<TeacherEntity> mathTeacher;

}