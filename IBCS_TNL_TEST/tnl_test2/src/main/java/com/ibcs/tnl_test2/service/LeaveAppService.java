package com.ibcs.tnl_test2.service;

import com.ibcs.tnl_test2.client.HrRestTnl;
import com.ibcs.tnl_test2.dto.EmpDto;
import com.ibcs.tnl_test2.dto.LeaveAppDto;
import com.ibcs.tnl_test2.dto.ResponseFeignClientDto;
import com.ibcs.tnl_test2.entity.LeaveApp;
import com.ibcs.tnl_test2.repo.LeaveTypeRepo;
import com.ibcs.tnl_test2.repo.LeaveAppRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LeaveAppService {

    @Autowired
    private LeaveAppRepo leaveAppRepo;
    @Autowired
    private LeaveTypeRepo leaveTypeRepo;
    @Autowired
    private HrRestTnl hrRestTnl;


    private LeaveAppDto conv(LeaveApp leaveApp) {
        LeaveAppDto leaveAppDto = new LeaveAppDto();
        BeanUtils.copyProperties(leaveApp, leaveAppDto, "leaveTypeId", "entry", "status");
        leaveAppDto.setLeaveTypeId(leaveApp.getLeaveTypeId().getId());
        leaveAppDto.setEntry(leaveApp.getEntry().name());
        leaveAppDto.setStatus(leaveApp.getStatus().name());
        return leaveAppDto;
    }

    public LeaveAppDto save(LeaveAppDto leaveAppDto) {
        LeaveApp leaveApp = new LeaveApp();
        BeanUtils.copyProperties(leaveAppDto, leaveApp, "leaveTypeId", "entry", "status");
        leaveApp.setLeaveTypeId(leaveTypeRepo.getById(leaveAppDto.getLeaveTypeId()));
        leaveApp.setEntry(LeaveApp.EntryType.valueOf(leaveAppDto.getEntry()));
        leaveApp.setStatus(LeaveApp.Status.valueOf(leaveAppDto.getStatus()));


        return conv(leaveAppRepo.save(leaveApp));
    }

    public LeaveAppDto update(LeaveAppDto leaveAppDto, Long id) {
        LeaveApp leaveApp = leaveAppRepo.getById(id);
        BeanUtils.copyProperties(leaveAppDto, leaveApp, "id", "leaveTypeId", "entry", "status");
        leaveApp.setLeaveTypeId(leaveTypeRepo.getById(leaveAppDto.getLeaveTypeId()));
        leaveApp.setEntry(LeaveApp.EntryType.valueOf(leaveAppDto.getEntry()));
        leaveApp.setStatus(LeaveApp.Status.valueOf(leaveAppDto.getStatus()));

        return conv(leaveAppRepo.save(leaveApp));
    }

    public LeaveAppDto findById(Long id) {
        LeaveApp leaveApp = leaveAppRepo.getById(id);
        LeaveAppDto leaveAppDto = new LeaveAppDto();
        BeanUtils.copyProperties(leaveApp, leaveAppDto);

        return leaveAppDto;
    }

    public Page<LeaveAppDto> findAll(Pageable pageable, String sText) {
        Page<LeaveApp> emp = leaveAppRepo.findAllCustom(pageable, sText);
        List<LeaveAppDto> ss = new ArrayList(pageable.getPageSize());
        for (LeaveApp pp : emp.getContent()) {
            ss.add(conv(pp));
        }

        Page<LeaveAppDto> leaveAppDtos = new PageImpl(ss, pageable, emp.getTotalElements());

        return leaveAppDtos;
    }

    public void deleteById(Long id) {
        leaveAppRepo.deleteById(id);
    }

    // Feign client implementation.....

    public ResponseFeignClientDto findEmp(Long id) {

        ResponseFeignClientDto responseFeignClientDto = new ResponseFeignClientDto();

        if (!leaveAppRepo.existsById(id)) {
            return new ResponseFeignClientDto("User not found", null, null);

        } else {

            LeaveApp leaveApp = leaveAppRepo.getById(id);
            responseFeignClientDto.setLeaveAppDto(conv(leaveApp));

            EmpDto empDto = hrRestTnl.getEmp(leaveApp.getEmployeeId());
            responseFeignClientDto.setEmpDto(empDto);

            responseFeignClientDto.setUserMessage("Successfully get user information.");


        }
        return responseFeignClientDto;
    }

}
