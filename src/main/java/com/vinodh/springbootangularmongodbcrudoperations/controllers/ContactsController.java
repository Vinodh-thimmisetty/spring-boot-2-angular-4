package com.vinodh.springbootangularmongodbcrudoperations.controllers;

import com.vinodh.springbootangularmongodbcrudoperations.model.ContactInfo;
import com.vinodh.springbootangularmongodbcrudoperations.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/contacts")
@Slf4j
public class ContactsController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/listAll")
    public ResponseEntity<List<ContactInfo>> getAllContacts() {
        Iterable<ContactInfo> allContacts = contactRepository.findAll();
        List<ContactInfo> contactInfoList = StreamSupport.stream(allContacts.spliterator(), false)
                .collect(Collectors.toList());
        return ResponseEntity.ok(contactInfoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactInfo> getContactInfo(@PathVariable("id") String id) {
        Optional<ContactInfo> contactInfo = contactRepository.findById(id);
        return ResponseEntity.ok(contactInfo.orElse(null));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ContactInfo> updateContactInfo(@PathVariable("id") String id, @RequestBody ContactInfo contact) {
        log.info("Updating the Contact Info for :: {}", id);
        Optional<ContactInfo> contactInfo = contactRepository.findById(id);
        if (contactInfo.isPresent()) contactRepository.save(contact);
        return ResponseEntity.ok(contactInfo.orElse(null));
    }
 
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeContactInfo(@PathVariable("id") String id) {
        contactRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/newContact")
    public ResponseEntity<String> newContactInfo(@RequestBody ContactInfo contact) {
        log.info(">>>>>>>>>>>>>>> {}",contact.getEmail());
        contactRepository.save(contact);
        return ResponseEntity.ok("Saved !!");
    }


}
