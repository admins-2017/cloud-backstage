package com.giantlizardcloud.sys.service.impl;

import com.giantlizardcloud.sys.entity.Menu;
import com.giantlizardcloud.sys.mapper.MenuMapper;
import com.giantlizardcloud.sys.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.giantlizardcloud.vo.MenuTreeVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-08-04
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<MenuTreeVo> getMenuByRole(Long id) {
        return this.baseMapper.getMenuByRoleId(id);
    }
}
