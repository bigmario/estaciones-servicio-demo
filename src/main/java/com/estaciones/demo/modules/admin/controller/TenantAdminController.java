package com.estaciones.demo.modules.admin.controller;

import com.estaciones.demo.modules.admin.dto.RegisterTenantDto;
import com.estaciones.demo.modules.admin.entity.Tenant;
import com.estaciones.demo.modules.admin.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/tenants")
public class TenantAdminController {
    private final TenantService tenantService;

    @Autowired
    public TenantAdminController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping("/{id}")
    public Optional<Tenant> getTenantById(@PathVariable Integer id) {
        return tenantService.getTenantById(id);
    }

    @PostMapping
    public Tenant createTenant(@RequestBody Tenant tenant) {
        return tenantService.createTenant(tenant);
    }

    @PutMapping("/{id}")
    public Tenant updateTenant(@PathVariable Integer id, @RequestBody Tenant tenant) {
        tenant.setId(id);
        return tenantService.updateTenant(tenant);
    }

    @DeleteMapping("/{id}")
    public void deleteTenant(@PathVariable Integer id) {
        tenantService.deleteTenant(id);
    }

    @GetMapping("/orders")
    public List<String> getAllTenantOrders() {
        return tenantService.getAllTenantOrders();
    }
}

