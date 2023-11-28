-- CREATE TABLE sql queries:

CREATE TABLE companies ( name character,
                         address character,
                         number_of_employees integer,
                         dateFound timestamp without time zone,
                         type_of_business character,
                         id bigint);


CREATE TABLE employees ( id bigint,
                         name character,
                         salary double precision,
                         email_address character,
                         department character,
                         hiring_date timestamp without time zone,
                         employment_type character,
                         company_id bigint );