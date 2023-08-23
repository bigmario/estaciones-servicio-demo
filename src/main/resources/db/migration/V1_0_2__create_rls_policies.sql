DROP policy if exists tenant_users_isolation_policy ON public.supply_order;

CREATE POLICY tenant_users_isolation_policy ON public.supply_order
	USING (tenant_id = current_setting('app.tenant_id', true)::integer);

ALTER TABLE public.supply_order ENABLE ROW LEVEL SECURITY;
