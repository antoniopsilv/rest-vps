package com.swagger.coderestapi.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.swagger.coderestapi.entity.Partner;

@Repository
public interface PartnerRepository extends MongoRepository<Partner, String> {

	Optional<Partner> findPartnerById(Integer id);
}