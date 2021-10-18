package com.ibcs.tnl_test2.client;

import com.ibcs.tnl_test2.dto.EmpDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HR-SERVICE")
public interface HrRestTnl {

    @GetMapping("/hr/empApi/{id}")
    public EmpDto getEmp(@PathVariable Long id);
}
