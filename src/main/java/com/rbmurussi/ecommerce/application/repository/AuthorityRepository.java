package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Authority;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Authority entity.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {
}
