package com.webservices.company.repository;

import com.webservices.company.domain.Company;
import com.webservices.company.domain.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Component
public class EmployeeRepository implements IEmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Employee> rowMapper;

    public EmployeeRepository(JdbcTemplate jdbcTemplate, RowMapper<Employee> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public Employee add(Employee employeeToBeAdded, Long companyId) {
        // Preparing the insert statement
        PreparedStatementCreator preparedStatementCreator = (Connection connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.employees(\n" +
                    "\tname, salary, email_address, department, hiring_date, employment_type, company_id)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?, ?)", new String[]{"id"}); // first index is 1
            preparedStatement.setString(1, employeeToBeAdded.getName());
            preparedStatement.setDouble(2, employeeToBeAdded.getSalary());
            preparedStatement.setString(3, employeeToBeAdded.getEmailAddress());
            preparedStatement.setString(4, employeeToBeAdded.getDepartment());
            preparedStatement.setTimestamp(5, Timestamp.from(employeeToBeAdded.getHiringDate()));
            preparedStatement.setString(6, String.valueOf(employeeToBeAdded.getEmploymentType()));
            preparedStatement.setLong(7, companyId);
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        Number keyGenerated = generatedKeyHolder.getKey();
        Long key = keyGenerated.longValue();
        return get(key);

    }

    @Override
    public Employee get(Long employeeId) {
        Employee employee = jdbcTemplate.queryForObject("SELECT * FROM public.employees\n" +
                "Where id = " + employeeId, rowMapper);
        return employee;
    }

    @Override
    public Employee update(Long id, Employee employeeToUpdate) {
        // Preparing the update statement
        PreparedStatementCreator preparedStatementCreator = (Connection connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.employees\n" +
                    "\tSET name=?, salary=?, email_address=?, department=?, hiring_date=?, employment_type=?, company_id=?\n" +
                    "\tWHERE id = ? ");
            preparedStatement.setString(1, employeeToUpdate.getName());
            preparedStatement.setDouble(2, employeeToUpdate.getSalary());
            preparedStatement.setString(3, employeeToUpdate.getEmailAddress());
            preparedStatement.setString(4, employeeToUpdate.getDepartment());
            preparedStatement.setTimestamp(5, Timestamp.from(employeeToUpdate.getHiringDate()));
            preparedStatement.setString(6, String.valueOf(employeeToUpdate.getEmploymentType()));
            preparedStatement.setLong(7, employeeToUpdate.getCompanyId());
            preparedStatement.setLong(8, id);
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator);
        return get(id);


    }

    @Override
    public void delete(Long employeeId) {
        // Preparing the delete statement
        PreparedStatementCreator preparedStatementCreator = (Connection connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.employees\n" +
                    " WHERE id = ? ");
            preparedStatement.setLong(1, employeeId);
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator);

    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = jdbcTemplate.query("SELECT * FROM public.employees;", rowMapper);
       /* for (Employee employee : employees) {
            System.out.println(employee.toString());
        } */
        return employees;
    }
}
