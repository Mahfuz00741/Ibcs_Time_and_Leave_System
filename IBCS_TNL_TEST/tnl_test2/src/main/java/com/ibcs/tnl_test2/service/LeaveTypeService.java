package com.ibcs.tnl_test2.service;

import com.ibcs.tnl_test2.dto.LeaveTypeDto;
import com.ibcs.tnl_test2.entity.LeaveType;
import com.ibcs.tnl_test2.repo.LeaveTypeRepo;
import com.ibcs.tnl_test2.repo.LeaveAppRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveTypeService {

    @Autowired
    private LeaveTypeRepo leaveTypeRepo;
    @Autowired
    private LeaveAppRepo leaveAppRepo;

    private LeaveTypeDto conv(LeaveType leaveType){
        LeaveTypeDto leaveTypeDto = new LeaveTypeDto();
        BeanUtils.copyProperties(leaveType, leaveTypeDto);

        return leaveTypeDto;
    }

    public LeaveTypeDto save(LeaveTypeDto leaveTypeDto){
        LeaveType leaveType = new LeaveType();
        BeanUtils.copyProperties(leaveTypeDto, leaveType);

        return conv(leaveTypeRepo.save(leaveType));
    }

    public LeaveTypeDto update(LeaveTypeDto leaveTypeDto, Long id){
        LeaveType leaveType = leaveTypeRepo.getById(id);
        BeanUtils.copyProperties(leaveTypeDto, leaveType, "id");

        return conv(leaveTypeRepo.save(leaveType));
    }

    public LeaveTypeDto findById(Long id){
        LeaveType leaveType = leaveTypeRepo.getById(id);
        LeaveTypeDto leaveTypeDto = new LeaveTypeDto();
        BeanUtils.copyProperties(leaveType, leaveTypeDto);

        return leaveTypeDto;
    }

    public Page<LeaveTypeDto> findAll(Pageable pageable, String sText) {
        Page<LeaveType> dept = leaveTypeRepo.findAllCustom(pageable, sText);

        List<LeaveTypeDto> sss = new ArrayList(pageable.getPageSize());
        for (LeaveType pp : dept.getContent()) {
            sss.add(conv(pp));
        }

        Page<LeaveTypeDto> leaveTypeDtos = new PageImpl(sss, pageable, dept.getTotalElements());

        return leaveTypeDtos;
    }

    public void deleteById(Long id){
        leaveTypeRepo.deleteById(id);
    }
}
