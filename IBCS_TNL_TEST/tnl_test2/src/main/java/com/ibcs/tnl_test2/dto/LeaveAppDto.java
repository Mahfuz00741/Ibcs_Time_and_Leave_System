package com.ibcs.tnl_test2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveAppDto extends BaseEntityDto{

    private LocalDate appDate;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String entry;

    private String reason;

    private Long employeeId;

    private Long leaveTypeId;

    private String onLeaveContactNo;

    private Long responsiblePersonId;

    private boolean active;

    private String remark;

    private String status;
}
