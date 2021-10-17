package com.ibcs.tnl_test2.service;

import com.ibcs.tnl_test2.dto.LeaveAppDto;
import com.ibcs.tnl_test2.entity.LeaveApp;
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
public class LeaveAppService {

    @Autowired
    private LeaveAppRepo leaveAppRepo;
    @Autowired
    private LeaveTypeRepo leaveTypeRepo;


    private LeaveAppDto conv(LeaveApp leaveApp){
        LeaveAppDto leaveAppDto = new LeaveAppDto();
        BeanUtils.copyProperties(leaveApp, leaveAppDto, "leaveTypeId", "entry");
        leaveAppDto.setLeaveTypeId(leaveApp.getLeaveTypeId().getId());
        leaveAppDto.setEntry(leaveApp.getEntry().name());

        return leaveAppDto;
    }

    public LeaveAppDto save(LeaveAppDto leaveAppDto){
        LeaveApp leaveApp = new LeaveApp();
        BeanUtils.copyProperties(leaveAppDto, leaveApp, "leaveTypeId", "entry");
        leaveApp.setLeaveTypeId(leaveTypeRepo.getById(leaveAppDto.getLeaveTypeId()));
        leaveApp.setEntry(LeaveApp.EntryType.valueOf(leaveAppDto.getEntry()));

        return conv(leaveAppRepo.save(leaveApp));
    }

    public LeaveAppDto update(LeaveAppDto leaveAppDto, Long id){
        LeaveApp leaveApp = leaveAppRepo.getById(id);
        BeanUtils.copyProperties(leaveAppDto, leaveApp, "id", "leaveTypeId", "entry");
        leaveApp.setLeaveTypeId(leaveTypeRepo.getById(leaveAppDto.getLeaveTypeId()));
        leaveApp.setEntry(LeaveApp.EntryType.valueOf(leaveAppDto.getEntry()));

        return conv(leaveAppRepo.save(leaveApp));
    }

    public LeaveAppDto findById(Long id){
        LeaveApp leaveApp = leaveAppRepo.getById(id);
        LeaveAppDto leaveAppDto = new LeaveAppDto();
        BeanUtils.copyProperties(leaveApp, leaveAppDto);

        return leaveAppDto;
    }

    public Page<LeaveAppDto> findAll(Pageable pageable, String sText){
        Page<LeaveApp> emp = leaveAppRepo.findAllCustom(pageable, sText);
        List<LeaveAppDto> ss = new ArrayList(pageable.getPageSize());
        for (LeaveApp pp : emp.getContent()) {
            ss.add(conv(pp));
        }

        Page<LeaveAppDto> leaveAppDtos = new PageImpl(ss, pageable, emp.getTotalElements());

        return leaveAppDtos;
    }

    public void deleteById(Long id){
        leaveAppRepo.deleteById(id);
    }
}
