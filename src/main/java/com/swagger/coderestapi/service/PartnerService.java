package com.swagger.coderestapi.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.swagger.coderestapi.entity.Partner;
import com.swagger.coderestapi.exception.InsufficientCreditException;
import com.swagger.coderestapi.exception.PartnerNotFoundException;
import com.swagger.coderestapi.repository.PartnerRepository;

@Service
public class PartnerService {

	private final PartnerRepository partnerRepository;

	public PartnerService(PartnerRepository partnerRepository) {
		this.partnerRepository = partnerRepository;
	}

	public Partner findPartnerById(Integer id) {
	    return partnerRepository.findPartnerById(id)
	            .orElseThrow(() ->
	                    new PartnerNotFoundException(
	                            "Partner not found: " + id
	                    )
	            );
	}
	
	public void checkCredit(Integer partnerId, BigDecimal orderValue) {

		Partner partner = findPartnerById(partnerId);

		if (partner.getAvailableCredit().compareTo(orderValue) < 0) {
			//throw new RuntimeException("Insufficient credit");
		    throw new InsufficientCreditException("Insufficient credit");
		}
	}

	public Partner debitCredit(Integer partnerId, BigDecimal orderValue) {

		Partner partner = findPartnerById(partnerId);

		if (partner.getAvailableCredit().compareTo(orderValue) < 0) {
			throw new RuntimeException("Insufficient credit");
		}

		BigDecimal newAvailableCredit = partner.getAvailableCredit().subtract(orderValue);

		partner.setAvailableCredit(newAvailableCredit);

		return partnerRepository.save(partner);
	}
}
