package com.estaciones.demo.core.interceptor;

import com.estaciones.demo.core.Jwt.JwtAuthenticationFilter;
import com.estaciones.demo.core.Jwt.JwtService;
import com.estaciones.demo.core.datasource.TenantContext;
import com.estaciones.demo.core.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class TenantInterceptor implements HandlerInterceptor {

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;
  @Autowired
  private JwtService jwtService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = jwtAuthenticationFilter.getTokenRequestPublic(request);
    Integer tenantId = jwtService.getTenantIdFromToken(token);
    //System.out.println(tenatID);
    if (token.isEmpty()) {
      response.getWriter().write(new ErrorResponse("token invalid").toString());
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.setContentType("application/json");
      return false;
    }
    try {
      TenantContext.setTenantId(tenantId);
      return true;
    } catch (NumberFormatException e) {
      response.getWriter().write(new ErrorResponse("token value is incorrect").toString());
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.setContentType("application/json");
      return false;
    }

  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    TenantContext.clear();
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    //NOOP
  }
}
