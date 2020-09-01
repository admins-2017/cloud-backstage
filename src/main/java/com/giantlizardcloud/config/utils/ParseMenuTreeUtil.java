package com.giantlizardcloud.config.utils;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.giantlizardcloud.vo.MenuTreeVo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParseMenuTreeUtil {

    /**
     * @方法名: parseMenuTree<br>
     * @描述: 组装菜单<br>
     * @param list 数据库里面获取到的全量菜单列表
     * @return
     */
    public static List<MenuTreeVo> parseMenuTree(List<MenuTreeVo> list){
        List<MenuTreeVo> result = new ArrayList<>();

        // 1、获取第一级节点
//        for (MenuTreeVo menu : list) {
//            if(0 == menu.getParentId()) {
//                result.add(menu);
//                list.remove(menu);
//            }
//        }
        Iterator<MenuTreeVo> iterator = list.iterator();
        while (iterator.hasNext()) {
            MenuTreeVo menu = iterator.next();
            if(0 == menu.getParentId()) {
                result.add(menu);
                iterator.remove();
            }
        }

        System.out.println(result.toString());
        // 2、递归获取子节点
        for (MenuTreeVo parent : result) {
            parent = recursiveTree(parent, list);
        }

        // 如果没有一级节点 则将2级节点做主节点
        for (MenuTreeVo menu : list) {
            if(1 == menu.getType()) {
                result.add(menu);
            }
        }

        return result;
    }

    public static MenuTreeVo recursiveTree(MenuTreeVo parent, List<MenuTreeVo> list) {
//        for (MenuTreeVo menu : list) {
//            if(parent.getMenuId() .equals( menu.getParentId())) {
//                parent.getChildren().add(menu);
//                list.remove(menu);
//            }
//        }
        Iterator<MenuTreeVo> iterator = list.iterator();
        while (iterator.hasNext()) {
            MenuTreeVo vo = iterator.next();
            if(parent.getMenuId() .equals( vo.getParentId())) {
                parent.getChildren().add(vo);
                iterator.remove();
            }
        }
        return parent;
    }
}
