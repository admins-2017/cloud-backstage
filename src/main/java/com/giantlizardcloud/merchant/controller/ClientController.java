package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.config.utils.IdWorker;
import com.giantlizardcloud.merchant.dto.QueryClientDto;
import com.giantlizardcloud.merchant.dto.UpdateClientDto;
import com.giantlizardcloud.merchant.entity.Client;
import com.giantlizardcloud.merchant.entity.Supplier;
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

    @GetMapping("/query")
    public JSONResult getClient(QueryClientDto dto) {
        return JSONResult.ok(clientService.page(new Page<>(dto.getPage(), dto.getSize())
                , new QueryWrapper<Client>().like(!"".equals(dto.getClientName())&&null!=dto.getClientName(), "client_name", dto.getClientName())
                        .like(!"".equals(dto.getClientPhone())&&null!=dto.getClientPhone(),"client_phone",dto.getClientPhone())
                        .like(!"".equals(dto.getClientEmail())&&null!=dto.getClientEmail(),"client_email",dto.getClientEmail())
                        .eq(null!=dto.getClientStatus(),"client_status",dto.getClientStatus())));
    }

    @GetMapping
    public JSONResult getAllClient() {
        return JSONResult.ok(clientService.list(new QueryWrapper<Client>().eq("client_status", 1)));
    }

    @PostMapping
    public JSONResult addClient(Client client) {
        client.setClientId(new IdWorker().nextId());
        clientService.save(client);
        return JSONResult.ok("添加客户成功");
    }

    @PutMapping
    public JSONResult updateClient(UpdateClientDto dto) {
        clientService.update(new UpdateWrapper<Client>()
                .set(dto.getClientName() != null && !"".equals(dto.getClientName()), "client_name", dto.getClientName())
                .set(dto.getClientPhone() != null && !"".equals(dto.getClientPhone()), "client_phone", dto.getClientPhone())
                .set(dto.getClientAddress() != null && !"".equals(dto.getClientAddress()), "client_address", dto.getClientAddress())
                .set(dto.getClientGender() != null && !"".equals(dto.getClientGender()), "client_gender", dto.getClientGender())
                .set(dto.getClientBirthday() != null && !"".equals(dto.getClientBirthday()), "client_birthday", dto.getClientBirthday())
                .set(dto.getClientEmail() != null && !"".equals(dto.getClientEmail()), "client_email", dto.getClientEmail())
                .set(dto.getClientDesc() != null && !"".equals(dto.getClientDesc()), "client_desc", dto.getClientDesc())
                .eq("client_id", dto.getClientId()));
        return JSONResult.ok("修改客户成功");
    }

    @PutMapping("/status/{id}/{status}")
    public JSONResult removeClientById(@PathVariable Long id, @PathVariable int status) {
        clientService.update(new UpdateWrapper<Client>().set("client_status", status).eq("client_id", id));
        if (status == 0) {
            return JSONResult.ok("删除客户成功");
        } else {
            return JSONResult.ok("恢复客户成功");

        }
    }

}
