package com.ibcs.hr_test2.api;

import com.ibcs.hr_test2.dto.DesgDto;
import com.ibcs.hr_test2.service.DesgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/desgApi")
public class DesgApi {

    @Autowired
    private DesgService desgService;

    @GetMapping("/")
    Page<DesgDto> all() {

        return desgService.findAll(PageRequest.of(0, 10), null);
    }

    @GetMapping("/{id}")
    public DesgDto findOne(@PathVariable Long id){
        return desgService.findOne(id);
    }

    @PostMapping("/")
    public DesgDto createDesg(@RequestBody DesgDto newDesg){
        return desgService.save(newDesg);
    }

    @PutMapping("/{id}")
    public DesgDto updateDesg(@RequestBody DesgDto newDesg, @PathVariable Long id){
        return desgService.update(newDesg, id);
    }

    @DeleteMapping("/{id}")
    public void deleteDesg(@PathVariable Long id){
        desgService.deleteById(id);
    }
}
