package com.ibcs.admin_test2.service;

import com.ibcs.admin_test2.dto.UserDto;
import com.ibcs.admin_test2.entity.User;
import com.ibcs.admin_test2.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private UserDto conv(User user){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);

        return userDto;
    }

    public UserDto save(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        return conv(userRepo.save(user));
    }

    public UserDto update(UserDto userDto, Long id){
        User user = userRepo.getById(id);
        BeanUtils.copyProperties(userDto, user, "id");

        return conv(userRepo.save(user));
    }

    public UserDto findById(Long id){
        User user = userRepo.getById(id);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);

        return userDto;
    }

    public Page<UserDto> findAll(Pageable pageable, String sText) {
        Page<User> user = userRepo.findAllCustom(pageable, sText);


        List<UserDto> sss = new ArrayList(pageable.getPageSize());
        for (User pp : user.getContent()) {
            sss.add(conv(pp));
        }

        Page<UserDto> userDtos = new PageImpl(sss, pageable, user.getTotalElements());

        return userDtos;
    }


    public void deleteById(Long id){
        userRepo.deleteById(id);
    }
}
