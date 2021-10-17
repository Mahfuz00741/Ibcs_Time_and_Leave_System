package com.ibcs.hr_test2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "HR_DEPT")
@NoArgsConstructor
@AllArgsConstructor
public class Dept extends BaseEntity {

    @Column(unique = true, nullable = false, length = 10)
    private String code;

    @Column(unique = true, nullable = false, length = 35)
    private String name;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "HR_EMP_HOD_ID")
    private Emp headOfId;
}
