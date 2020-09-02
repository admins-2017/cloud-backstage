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
        //使用迭代器进行根目录获取 并将已经添加到result中菜单删除
        Iterator<MenuTreeVo> iterator = list.iterator();
        while (iterator.hasNext()) {
            MenuTreeVo menu = iterator.next();
            if(0 == menu.getParentId()) {
                result.add(menu);
                iterator.remove();
            }
        }
        // 2、递归获取子节点
        for (MenuTreeVo parent : result) {
            parent = recursiveTree(parent, list);
        }
        // 将没有父级目录的二级菜单 添加到result中
        for (MenuTreeVo menu : list) {
            if(1 == menu.getType()) {
                result.add(menu);
            }
        }

        return result;
    }

    public static MenuTreeVo recursiveTree(MenuTreeVo parent, List<MenuTreeVo> list) {
        //使用迭代器根据父级id进行递归子节点 并将以添加的目录删除
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
