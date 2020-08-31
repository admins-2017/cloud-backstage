package com.giantlizardcloud.sys.service;

import com.giantlizardcloud.sys.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.giantlizardcloud.vo.MenuTreeVo;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
public interface IMenuService extends IService<Menu> {

    List<MenuTreeVo> getMenuByRole(Long id);
}
