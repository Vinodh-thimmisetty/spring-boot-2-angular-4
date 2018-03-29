package com.vinodh.springbootangularmongodbcrudoperations.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "contacts")
public class ContactInfo {
    @Id
    String id;
    @Indexed
    String name;
    Address address;
    String city;
    String phone;
    String email;
    List<String> tags;

}
