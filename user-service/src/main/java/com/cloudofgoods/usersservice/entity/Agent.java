package com.cloudofgoods.usersservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agent {
    @Id
    private Long id;
    @OneToOne(mappedBy = "agent", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private User user;
}
