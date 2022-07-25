package com.cloudofgoods.auth.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserRegister {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
//    @ValidPassword
    @NotEmpty
    private String password;
//    @ValidPassword
    private String matchingPassword;

//    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

}
