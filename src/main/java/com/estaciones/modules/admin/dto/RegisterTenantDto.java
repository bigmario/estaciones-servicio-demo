package com.estaciones.modules.admin.dto;

import com.estaciones.modules.admin.entity.Tenant;
import jakarta.annotation.Nullable;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegisterTenantDto extends Tenant {
    @NotNull
    @NotBlank
    String name;

    @Nullable
    String description;
}
