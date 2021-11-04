package com.ibcs.tnl_test2.api;

import com.ibcs.tnl_test2.dto.LeaveTypeDto;
import com.ibcs.tnl_test2.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/leaveTypeApi")
public class LeaveTypeApi {

    @Autowired
    private LeaveTypeService leaveTypeService;

    @GetMapping("/")
    Page<LeaveTypeDto> all() {

        return leaveTypeService.findAll(PageRequest.of(0, 10), null);
    }

    @GetMapping("/{id}")
    public LeaveTypeDto findOne(@PathVariable Long id){
        return leaveTypeService.findById(id);
    }

    @PostMapping("/")
    public LeaveTypeDto createLeaveType(@RequestBody LeaveTypeDto newDept){
        return leaveTypeService.save(newDept);
    }

    @PutMapping("/{id}")
    public LeaveTypeDto updateLeaveType(@RequestBody LeaveTypeDto newDept, @PathVariable Long id){
        return leaveTypeService.update(newDept, id);
    }

    @DeleteMapping("/{id}")
    public void deleteLeaveType(@PathVariable Long id){
        leaveTypeService.deleteById(id);
    }
}
