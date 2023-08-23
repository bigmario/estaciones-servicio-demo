
INSERT INTO public.tenants (name) VALUES ('tenant_1') ON CONFLICT DO NOTHING;
INSERT INTO public.tenants (name) VALUES ('tenant_2')  ON CONFLICT DO NOTHING;

------------------------------------------------------------------------------------------
INSERT INTO public.users (tenant_id, username, password, email, name, role)
VALUES
((SELECT id FROM public.tenants WHERE name = 'tenant_1'), 'mario', 'password', 'tenant_1+user1@gmail.com', 'Tenant 1 User 1', 'USER')
ON CONFLICT DO NOTHING;

INSERT INTO public.users (tenant_id, username, password, email, name, role)
VALUES
((SELECT id FROM public.tenants WHERE name = 'tenant_1'), 'andres', 'password', 'tenant_1+user2@gmail.com', 'Tenant 1 User 2', 'USER')
ON CONFLICT DO NOTHING;

INSERT INTO public.users (tenant_id, username, password, email, name, role)
VALUES
((SELECT id FROM public.tenants WHERE name = 'tenant_2'), 'angelica', 'password', 'tenant_2+user1@gmail.com', 'Tenant 2 User 1', 'USER')
ON CONFLICT DO NOTHING;

INSERT INTO public.users (tenant_id, username, password, email, name, role)
VALUES
((SELECT id FROM public.tenants WHERE name = 'tenant_2'), 'robe', 'password', 'tenant_2+user2@gmail.com', 'Tenant 2 User 2', 'USER')
ON CONFLICT DO NOTHING;

------------------------------------------------------------------------------------------
INSERT INTO public.supply_order (tenant_id, status)
  VALUES
((SELECT id FROM public.tenants WHERE name = 'tenant_1'), 'aprobada') ON CONFLICT DO NOTHING;

INSERT INTO public.supply_order (tenant_id, status)
  VALUES
((SELECT id FROM public.tenants WHERE name = 'tenant_2'), 'rechazada') ON CONFLICT DO NOTHING;

INSERT INTO public.supply_order (tenant_id, status)
  VALUES
((SELECT id FROM public.tenants WHERE name = 'tenant_1'), 'pendiente') ON CONFLICT DO NOTHING;

INSERT INTO public.supply_order (tenant_id, status)
  VALUES
((SELECT id FROM public.tenants WHERE name = 'tenant_2'), 'pendiente') ON CONFLICT DO NOTHING;


