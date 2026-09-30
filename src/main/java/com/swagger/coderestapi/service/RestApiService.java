package com.swagger.coderestapi.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.swagger.coderestapi.entity.Order;
import com.swagger.coderestapi.repository.RestApiRepository;


@Service
public class RestApiService {

	
    private final RestApiRepository restApiRepository;

    public RestApiService(RestApiRepository restApiRepository) {
        this.restApiRepository = restApiRepository;
    }
    

//    public Order retrieveOrder(Integer id) {
//
//        return restApiRepository.findById(id)
//            .orElseThrow(() -> new OrderNotFoundException(id));
//    }
	
	public List<Order> getOrders() {
		return restApiRepository.findAll();
	}


	public Order createOrder(Order order) {
		return restApiRepository.save(order);
	} 

}
