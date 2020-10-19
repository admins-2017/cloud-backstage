package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.dto.UpdateClientDto;
import com.giantlizardcloud.merchant.entity.Client;
import com.giantlizardcloud.merchant.service.IClientService;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-10-19
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{page}/{size}")
    public JSONResult getClientByPage(@PathVariable int page, @PathVariable int size) {
        return JSONResult.ok(clientService.page(new Page<>(page, size)));
    }

    @GetMapping
    public JSONResult getAllClient() {
        return JSONResult.ok(clientService.list(new QueryWrapper<Client>().eq("client_status", 0)));
    }

    @PostMapping
    public JSONResult addClient(Client client) {
        clientService.save(client);
        return JSONResult.ok("添加客户成功");
    }

    @PutMapping
    public JSONResult updateClient(UpdateClientDto dto) {
        return JSONResult.ok(dto);
    }

    @PutMapping("/status/{id}/{status}")
    public JSONResult removeClientById(@PathVariable Long id, @PathVariable int status) {
        clientService.update(new UpdateWrapper<Client>().set("client_status", status).eq("client_id", id));
        if (status==0){
            return JSONResult.ok("恢复客户成功");
        }else {
            return JSONResult.ok("删除客户成功");
        }
    }

}
