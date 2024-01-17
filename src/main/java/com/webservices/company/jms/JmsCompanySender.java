package com.webservices.company.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webservices.company.domain.Company;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsCompanySender {
    private static final String COMPANY_ADDED_NOTIFICATION = "company-added-notification";
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public JmsCompanySender(JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendCompanyAddedMessage(Company addedCompany) {
        try {
            jmsTemplate.convertAndSend(COMPANY_ADDED_NOTIFICATION, objectMapper.writeValueAsString(addedCompany));
        } catch (JsonProcessingException e) {
            System.out.println("Something went wrong!");

        }

    }
}
