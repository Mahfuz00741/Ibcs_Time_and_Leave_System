package com.ibcs.hr_test2.service;

import com.ibcs.hr_test2.dto.DesgDto;
import com.ibcs.hr_test2.entity.Dept;
import com.ibcs.hr_test2.entity.Desg;
import com.ibcs.hr_test2.repo.DesgRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DesgService {

    @Autowired
    private DesgRepo desgRepo;

    private DesgDto conv(Desg desg){
        DesgDto desgDto = new DesgDto();
        BeanUtils.copyProperties(desg, desgDto);

        return desgDto;
    }

    public DesgDto save(DesgDto desgDto){
        Desg desg = new Desg();
        BeanUtils.copyProperties(desgDto, desg);

        return conv(desgRepo.save(desg));
    }

    public DesgDto update(DesgDto desgDto, Long id){
        Desg desg = desgRepo.getById(id);
        BeanUtils.copyProperties(desgDto, desg, "id");

        return conv(desgRepo.save(desg));
    }

    public DesgDto findOne(Long id){
        Desg desg = desgRepo.getById(id);
        DesgDto desgDto = new DesgDto();
        BeanUtils.copyProperties(desg, desgDto);

        return desgDto;
    }

    public Page<DesgDto> findAll(Pageable pageable, String sText) {
        Page<Desg> desg = desgRepo.findAllCustom(pageable, sText);


        List<DesgDto> ssp = new ArrayList(pageable.getPageSize());
        for (Desg pp : desg.getContent()) {
            ssp.add(conv(pp));
        }

        Page<DesgDto> desgDtos = new PageImpl(ssp, pageable, desg.getTotalElements());

        return desgDtos;
    }

    public void deleteById(Long id){
        desgRepo.deleteById(id);
    }
}
