package com.ibcs.tnl_test2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveTypeDto extends BaseEntityDto{

    private String name;

    private Long allowNoOfLeaveMonth;

    private Long allowNoOfLeaveYearly;

    private boolean active;
}
