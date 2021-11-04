package com.ibcs.tnl_test2.api;

import com.ibcs.tnl_test2.dto.LeaveAppDto;
import com.ibcs.tnl_test2.dto.ResponseFeignClientDto;
import com.ibcs.tnl_test2.service.LeaveAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/leaveAppApi")
public class LeaveAppApi {

    @Autowired
    private LeaveAppService leaveAppService;

    @GetMapping("/")
    public Page<LeaveAppDto> all() {
        return leaveAppService.findAll(PageRequest.of(0, 10), null);
    }

    @GetMapping("/{id}")
    public LeaveAppDto findOne(@PathVariable Long id) {
        return leaveAppService.findById(id);
    }

    @PostMapping("/")
    public LeaveAppDto createLeaveApp(@RequestBody LeaveAppDto newEmp){
        return leaveAppService.save(newEmp);
    }

    @PutMapping("/{id}")
    public LeaveAppDto updateLeaveApp(@RequestBody LeaveAppDto newEmp, @PathVariable Long id){
        return leaveAppService.update(newEmp, id);
    }

    @DeleteMapping("/{id}")
    public void deleteLeaveApp(@PathVariable Long id){
        leaveAppService.deleteById(id);
    }

    // Feign client api...

    @GetMapping("empDetails/{id}")
    public ResponseFeignClientDto empView(@PathVariable Long id){
        return leaveAppService.findEmp(id);
    }
}
