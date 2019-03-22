package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Cliente;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Cliente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

}
