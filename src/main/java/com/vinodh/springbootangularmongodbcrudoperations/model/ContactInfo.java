package com.vinodh.springbootangularmongodbcrudoperations.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "contacts")
public class ContactInfo {
    @Id
    String id;
    String name;
    String address;
    String city;
    String phone;
    String email;

}
