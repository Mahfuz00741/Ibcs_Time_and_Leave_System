package com.ibcs.hr_test2.api;

import com.ibcs.hr_test2.dto.EmpDto;
import com.ibcs.hr_test2.dto.ResponseFeignClientDto;
import com.ibcs.hr_test2.dto.UserDto;
import com.ibcs.hr_test2.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empApi")
public class EmpApi {

    @Autowired
    private EmpService empService;


    @GetMapping("/")
    public Page<EmpDto> all() {
        return empService.findAll(PageRequest.of(0, 10), null);
    }

    @GetMapping("/{id}")
    public EmpDto findOne(@PathVariable Long id) {
        return empService.findById(id);
    }

    @PostMapping("/")
    public EmpDto createEmp(@RequestBody EmpDto newEmp){
        return empService.save(newEmp);
    }

    @PutMapping("/{id}")
    public EmpDto updateEmp(@RequestBody EmpDto newEmp, @PathVariable Long id){
        return empService.update(newEmp, id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmp(@PathVariable Long id){
        empService.deleteById(id);
    }

    // AdminRestHr feign client Api here..
    @GetMapping("userDetails/{id}")
    public ResponseFeignClientDto userView(@PathVariable Long id){
        return empService.findUser(id);
    }

}
