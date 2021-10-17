package com.ibcs.hr_test2.service;

import com.ibcs.hr_test2.dto.DeptDto;
import com.ibcs.hr_test2.entity.Dept;
import com.ibcs.hr_test2.repo.DeptRepo;
import com.ibcs.hr_test2.repo.EmpRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeptService {

    @Autowired
    private DeptRepo deptRepo;
    @Autowired
    private EmpRepo empRepo;

    private DeptDto conv(Dept dept){
        DeptDto deptDto = new DeptDto();
        BeanUtils.copyProperties(dept, deptDto, "headOfId");
        deptDto.setHeadOfId(dept.getHeadOfId().getId());

        return deptDto;
    }

    public DeptDto save(DeptDto deptDto){
        Dept dept = new Dept();
        BeanUtils.copyProperties(deptDto, dept, "headOfId");
        dept.setHeadOfId(empRepo.getById(deptDto.getHeadOfId()));

        return conv(deptRepo.save(dept));
    }

    public DeptDto update(DeptDto deptDto, Long id){
        Dept dept = deptRepo.getById(id);
        BeanUtils.copyProperties(deptDto, dept, "id", "headOfId");
        dept.setHeadOfId(empRepo.getById(deptDto.getHeadOfId()));

        return conv(deptRepo.save(dept));
    }

    public DeptDto findById(Long id){
        Dept dept = deptRepo.getById(id);
        DeptDto deptDto = new DeptDto();
        BeanUtils.copyProperties(dept, deptDto);

        return deptDto;
    }

    public Page<DeptDto> findAll(Pageable pageable, String sText) {
        Page<Dept> dept = deptRepo.findAllCustom(pageable, sText);

        List<DeptDto> sss = new ArrayList(pageable.getPageSize());
        for (Dept pp : dept.getContent()) {
            sss.add(conv(pp));
        }

        Page<DeptDto> deptDtos = new PageImpl(sss, pageable, dept.getTotalElements());

        return deptDtos;
    }

    public void deleteById(Long id){
        deptRepo.deleteById(id);
    }
}
