package com.vinodh.springbootangularmongodbcrudoperations.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
