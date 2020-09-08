package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.utils.IdWorker;
import com.giantlizardcloud.dto.InsertShopDto;
import com.giantlizardcloud.dto.UpdateShopDto;
import com.giantlizardcloud.merchant.entity.Shop;
import com.giantlizardcloud.merchant.service.IShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-08-20
 */
@RestController
@RequestMapping("/shop")
@Api(value = "商铺管理",tags = "商铺对应操作")
@Slf4j
public class ShopController {

    private final IShopService shopService;

    public ShopController(IShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    @ApiOperation(value="获取所有商铺")
    public JSONResult getAllShop(){
        List<Shop> shops = shopService.list(new QueryWrapper<Shop>().lt("shop_status", 3));
        return JSONResult.ok(shops);
    }

    @GetMapping("/{name}")
    @ApiOperation(value="根据商铺名模糊查询获取商铺",notes = "商铺名")
    public JSONResult getShopLikeName(@PathVariable String name){
        List<Shop> list = shopService.list(new QueryWrapper<Shop>().likeRight("shop_name", name));
        return JSONResult.ok(list);
    }

    @GetMapping("/{page}/{size}")
    @ApiOperation(value="获取所有商铺")
    public JSONResult getAllShopByPage(@PathVariable Integer page,@PathVariable Integer size){
        IPage<Shop> shops = shopService.selectAllShopWithUser(null,page,size);
        return JSONResult.ok(shops);
    }

    @GetMapping("/{page}/{size}/{shopName}")
    @ApiOperation(value="根据查询条件获取所有商铺")
    public JSONResult getShopByLikeName(@PathVariable Integer page,@PathVariable Integer size,@PathVariable String shopName){
        IPage<Shop> shops = shopService.selectAllShopWithUser(shopName,page,size);
        return JSONResult.ok(shops);
    }

    @PostMapping
    public JSONResult insertShop(InsertShopDto dto){
        log.info(dto.toString());
        Shop shop = new Shop();
        BeanUtils.copyProperties(dto,shop);
        shop.setShopId(new IdWorker().nextId());
        shopService.save(shop);
        return JSONResult.ok("新增商铺成功");
    }

    @DeleteMapping("/{shopId}")
    public JSONResult delShop(@PathVariable Long shopId){
        log.info(shopId.toString());
        return JSONResult.ok("删除商铺成功");
    }

    @PutMapping
    public JSONResult updateShop(UpdateShopDto dto){
        log.info(dto.toString());
        shopService.update(new UpdateWrapper<Shop>()
                .set(dto.getShopName()!=null&&!dto.getShopName().equals(""),"shop_name",dto.getShopName())
                .set(dto.getShopAddress()!=null&&!dto.getShopAddress().equals(""),"shop_address",dto.getShopAddress())
                .set(dto.getShopIntroduction()!=null&&!dto.getShopIntroduction().equals(""),"shop_introduction",dto.getShopIntroduction())
                .set(dto.getShopTel()!=null&&!dto.getShopTel().equals(""),"shop_tel",dto.getShopTel())
                .set(dto.getShopStatus()!=null&&!dto.getShopStatus().equals(""),"shop_status",dto.getShopStatus())
                .eq("shop_id",dto.getShopId()));
        return JSONResult.ok("修改商铺完成");
    }
}
