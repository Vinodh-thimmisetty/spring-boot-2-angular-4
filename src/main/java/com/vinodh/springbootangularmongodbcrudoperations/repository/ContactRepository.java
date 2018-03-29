package com.vinodh.springbootangularmongodbcrudoperations.repository;

import com.vinodh.springbootangularmongodbcrudoperations.model.ContactInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * https://docs.spring.io/spring-data/mongodb/docs/2.0.0.RELEASE/reference/html/#mongo.aggregation.supported-aggregation-operations
 *
 * https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#repository-query-keywords
 *
 */
public interface ContactRepository extends MongoRepository<ContactInfo, String> {

    // No need to Mention @Query
    List<ContactInfo> findByEmail(String email);

    // ?0 ==> First Parameter
    @Query("{'address.zipCode' : ?0}")
    List<ContactInfo> findByZipCode(String zipCode);

    List<ContactInfo> findByTagsIn(List<String> tagName);
}
