package com.webservices.company.repository;

import com.webservices.company.domain.Company;
import com.webservices.company.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Component
public class CompanyRepository implements ICompanyRepository {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Company> rowMapper;

    public CompanyRepository(JdbcTemplate jdbcTemplate, RowMapper<Company> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public Company add(Company companyToBeAdded) {
        PreparedStatementCreator preparedStatementCreator = (Connection connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.companies(\n" +
                    "\tname, address, number_of_employees, \"dateFound\", type_of_business)\n" +
                    "\tVALUES (? ,?, ?, ?, ?)", new String[]{"id"});
            preparedStatement.setString(1, companyToBeAdded.getName());
            preparedStatement.setString(2, companyToBeAdded.getAddress());
            preparedStatement.setInt(3, companyToBeAdded.getNumberOfEmployees());
            preparedStatement.setTimestamp(4, Timestamp.from(companyToBeAdded.getDateFound()));
            preparedStatement.setString(5, companyToBeAdded.getTypeOfBusiness());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        // Actual insert happens when calling update method
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        Number keyGenerated = generatedKeyHolder.getKey();
        Long key = keyGenerated.longValue();
        return get(key);
    }

    @Override
    public Company get(Long id) {
        Company company = null;
        try {
            company = jdbcTemplate.queryForObject("select * from companies where id = ?", rowMapper,id);
        } catch (EmptyResultDataAccessException e){
           return null;
        }
        return company;
    }

    @Override
    public Company update(Long id, Company updatedCompany) {
        PreparedStatementCreator preparedStatementCreator = (Connection connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.companies\n" +
                    "\tSET name=?, address=?, number_of_employees=?, \"dateFound\"=?, type_of_business=?\n" +
                    "\tWHERE id = ?");
            preparedStatement.setString(1, updatedCompany.getName());
            preparedStatement.setString(2, updatedCompany.getAddress());
            preparedStatement.setInt(3, updatedCompany.getNumberOfEmployees());
            preparedStatement.setTimestamp(4, updatedCompany.getDateFound() != null ? Timestamp.from(updatedCompany.getDateFound()) : null);
            preparedStatement.setString(5, updatedCompany.getTypeOfBusiness());
            preparedStatement.setLong(6, id);
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator);
        return get(id);
    }

    @Override
    public void delete(Long id) {
        PreparedStatementCreator preparedStatementCreator = (Connection connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.companies\n" +
                    " WHERE id = ? ");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator);
    }

    @Override
    public List<Company> getAll() {
        List<Company> companies = jdbcTemplate.query("SELECT * FROM public.companies;", rowMapper);
        return companies;
    }

}
