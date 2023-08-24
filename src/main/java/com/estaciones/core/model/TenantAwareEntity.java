package com.estaciones.core.model;

import com.estaciones.core.datasource.TenantListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(TenantListener.class)
public abstract class TenantAwareEntity implements TenantAware {

  @Column(name = "tenant_id", nullable = false)
  private Integer tenantId;

  public Integer getTenantId() {
    return tenantId;
  }

  public void setTenantId(Integer tenantId) {
    this.tenantId = tenantId;
  }
}
