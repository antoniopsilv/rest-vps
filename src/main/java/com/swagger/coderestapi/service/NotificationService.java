package com.swagger.coderestapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.swagger.coderestapi.entity.Order;

@Service
public class NotificationService {

    private static final Logger logger =
            LoggerFactory.getLogger(NotificationService.class);

    public void notifyStatusChange(Order order) {

        logger.info(
            "MESSAGE BROKER - Order {} changed status to {}",
            order.getId(),
            order.getStatus()
        );
    }
}