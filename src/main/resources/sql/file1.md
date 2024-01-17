-- CREATE TABLE sql queries:

CREATE TABLE companies ( name character NOT NULL,
                         address character NOT NULL,
                         number_of_employees integer NOT NULL,
                         date_found timestamp without time zone,
                         type_of_business character NOT NULL,
                         id serial NOT NULL , 
                         PRIMARY KEY (id) );


CREATE TABLE employees ( id serial NOT NULL,
                         name character NOT NULL,
                         salary double precision NOT NULL,
                         email_address character NOT NULL,
                         department character NOT NULL,
                         hiring_date timestamp without time zone,
                         employment_type character NOT NULL,
                         company_id serial NOT NULL,
                         PRIMARY KEY (id),
                         FOREIGN KEY (company_id) REFERENCES companies(id) );