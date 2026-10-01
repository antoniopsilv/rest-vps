package com.swagger.coderestapi.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.swagger.coderestapi.entity.Order;
import com.swagger.coderestapi.entity.Partner;
import com.swagger.coderestapi.enums.StatusEnum;
import com.swagger.coderestapi.repository.PartnerRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class OrderIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private PartnerRepository partnerRepository;
    
    
    
    @BeforeEach
    void cleanTestData() {

        mongoTemplate.remove(
            Query.query(
                Criteria.where("id").in(1001, 5555, 6666, 8888, 7777)
            ),
            Order.class
        );

        partnerRepository.deleteAll();

        partnerRepository.save(
            new Partner(
                2001,
                BigDecimal.valueOf(1000.00)
            )
        );

        partnerRepository.save(
            new Partner(
                9999,
                BigDecimal.valueOf(1000.00)
            )
        );
    }

    @Test
    void getOrders() {

        ResponseEntity<Order[]> response = restTemplate.getForEntity(
                "/rest/consultapedidos",
                Order[].class
        );

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void createOrder() {

        Order order = new Order(
                1001,
                2001,
                new ArrayList<>(),
                BigDecimal.valueOf(150.00),
                StatusEnum.PENDING,
                LocalDate.now(),
                LocalDateTime.now()
        );

        ResponseEntity<Order> response = restTemplate.postForEntity(
                "/rest/cadastrapedidos",
                order,
                Order.class
        );

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Order createdOrder = response.getBody();

        assertNotNull(createdOrder);
        assertEquals(1001, createdOrder.getId());
        assertEquals(2001, createdOrder.getIdPartner());
        assertEquals(BigDecimal.valueOf(150.00), createdOrder.getTotalValue());
        assertEquals(StatusEnum.PENDING, createdOrder.getStatus());
    }

    @Test
    void updateOrder() {

        Order order = new Order(
                8888,
                9999,
                new ArrayList<>(),
                BigDecimal.valueOf(200.00),
                StatusEnum.APPROVED,
                LocalDate.now(),
                LocalDateTime.now()
        );

        ResponseEntity<Order> createResponse = restTemplate.postForEntity(
                "/rest/cadastrapedidos",
                order,
                Order.class
        );

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        assertNotNull(createResponse.getBody());
        assertEquals(8888, createResponse.getBody().getId());

        Order orderUpdated = new Order(
                8888,
                9999,
                new ArrayList<>(),
                BigDecimal.valueOf(500.00),
                StatusEnum.APPROVED,
                LocalDate.now(),
                LocalDateTime.now()
        );

        restTemplate.put(
                "/rest/atualizapedidos/8888",
                orderUpdated
        );

        ResponseEntity<String> response = restTemplate.getForEntity(
                "/rest/consultapedidos/8888",
                String.class
        );

        System.out.println("STATUS HTTP: " + response.getStatusCode());
        System.out.println("BODY: " + response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    void cancelOrder() {

        Order order = new Order(
                7777,
                2001,
                new ArrayList<>(),
                BigDecimal.valueOf(300.00),
                StatusEnum.APPROVED,
                LocalDate.now(),
                LocalDateTime.now()
        );

        ResponseEntity<Order> createResponse = restTemplate.postForEntity(
                "/rest/cadastrapedidos",
                order,
                Order.class
        );

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());

        ResponseEntity<Order> response = restTemplate.exchange(
                "/rest/cancelapedidos/7777",
                HttpMethod.DELETE,
                null,
                Order.class
        );

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Order cancelledOrder = response.getBody();

        assertNotNull(cancelledOrder);
        assertEquals(7777, cancelledOrder.getId());
        assertEquals(StatusEnum.CANCELLED, cancelledOrder.getStatus());
    }
    
    @Test
    void approveOrder() {

        Order order = new Order(
                5555,
                2001,
                new ArrayList<>(),
                BigDecimal.valueOf(300.00),
                StatusEnum.PENDING,
                LocalDate.now(),
                LocalDateTime.now()
        );

        ResponseEntity<Order> createResponse = restTemplate.postForEntity(
                "/rest/cadastrapedidos",
                order,
                Order.class
        );

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());

        ResponseEntity<Order> approveResponse = restTemplate.exchange(
                "/rest/aprovapedidos/5555",
                HttpMethod.PUT,
                null,
                Order.class
        );

        assertNotNull(approveResponse);
        assertEquals(HttpStatus.OK, approveResponse.getStatusCode());

        Order approvedOrder = approveResponse.getBody();

        assertNotNull(approvedOrder);
        assertEquals(5555, approvedOrder.getId());
        assertEquals(StatusEnum.APPROVED, approvedOrder.getStatus());

        Partner partner = partnerRepository
                .findPartnerById(2001)
                .orElseThrow();

        assertEquals(
                BigDecimal.valueOf(700.00),
                partner.getAvailableCredit()
        );
    }
    
    @Test
    void shouldNotCreateOrderWhenCreditIsInsufficient() {

        Order order = new Order(
                6666,
                2001,
                new ArrayList<>(),
                BigDecimal.valueOf(1500.00),
                StatusEnum.PENDING,
                LocalDate.now(),
                LocalDateTime.now()
        );

        ResponseEntity<Order> response = restTemplate.postForEntity(
                "/rest/cadastrapedidos",
                order,
                Order.class
        );

        assertNotNull(response);
      
        assertEquals(
        	    HttpStatus.UNPROCESSABLE_CONTENT,
        	    response.getStatusCode()
        	);
    }
}