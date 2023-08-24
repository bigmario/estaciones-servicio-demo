package com.estaciones.core.datasource;

import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.web.context.request.RequestContextHolder;

public class DynamicDataSourceRouter extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // Obtén el RequestContextHolder para acceder a la solicitud actual
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        // Obtén el URI de la solicitud
        String uri = request.getRequestURI();

        // Si el URI comienza con "/admin/", usar el DataSource para administradores
        if (uri.startsWith("/admin/")) {
            return "tenantAdminDatasource";
        }

        // Para otros casos, usar el DataSource predeterminado
        return "applicationDataSource";
    }
}
