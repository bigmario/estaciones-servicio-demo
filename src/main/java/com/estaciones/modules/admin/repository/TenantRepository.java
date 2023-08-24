package com.estaciones.modules.admin.repository;

import com.estaciones.modules.admin.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {
    // Métodos personalizados si es necesario

    @Query(value = "SELECT DISTINCT status FROM supply_order inner join tenants on tenants.id = supply_order.tenant_id", nativeQuery = true)
    List<String>getAllTenantOrders();
}

