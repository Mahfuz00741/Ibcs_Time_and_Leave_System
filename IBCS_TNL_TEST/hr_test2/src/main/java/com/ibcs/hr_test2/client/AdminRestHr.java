package com.ibcs.hr_test2.client;

import com.ibcs.hr_test2.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="ADMIN-SERVICE")
public interface AdminRestHr {

    @GetMapping("/admin/userApi/{id}")
    public UserDto getUser(@PathVariable Long id);
}
