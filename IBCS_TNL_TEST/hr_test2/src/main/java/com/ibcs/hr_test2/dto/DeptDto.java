package com.ibcs.hr_test2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptDto extends BaseEntityDto{

    private String code;

    private String name;

    private boolean active;

    private Long headOfId;
}
