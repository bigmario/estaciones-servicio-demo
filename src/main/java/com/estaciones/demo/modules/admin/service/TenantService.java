package com.estaciones.demo.modules.admin.service;

import com.estaciones.demo.modules.admin.entity.Tenant;
import com.estaciones.demo.modules.admin.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class TenantService {
    @Autowired
    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Optional<Tenant> getTenantById(Integer id) {
        return tenantRepository.findById(id);
    }

    public Tenant createTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    public Tenant updateTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    public void deleteTenant(Integer id) {
        tenantRepository.deleteById(id);
    }

    public List<String> getAllTenantOrders() {
        List<String> orders = tenantRepository.getAllTenantOrders();
        System.out.println(orders);
        return tenantRepository.getAllTenantOrders();
    }
}

