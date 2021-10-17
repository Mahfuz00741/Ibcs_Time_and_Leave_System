package com.ibcs.hr_test2.api;

import com.ibcs.hr_test2.dto.DeptDto;
import com.ibcs.hr_test2.dto.EmpDto;
import com.ibcs.hr_test2.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deptApi")
public class DeptApi {

    @Autowired
    private DeptService deptService;

    @GetMapping("/")
    Page<DeptDto> all() {

        return deptService.findAll(PageRequest.of(0, 10), null);
    }

    @GetMapping("/{id}")
    public DeptDto findOne(@PathVariable Long id){
        return deptService.findById(id);
    }

    @PostMapping("/")
    public DeptDto createDept(@RequestBody DeptDto newDept){
        return deptService.save(newDept);
    }

    @PutMapping("/{id}")
    public DeptDto updateDept(@RequestBody DeptDto newDept, @PathVariable Long id){
        return deptService.update(newDept, id);
    }

    @DeleteMapping("/{id}")
    public void deleteDept(@PathVariable Long id){
        deptService.deleteById(id);
    }
}
