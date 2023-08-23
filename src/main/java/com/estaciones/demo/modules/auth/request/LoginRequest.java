package com.estaciones.demo.modules.auth.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "username is mandatory")
    @NotNull
    @NotEmpty
    String username;

    @NotBlank(message = "password is mandatory")
    @NotNull
    @NotEmpty
    String password; 
}
