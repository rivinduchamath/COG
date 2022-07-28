package com.cloudofgoods.usersservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "affiliate_user")
public class AffiliateUser {
    @Id
    private Long id;
    @OneToOne(mappedBy = "affiliate_user", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private User user;
}
