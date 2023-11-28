--  INSERT, SELECT, UPDATE, DELETE sql queries :

INSERT INTO employees(
	         id, name, salary, email_address, department, hiring_date, employment_type, company_id)
	VALUES ('3','Peter', '3500', 'Peter@gmail.com', 'HR','2018-09-01 08:30:55' ,'FULL_TIME', '2');



SELECT name, salary, employment_type, company_id FROM employees
	WHERE company_id = '1';

UPDATE companies
	SET  number_of_employees= '50'
	WHERE id = '1';


DELETE FROM employees
	WHERE salary ='2000';