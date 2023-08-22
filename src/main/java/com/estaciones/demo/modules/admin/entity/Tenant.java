package com.estaciones.demo.modules.admin.entity;

import com.estaciones.demo.core.audit.AuditableEntity;
import com.estaciones.demo.modules.user.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tenants")
@Getter
@Setter
@NoArgsConstructor
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String description;

    @OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<User> users;

    // Getters y setters
}
