package com.ibcs.tnl_test2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TNL_LEAVE_TYPE")
@NoArgsConstructor
@AllArgsConstructor
public class LeaveType extends BaseEntity {

    @Column(nullable=false, length=35)
    private String name;

    @Column(name="ALLOW_NO_OF_LEAVE_MONTH", nullable = false)
    private Long allowNoOfLeaveMonth;

    @Column(name="ALLOW_NO_OF_LEAVE_YEARLY",nullable = false )
    private Long allowNoOfLeaveYearly;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean active;
}
