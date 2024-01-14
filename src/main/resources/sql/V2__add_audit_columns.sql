DO
$$
    BEGIN
		ALTER TABLE IF EXISTS public.companies ADD COLUMN added_by text;
		ALTER TABLE IF EXISTS public.companies ADD COLUMN modified_by text;
		ALTER TABLE IF EXISTS public.companies ADD COLUMN created_at timestamp without time zone;
		ALTER TABLE IF EXISTS public.companies ADD COLUMN modified_at timestamp without time zone;

		ALTER TABLE IF EXISTS public.employees ADD COLUMN added_by text;
        ALTER TABLE IF EXISTS public.employees ADD COLUMN modified_by text;
        ALTER TABLE IF EXISTS public.employees ADD COLUMN created_at timestamp without time zone;
        ALTER TABLE IF EXISTS public.employees ADD COLUMN modified_at timestamp without time zone;
    EXCEPTION
        WHEN undefined_column THEN RAISE NOTICE 'column another_table.not_exist does not exist';
    END;
$$;
