package com.vinodh.springbootangularmongodbcrudoperations;


import com.vinodh.springbootangularmongodbcrudoperations.controllers.ContactsController;
import com.vinodh.springbootangularmongodbcrudoperations.model.ContactInfo;
import com.vinodh.springbootangularmongodbcrudoperations.repository.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactsController.class)
@ActiveProfiles(profiles = "test")
public class ContactControllerTest {


    @MockBean
    ContactRepository contactRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getContactInfo_test() throws Exception {
        when(contactRepository
                .findById("test123")).thenReturn(Optional
                .of(ContactInfo
                        .builder()
                        .id("test123")
                        .name("vinodh")
                        .build())
        );
        this.mockMvc
                .perform(get("/contacts/test123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("vinodh")));

        // 3 for saving, one for fetching
        verify(contactRepository, times(1)).findById(anyString());
        verify(contactRepository, times(3)).save(any(ContactInfo.class));
        verifyNoMoreInteractions(contactRepository);

    }

}
