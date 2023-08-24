package com.estaciones.core.datasource;

import com.estaciones.core.model.TenantAware;
import jakarta.persistence.PrePersist;

public class TenantListener {

  @PrePersist
  public void setTenant(final TenantAware entity) {
    final Integer tenantId = TenantContext.getTenantId();
    entity.setTenantId(tenantId);
  }

}
