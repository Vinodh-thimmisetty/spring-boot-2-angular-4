package com.vinodh.springbootangularmongodbcrudoperations.repository;


import com.vinodh.springbootangularmongodbcrudoperations.model.Address;
import com.vinodh.springbootangularmongodbcrudoperations.model.ContactInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


/**
 *
 * testCompile('de.flapdoodle.embed:de.flapdoodle.embed.mongo') is mandatory for Embed Mongo DB
 *
 */
@RunWith(SpringRunner.class)
@DataMongoTest
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class ContactRepoTestMongo {

    @Autowired
    ContactRepository contactRepository;

    @Before
    public void setup() {
        contactRepository.deleteAll();
    }


    @After
    public void cleanup() {
        contactRepository.deleteAll();
    }

    @Test
    public void getContactInfo() {
        Assert.assertFalse(contactRepository.findById("abcd1234").isPresent());

        ContactInfo contactInfo = ContactInfo.builder()
                .id("abcd1234")
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
                .tags(Arrays.asList("shols", "Thimmisetty"))
                .build();

        contactRepository.save(contactInfo);

        Assert.assertTrue(contactRepository.findById("abcd1234").isPresent());

        List<ContactInfo> contactInfoList = contactRepository.findByEmail("vinodh5052@gmail.com");
        Assert.assertNotNull(contactInfoList);
        Assert.assertEquals("abcd1234", contactInfoList.get(0).getId());

        // Add some more test Data
        ContactInfo contactInfo2 = ContactInfo.builder()
                .id("yoyo1234")
                .name("Vinodh Thimmi")
                .email("vinodh@gmail.com")
                .city("Chennai")
                .address(Address.builder()
                        .city("Chennai")
                        .street("Shols")
                        .state("Tamilnadu")
                        .country("India")
                        .zipCode("600019")
                        .build())
                .phone("1234561111")
                .tags(Arrays.asList("vinodh", "chennai", "india"))
                .build();

        contactRepository.save(contactInfo2);

        ContactInfo contactInfo3 = ContactInfo.builder()
                .id("qwerty1234")
                .name("Thimmisetty Vinodh Kumar")
                .email("vinodh123@gmail.com")
                .city("Chennai")
                .address(Address.builder()
                        .city("Kadapa")
                        .street("Thimmisetty Palli")
                        .state("Andhra Pradesh")
                        .country("India")
                        .zipCode("516101")
                        .build())
                .phone("7894561230")
                .tags(Arrays.asList("kadapa", "andhra", "vinodh", "india"))
                .build();

        contactRepository.save(contactInfo3);

        contactInfoList = contactRepository.findByZipCode("600019");
        Assert.assertNotNull(contactInfoList);
        Assert.assertEquals(2, contactInfoList.size());
        Assert.assertEquals("abcd1234", contactInfoList.get(0).getId());


        contactInfoList = contactRepository.findByTagsIn(Arrays.asList("india", "vinodh"));
        Assert.assertNotNull(contactInfoList);
        Assert.assertEquals(2, contactInfoList.size());


    }

}
