package com.giantlizardcloud.merchant.controller;



import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.dto.ClientDto;
import com.giantlizardcloud.merchant.entity.Client;
import com.giantlizardcloud.merchant.service.IClientService;
import com.giantlizardcloud.merchant.vo.ClientWithShopVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-24
 */
@RestController
@RequestMapping("/client")
@Api(value = "客户信息",tags = "客户信息对应操作")
@Slf4j
public class ClientController {

    private IClientService clientService;

    public ClientController(IClientService clientService){

        this.clientService=clientService;

    }
    /*
    * JSONResult.ok(clientService.page(new Page<>(page,size)
                ,new QueryWrapper<Client>().eq("client_name","郝树星")));
    * */
    //分页查询
    @GetMapping("/{page}/{size}")
    public JSONResult  getAllClient(@PathVariable Integer page, @PathVariable Integer size){

       IPage<ClientWithShopVo> clientList = clientService.getAllClientWithShop(page,size);

        return JSONResult.ok(clientList);

    }

    //查询所有客户
    @GetMapping
    @ApiOperation(value="获取所有客户")
    public JSONResult getAll(){

        List<Client> list = clientService.list();

        return  JSONResult.ok(list);
    }

        //

        @PostMapping
        @ApiOperation(value="新增客户")
        public JSONResult insertClient(Client client){

            clientService.save(client);

            return JSONResult.ok("新增完成");
        }


    @PutMapping("/{clientId}")
    @ApiOperation(value="修改客户信息",notes = "clientId客户Id")
    public JSONResult updateClient(@PathVariable Long  clientId , Client client){

        log.info(client.toString());

       // clientService.update(client,new UpdateWrapper<Client>());
        //clientService.update(clientDto,new UpdateWrapper<Client>().eq("client_id",clientId));
        clientService.update(client,new UpdateWrapper<Client>().eq("client_id",clientId));
        return  JSONResult.ok("修改完成");

    }


    @DeleteMapping("/{clientId}")
    @ApiOperation(value="删除客户",notes = "clientId客户Id")
    public JSONResult DeleteClient(@PathVariable Long clientId){
        System.out.println(clientId);
        //clientService.update(new UpdateWrapper<Client>().eq("client_id",clientId));
            clientService.removeById(clientId);
        return JSONResult.ok("删除成功");
    }




}
