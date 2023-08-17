package com.estaciones.demo.core.datasource;

import com.estaciones.demo.core.model.TenantAware;
import jakarta.persistence.PrePersist;

public class TenantListener {

  @PrePersist
  public void setTenant(final TenantAware entity) {
    final Integer tenantId = TenantContext.getTenantId();
    entity.setTenantId(tenantId);
  }

}
