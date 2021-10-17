package com.ibcs.hr_test2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "HR_DESG")
@NoArgsConstructor
@AllArgsConstructor
public class Desg extends BaseEntity {

    @Column(unique = true, nullable = false, length = 35)
    private String name;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean active;


}
