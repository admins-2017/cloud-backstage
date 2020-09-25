package com.giantlizardcloud.config.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.merchant.entity.Classification;
import com.giantlizardcloud.merchant.vo.ClassificationVo;
import com.giantlizardcloud.vo.MenuTreeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class ParseClassificationTreeUtil {

    /**
     * @param list 数据库里面获取到的全量菜单列表
     * @return
     * @方法名: parseMenuTree<br>
     * @描述: 组装菜单<br>
     */
    public static Page<ClassificationVo> parseMenuTree(Page<Classification> parentList, List<Classification> list) {
        List<ClassificationVo> result = new ArrayList<>();
        List<ClassificationVo> childrenList = new ArrayList<>();

        List<Classification> records = parentList.getRecords();
        /**
         *  使用迭代器进行一级分类获取 并将已经添加到result中后将分类删除
         */
        Iterator<Classification> iterator = records.iterator();
        while (iterator.hasNext()) {
            Classification classification = iterator.next();
            ClassificationVo classificationVo = new ClassificationVo();
            BeanUtils.copyProperties(classification, classificationVo);
            result.add(classificationVo);
            iterator.remove();
        }
        /**
         *  使用迭代器进行二级分类获取 并将已经添加到childrenList中后将分类删除
         */
        Iterator<Classification> childrenIterator = list.iterator();
        while (childrenIterator.hasNext()) {
            Classification classification = childrenIterator.next();
//            if (1 == classification.getParentId()) {
                ClassificationVo classificationVo = new ClassificationVo();
                BeanUtils.copyProperties(classification, classificationVo);
                childrenList.add(classificationVo);
//            }
        }

        // 2、递归获取所有二级分类的子分类
        for (ClassificationVo parent : childrenList) {
            parent = recursiveTree(parent, list);
        }

        // 2、递归获取子分类
        for (ClassificationVo parent : result) {
            parent = recursiveTree2(parent, childrenList);
        }

        Page<ClassificationVo> vo = new Page<>();
        vo.setTotal(parentList.getTotal());
        vo.setRecords(result);
        vo.setSize(parentList.getSize());
        vo.setCurrent(parentList.getCurrent());
        vo.setPages(parentList.getPages());
        return vo;
    }

    public static ClassificationVo recursiveTree(ClassificationVo parent, List<Classification> list) {
        //使用迭代器根据父级id进行递归子节点 并将以添加的分类删除
        Iterator<Classification> iterator = list.iterator();
        while (iterator.hasNext()) {
            Classification classification = iterator.next();
            if (parent.getClassificationId().equals(classification.getParentId())) {
                ClassificationVo classificationVo = new ClassificationVo();
                BeanUtils.copyProperties(classification, classificationVo);
                parent.getChildren().add(classificationVo);
                iterator.remove();
            }
        }
        return parent;
    }

    public static ClassificationVo recursiveTree2(ClassificationVo parent, List<ClassificationVo> list) {
        //使用迭代器根据父级id进行递归子节点 并将以添加的分类删除
        Iterator<ClassificationVo> iterator = list.iterator();
        while (iterator.hasNext()) {
            ClassificationVo classification = iterator.next();
            if (parent.getClassificationId().equals(classification.getParentId())) {
                parent.getChildren().add(classification);
                iterator.remove();
            }
        }
        return parent;
    }


}

