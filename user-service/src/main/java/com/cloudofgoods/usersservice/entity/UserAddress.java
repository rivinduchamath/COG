//package com.cloudofgoods.usersservice.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.Cascade;
//
//import javax.persistence.*;
//import java.awt.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "user_address")
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class UserAddress {
//    @Id
////    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    @Column(name = "address_line_1")
//    private String addressLineOne;
//    @Column(name = "address_line_2")
//    private String addressLineTwo;
//    @Column(name = "address_line_3")
//    private String addressLineThree;
//    @Column(name = "zip_code")
//    private String zipCode;
//    @Column(name = "coordination")
//    private Point point;
//    @Column(name = "status")
//    private int status;
//    @Column(name = "city_id")
//
//    private String cityId;
//    @ManyToOne
//    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
//    @JoinColumn(name = "movie_id", referencedColumnName = "id", insertable = false, updatable = false)
//    @Column(name = "user_id")
//    private User userId;
//    @Column(name = "auth_user_id")
//    private String authUserId;
//    @Column(name = "created_at")
//    private Date createdAt;
//    @Column(name = "updated_at")
//    private Date updatedAt;
//
//
//}
