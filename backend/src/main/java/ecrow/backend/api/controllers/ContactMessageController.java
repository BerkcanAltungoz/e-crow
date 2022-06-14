package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.ContactMessageService;
import ecrow.backend.core.utilities.Utils;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.entities.concretes.ContactMessage;
import ecrow.backend.entities.dtos.ContactMessageAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/contactMessage")
public class ContactMessageController {
    private final ContactMessageService contactMessageService;

    @Autowired
    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return Utils.getResponseEntity(contactMessageService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return Utils.getResponseEntity(contactMessageService.getById(id));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam Integer id) {
        return Utils.getResponseEntity(contactMessageService.deleteById(id));
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        return Utils.getResponseEntity(contactMessageService.deleteAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ContactMessageAddDto contactMessageAddDto) {
        return Utils.getResponseEntity(contactMessageService.add(contactMessageAddDto));
    }
}
