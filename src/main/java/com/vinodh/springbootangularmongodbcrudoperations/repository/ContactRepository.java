package com.vinodh.springbootangularmongodbcrudoperations.repository;

import com.vinodh.springbootangularmongodbcrudoperations.model.ContactInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<ContactInfo, String> {
 
}
