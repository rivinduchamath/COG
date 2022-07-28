package com.cloudofgoods.usersservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private Long id;
    @OneToOne(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private User user;
}