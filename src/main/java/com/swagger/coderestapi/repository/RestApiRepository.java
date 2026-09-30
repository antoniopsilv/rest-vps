package com.swagger.coderestapi.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.swagger.coderestapi.entity.Order;


@Repository
public interface RestApiRepository extends MongoRepository<Order, String> {
	
	Optional<Order> findOrderById(Integer id);
} 