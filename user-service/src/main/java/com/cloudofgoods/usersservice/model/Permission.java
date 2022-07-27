package com.cloudofgoods.usersservice.model;

import com.cloudofgoods.usersservice.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Table(name = "permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements SuperEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
}

