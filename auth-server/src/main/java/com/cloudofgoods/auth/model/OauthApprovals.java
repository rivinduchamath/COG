package com.cloudofgoods.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "oauth_approvals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OauthApprovals implements SuperEntity {
    @Id
    @Column(name = "userId")
    private String userId;

    @Column(name = "clientId")
    private String clientId;

    @Column(name = "scope")
    private String scope;

    @Column(name = "expiresAt")
    private Date expiresAt;

    @Column(name = "lastModifiedAt")
    private Date lastModifiedAt;



}

