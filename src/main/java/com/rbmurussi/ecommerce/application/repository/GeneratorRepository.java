package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Generator;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Generator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeneratorRepository extends MongoRepository<Generator, String> {

}
