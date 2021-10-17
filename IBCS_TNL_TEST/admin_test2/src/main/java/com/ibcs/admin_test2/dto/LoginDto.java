package com.ibcs.admin_test2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String loginId;//codeOrEmailOrMobileNo

    private String password;
}
