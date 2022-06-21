package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.BaseUserService;
import ecrow.backend.core.utilities.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/baseUser")
public class BaseUserController {
    private final BaseUserService baseUserService;

    @Autowired
    public BaseUserController(BaseUserService baseUserService) {
        this.baseUserService = baseUserService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return Utils.getResponseEntity(baseUserService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return Utils.getResponseEntity(baseUserService.getById(id));
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<?> getByEmail(@RequestParam String email) {
        return Utils.getResponseEntity(baseUserService.getByEmail(email));
    }

    @GetMapping("/getByPhoneNumber")
    public ResponseEntity<?> getByPhoneNumber(@RequestParam String phoneNumber) {
        return Utils.getResponseEntity(baseUserService.getByPhoneNumber(phoneNumber));
    }
}
