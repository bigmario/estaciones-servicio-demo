package com.estaciones.demo.modules.admin.service;

import com.estaciones.demo.modules.admin.dto.RegisterTenantDto;
import com.estaciones.demo.modules.admin.entity.Tenant;
import com.estaciones.demo.modules.admin.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {
    @Autowired
    private TenantRepository tenantRepository;

    public List<Tenant> getAllTenants() {
        List<Tenant> tenantsWithUsers = tenantRepository.findAll();

        return tenantsWithUsers;
    }

    public Tenant registerTenant(Tenant newTenant){
        return tenantRepository.save(newTenant);
    }



    // Otros métodos de administración de tenants
}

