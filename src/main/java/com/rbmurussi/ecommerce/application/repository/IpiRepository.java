package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Ipi;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Ipi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IpiRepository extends MongoRepository<Ipi, String> {

}
