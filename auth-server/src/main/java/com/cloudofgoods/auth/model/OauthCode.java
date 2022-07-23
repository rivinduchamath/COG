package com.cloudofgoods.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "oauth_code")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OauthCode implements SuperEntity {
    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "authentication")
    private Long authentication;





}

