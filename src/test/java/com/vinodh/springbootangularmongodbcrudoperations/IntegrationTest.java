package com.vinodh.springbootangularmongodbcrudoperations;

        import com.vinodh.springbootangularmongodbcrudoperations.model.ContactInfo;
        import org.junit.Assert;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.web.client.TestRestTemplate;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.test.context.ActiveProfiles;
        import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
public class IntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;


    @Test
    public void testAction() {

        ResponseEntity<ContactInfo> contactInfoResponseEntity = testRestTemplate.getForEntity("/contacts/test1234", ContactInfo.class);
        Assert.assertEquals(HttpStatus.OK, contactInfoResponseEntity.getStatusCode());
        Assert.assertEquals("vinodh5052@gmail.com", contactInfoResponseEntity.getBody().getEmail());
    }

}
