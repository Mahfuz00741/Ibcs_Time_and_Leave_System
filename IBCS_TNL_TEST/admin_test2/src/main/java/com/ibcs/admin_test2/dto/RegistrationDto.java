package com.ibcs.admin_test2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto implements Serializable {

    private String code;

    private String name;

    private String email;

    private String mobileNo;

    private String password;

    private String matchPassword;
}
