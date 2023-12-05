package com.webservices.company.config;

import com.webservices.company.domain.Company;
import com.webservices.company.domain.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class ApplicationConfig {
    @Bean
    public RowMapper<Company> companyRowMapper() {
        return new BeanPropertyRowMapper<>(Company.class);
    }

    @Bean
    public RowMapper<Employee> employeeRowMapper() {
        return new BeanPropertyRowMapper<>(Employee.class);
    }


}
