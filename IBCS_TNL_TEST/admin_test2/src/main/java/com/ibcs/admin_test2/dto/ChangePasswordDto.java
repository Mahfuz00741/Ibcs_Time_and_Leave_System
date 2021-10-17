package com.ibcs.admin_test2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {

    private Long userId;

    private String oldPassword;
    private String newPassword;
    private String retypePassword;
}
