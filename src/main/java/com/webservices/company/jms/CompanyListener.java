package com.webservices.company.jms;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webservices.company.domain.Employee;
import com.webservices.company.exceptions.ResourceNotFoundException;
import com.webservices.company.service.ICompanyService;
import com.webservices.company.service.IEmployeeService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.activemq.artemis.api.core.JsonUtil;
import org.apache.activemq.artemis.json.JsonArray;
import org.apache.activemq.artemis.json.JsonObject;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class CompanyListener {
    JmsEmployeesIds companiesPayload;
    private final ObjectMapper objectMapper;
    private final IEmployeeService iEmployeeService;

    public CompanyListener(ObjectMapper objectMapper, IEmployeeService iEmployeeService) {
        this.objectMapper = objectMapper;
        this.iEmployeeService = iEmployeeService;
    }

    @JmsListener(destination = "companies")
    public void ListenCompanies(Message<String> message) {
        String payLoad = message.getPayload();
        System.out.println("Message from Jms: " + payLoad);

        try {
            companiesPayload = objectMapper.readValue(payLoad, JmsEmployeesIds.class);
        } catch (JsonProcessingException e) {
            System.out.println("Message is not JSON. Details:" + e.getMessage());
        }
        List<Integer> idsList = companiesPayload.getListOfIds();

        for (int index = 0; index < idsList.size(); index++) {
            try {
                Employee employeeToDelete = iEmployeeService.get(Long.valueOf(idsList.get(index)));
                iEmployeeService.delete(Long.valueOf(employeeToDelete.getId()));
                System.out.println("employee with id: " + employeeToDelete.getId() + "is deleted.");
            } catch (ResourceNotFoundException e) {
                System.out.println("ResourceIsNotFoundException : " + e.getMessage());
            }
        }

    }
}
