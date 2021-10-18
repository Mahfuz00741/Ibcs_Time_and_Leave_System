package com.ibcs.hr_test2.service;

import com.ibcs.hr_test2.client.AdminRestHr;
import com.ibcs.hr_test2.dto.EmpDto;
import com.ibcs.hr_test2.dto.ResponseFeignClientDto;
import com.ibcs.hr_test2.dto.UserDto;
import com.ibcs.hr_test2.entity.Emp;
import com.ibcs.hr_test2.repo.DeptRepo;
import com.ibcs.hr_test2.repo.DesgRepo;
import com.ibcs.hr_test2.repo.EmpRepo;
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
public class EmpService {

    @Autowired
    private EmpRepo empRepo;
    @Autowired
    private DeptRepo deptRepo;
    @Autowired
    private DesgRepo desgRepo;

    @Autowired
    private AdminRestHr adminRestHr;

    private EmpDto conv(Emp emp) {
        EmpDto empDto = new EmpDto();
        BeanUtils.copyProperties(emp, empDto, "deptId", "desgId", "supervisorId", "gender");
        empDto.setDeptId(emp.getDeptId().getId());
        empDto.setDesgId(emp.getDesgId().getId());
        empDto.setSupervisorId(emp.getSupervisorId().getId());
        empDto.setGender(emp.getGender().name());

        return empDto;
    }

    public EmpDto save(EmpDto empDto) {
        Emp emp = new Emp();
        BeanUtils.copyProperties(empDto, emp, "deptId", "desgId", "supervisorId", "gender");
        emp.setDeptId(deptRepo.getById(empDto.getDeptId()));
        emp.setDesgId(desgRepo.getById(empDto.getDesgId()));
        emp.setSupervisorId(empRepo.getById(empDto.getSupervisorId()));
        emp.setGender(Emp.Gender.valueOf(empDto.getGender()));

        return conv(empRepo.save(emp));
    }

    public EmpDto update(EmpDto empDto, Long id) {
        Emp emp = empRepo.getById(id);
        BeanUtils.copyProperties(empDto, emp, "id", "deptId", "desgId", "supervisorId", "gender");
        emp.setDeptId(deptRepo.getById(empDto.getDeptId()));
        emp.setDesgId(desgRepo.getById(empDto.getDesgId()));
        emp.setSupervisorId(empRepo.getById(empDto.getSupervisorId()));
        emp.setGender(Emp.Gender.valueOf(empDto.getGender()));

        return conv(empRepo.save(emp));
    }

    public EmpDto findById(Long id) {
        Emp emp = empRepo.getById(id);
        EmpDto empDto = new EmpDto();
        BeanUtils.copyProperties(emp, empDto);

        return empDto;
    }

    public Page<EmpDto> findAll(Pageable pageable, String sText) {
        Page<Emp> emp = empRepo.findAllCustom(pageable, sText);
        List<EmpDto> ss = new ArrayList(pageable.getPageSize());
        for (Emp pp : emp.getContent()) {
            ss.add(conv(pp));
        }

        Page<EmpDto> empDtos = new PageImpl(ss, pageable, emp.getTotalElements());

        return empDtos;
    }

    public void deleteById(Long id) {
        empRepo.deleteById(id);
    }


    public ResponseFeignClientDto findUser(Long id) {

        try {
            ResponseFeignClientDto responseFeignClientDto = new ResponseFeignClientDto();

            Emp emp = empRepo.getById(id);

            if (emp == null) {
                return new ResponseFeignClientDto("No Employee found....!!", null, null);
            } else {
                responseFeignClientDto.setEmpDto(conv(emp));
                UserDto userDto = adminRestHr.getUser(emp.getUserId());

                responseFeignClientDto.setUserDto((userDto));

                return responseFeignClientDto;
            }

        }
        catch (Exception exception) {
            log.error("Exception occurred during getting user info", exception);
            return new ResponseFeignClientDto(exception.getMessage(), null, null);
        }

    }
}