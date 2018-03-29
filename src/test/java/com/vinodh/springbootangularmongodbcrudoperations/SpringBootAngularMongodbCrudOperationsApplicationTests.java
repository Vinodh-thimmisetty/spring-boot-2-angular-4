package com.vinodh.springbootangularmongodbcrudoperations;

import com.vinodh.springbootangularmongodbcrudoperations.model.Address;
import com.vinodh.springbootangularmongodbcrudoperations.model.ContactInfo;
import com.vinodh.springbootangularmongodbcrudoperations.repository.ContactRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

// @RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAngularMongodbCrudOperationsApplicationTests {

    @Test
    public void testAppConfig() {
        Assert.assertTrue(true);
    }

    /**
     * Load Sample Contact Data
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CommandLineRunner commandLineRunner(ContactRepository contactRepository, ApplicationContext applicationContext) {
        Assert.assertTrue(Stream.of(applicationContext.getBeanDefinitionNames())
                .anyMatch(eachBean -> eachBean.equalsIgnoreCase("contactRepository")));

        ContactInfo contactInfo = ContactInfo.builder()
                .id("test1234")
                .name("Vinodh Kumar")
                .email("vinodh5052@gmail.com")
                .city("Chennai")
                .address(Address.builder()
                        .city("Chennai")
                        .street("Perumbakkam")
                        .state("Tamilnadu")
                        .country("India")
                        .zipCode("600019")
                        .build())
                .phone("123456")
                .build();

        ContactInfo contactInfo1 = ContactInfo.builder()
                .id("test4567")
                .name("Vinodh Thimmisetty")
                .email("vinodh5@gmail.com")
                .city("Chennai1")
                .address(Address.builder()
                        .city("Chennai")
                        .street("Perumbakkam")
                        .state("Tamilnadu")
                        .country("India")
                        .zipCode("600019")
                        .build())
                .phone("123479")
                .build();


        ContactInfo contactInfo2 = ContactInfo.builder()
                .id("test890")
                .name("Thimmisetty")
                .email("vinodh@gmail.com")
                .city("Chennai2")
                .address(Address.builder()
                        .city("Chennai")
                        .street("Perumbakkam")
                        .state("Tamilnadu")
                        .country("India")
                        .zipCode("600019")
                        .build())
                .phone("123479")
                .build();

        return args -> {

            contactRepository.save(contactInfo);
            contactRepository.save(contactInfo1);
            contactRepository.save(contactInfo2);


        };

    }


}
