package com.estaciones.demo.modules.admin.repository;

import com.estaciones.demo.modules.admin.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    // Métodos personalizados si es necesario
}

