package com.swagger.coderestapi.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.swagger.coderestapi.entity.Order;


@Repository
public interface RestApiRepository extends MongoRepository<Order, String> {

} 