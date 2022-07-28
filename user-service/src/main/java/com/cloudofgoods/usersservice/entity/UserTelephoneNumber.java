package com.cloudofgoods.usersservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_telephone_number")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTelephoneNumber {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "country_code")
    private String country_code;
    @Column(name = "number")
    private String number;
    @Column(name = "status")
    private int status;
    @Column(name = "auth_user_id")
    private int auth_user_id;
    @Column(name = "created_at")
    private Date created_at;
    @Column(name = "updated_at")
    private Date updated_at;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User userId;



}
