package com.vinodh.springbootangularmongodbcrudoperations.controllers;

import com.vinodh.springbootangularmongodbcrudoperations.model.ContactInfo;
import com.vinodh.springbootangularmongodbcrudoperations.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
@Slf4j
public class ContactsController {

    private final ContactRepository contactRepository;

    // No need to mention @Autowired as Spring Boot internally performs Constructor Injection
    public ContactsController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<ContactInfo>> getAllContacts() {
        /*
        Iterable<ContactInfo> allContacts = contactRepository.findAll();
        List<ContactInfo> contactInfoList = StreamSupport.stream(allContacts.spliterator(), false)
                .collect(Collectors.toList());*/
        return ResponseEntity.ok(contactRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ContactInfo> getContactInfo(@PathVariable("id") String id) {
        Optional<ContactInfo> contactInfoResults = contactRepository.findById(id);
        // Add Additional JSON Response Params if Required
        // Return the Status as SUCCESS with Empty value
        return contactInfoResults.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new ContactInfo()));

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ContactInfo> updateContactInfo(@PathVariable("id") String id, @RequestBody ContactInfo contact) {
        log.info("Updating the Contact Info for :: {}", id);
        Optional<ContactInfo> contactInfoResults = contactRepository.findById(id);
        if (contactInfoResults.isPresent()) {
            contactRepository.save(contact);
            return ResponseEntity.ok(contactInfoResults.get());
        }
        return ResponseEntity.ok(new ContactInfo());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeContactInfo(@PathVariable("id") String id) {
        contactRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/newContact")
    public ResponseEntity<String> newContactInfo(@RequestBody ContactInfo contact) {
        log.info(">>>>>>>>>>>>>>> {}", contact.getEmail());
        contactRepository.save(contact);
        return ResponseEntity.ok("Saved !!");
    }


}
