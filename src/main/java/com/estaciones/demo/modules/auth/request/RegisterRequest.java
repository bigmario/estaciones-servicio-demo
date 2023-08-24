package com.estaciones.demo.modules.auth.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotNull
    @NotEmpty
    String username;

    @NotNull
    @NotEmpty
    String password;

    @NotNull
    @NotEmpty
    String name;

    @NotEmpty
    String email;
}
