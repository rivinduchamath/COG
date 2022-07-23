package com.cloudofgoods.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "oauth_refresh_token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OauRefreshToken implements SuperEntity {
    @Id
    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "token")
    private Long token;

    @Column(name = "authentication")
    private Long authentication;

}

