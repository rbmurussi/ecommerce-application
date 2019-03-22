package com.rbmurussi.ecommerce.application.repository;

import com.rbmurussi.ecommerce.application.domain.Icms;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Icms entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IcmsRepository extends MongoRepository<Icms, String> {

}
