package com.giantlizardcloud.merchant.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.giantlizardcloud.config.json.JSONResult;
import com.giantlizardcloud.merchant.dto.AddRepaymentDto;
import com.giantlizardcloud.merchant.dto.QueryRepaymentDto;
import com.giantlizardcloud.merchant.service.IRepaymentService;
import com.giantlizardcloud.merchant.vo.RepaymentWithAnnexVo;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-12-16
 */
@RestController
@RequestMapping("/repayment")
public class RepaymentController {

    private final IRepaymentService repaymentService;

    @PostMapping
    public JSONResult addRepayment(AddRepaymentDto dto){
        repaymentService.addRepayment(dto);
        return JSONResult.ok("添加还款单完成");
    }

    @PutMapping("/{id}")
    public JSONResult invalidRepayment(@PathVariable("id") Long rid){
        repaymentService.invalidRepayment(rid);
        return JSONResult.ok("作废还款单完成");
    }

    @GetMapping
    public JSONResult getRepayment( QueryRepaymentDto dto){
        IPage<RepaymentWithAnnexVo> vos = repaymentService.getRepayment(dto);
        return JSONResult.ok(vos);
    }

    public RepaymentController(IRepaymentService repaymentService) {
        this.repaymentService = repaymentService;
    }
}
