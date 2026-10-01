package com.swagger.coderestapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.swagger.coderestapi.entity.Order;
import com.swagger.coderestapi.enums.StatusEnum;
import com.swagger.coderestapi.exception.OrderNotFoundException;
import com.swagger.coderestapi.repository.RestApiRepository;

@Service
public class RestApiService {
	
    private final RestApiRepository restApiRepository;
    private final PartnerService partnerService;
    private final NotificationService notificationService;
    
    public RestApiService(
            RestApiRepository restApiRepository,
            PartnerService partnerService,
            NotificationService notificationService) {

        this.restApiRepository = restApiRepository;
        this.partnerService = partnerService;
        this.notificationService = notificationService;
    }
	
	
	public List<Order> getOrders() {
		return restApiRepository.findAll();
	}

    public Order createOrder(Order order) {

        partnerService.checkCredit(
                order.getIdPartner(),
                order.getTotalValue()
        );

        order.setStatus(StatusEnum.PENDING);
        
        return restApiRepository.save(order);
    }


	public Order updateOrder(Integer id, Order order) {

		Order existingOrder = restApiRepository.findOrderById(id).orElseThrow(() -> new RuntimeException("Order not found"));
	
	    existingOrder.setIdPartner(order.getIdPartner());
	    existingOrder.setItemList(order.getItemList());
	    existingOrder.setTotalValue(order.getTotalValue());
	    existingOrder.setStatus(order.getStatus());
	    existingOrder.setLastUpdateDate(LocalDateTime.now());

		return restApiRepository.save(existingOrder);
	}

	public Order cancelOrder(Integer id) {

		Order existingOrder = restApiRepository.findOrderById(id)
				//.orElseThrow(() -> new RuntimeException("Order not found"));
				.orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));
				existingOrder.setStatus(StatusEnum.CANCELLED);
				
				notificationService.notifyStatusChange(existingOrder);
		
		return restApiRepository.save(existingOrder);
	}

	public Order findOrderById(Integer id) {

		return restApiRepository.findOrderById(id)
				// .orElseThrow(() -> new RuntimeException("Order not found"));
				.orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));
	}

	public Order approveOrder(Integer id) {

		Order order = restApiRepository.findOrderById(id)
				// .orElseThrow(() -> new RuntimeException("Order not found"));
				.orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));

		if (order.getStatus() != StatusEnum.PENDING) {
			throw new RuntimeException("Order is not pending");
		}

		partnerService.debitCredit(order.getIdPartner(), order.getTotalValue());

		order.setStatus(StatusEnum.APPROVED);
		order.setLastUpdateDate(LocalDateTime.now());
		
		notificationService.notifyStatusChange(order);

		return restApiRepository.save(order);
	}
}
