package com.cloudofgoods.usersservice.entity;


import com.cloudofgoods.usersservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements SuperEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    private String firsName;
    @Column(name = "last_name",length = 1000)
    private String lastName;
    @Column(name = "auth_user_id")
    private int authUserId;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @OneToMany(mappedBy = "userId")
    private List<UserAddress> userAddresses = new ArrayList<>();
    @OneToMany(mappedBy = "userId")
    private List<UserTelephoneNumber> telephoneNumbers = new ArrayList<>();
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "vendor",
            inverseJoinColumns = @JoinColumn(name = "vendor", referencedColumnName = "id"))
    private Vendor vendor;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "driver",
            inverseJoinColumns = @JoinColumn(name = "driver", referencedColumnName = "id"))
    private Driver driver;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "affiliate_user",
            inverseJoinColumns = @JoinColumn(name = "affiliate_user", referencedColumnName = "id"))
    private AffiliateUser affiliate_user;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "agent",
            inverseJoinColumns = @JoinColumn(name = "agent", referencedColumnName = "id"))
    private Agent agent;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "customer",
            inverseJoinColumns = @JoinColumn(name = "customer", referencedColumnName = "id"))
    private Customer customer;
}
