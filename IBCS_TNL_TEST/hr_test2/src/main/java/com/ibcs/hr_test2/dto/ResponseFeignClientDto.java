package com.ibcs.hr_test2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFeignClientDto {

    private String userMessage;
    private EmpDto empDto;
    private UserDto userDto;
}
