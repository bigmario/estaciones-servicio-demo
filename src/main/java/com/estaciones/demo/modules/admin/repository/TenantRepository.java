package com.estaciones.demo.modules.admin.repository;

import com.estaciones.demo.modules.admin.entity.Tenant;
import com.estaciones.demo.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {}

