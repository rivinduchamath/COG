package com.cloudofgoods.usersservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    @Id
    private Long id;
    @OneToOne(mappedBy = "vendor", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private User user;
}
