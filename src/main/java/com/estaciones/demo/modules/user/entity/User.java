package com.estaciones.demo.modules.user.entity;

import com.estaciones.demo.core.model.TenantAwareEntity;
import com.estaciones.demo.modules.admin.entity.Tenant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends TenantAwareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name = "tenant_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private Tenant tenant;

    // Getters y setters
}

