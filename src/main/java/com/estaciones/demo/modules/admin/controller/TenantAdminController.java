package com.estaciones.demo.modules.admin.controller;

import com.estaciones.demo.modules.admin.dto.RegisterTenantDto;
import com.estaciones.demo.modules.admin.entity.Tenant;
import com.estaciones.demo.modules.admin.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/tenants")
public class TenantAdminController {
    @Autowired
    private TenantService tenantService;

    @GetMapping()
    public ResponseEntity<List<Tenant>> getAllTenants() {
        return ResponseEntity.ok(tenantService.getAllTenants());
    }

    @PostMapping()
    public ResponseEntity<Tenant> registerTenant(@RequestBody Tenant newTenant){
        return ResponseEntity.ok(tenantService.registerTenant(newTenant));
    }

    // Otros métodos de administración de tenants
}

