package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.CustomerService;
import ecrow.backend.core.utilities.Utils;
import ecrow.backend.entities.dtos.CustomerAddDto;
import ecrow.backend.entities.dtos.CustomerBalanceUpdateDto;
import ecrow.backend.entities.dtos.CustomerBaseUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return Utils.getResponseEntity(customerService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return Utils.getResponseEntity(customerService.getById(id));
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<?> getByEmail(@RequestParam String email) {
        return Utils.getResponseEntity(customerService.getByEmail(email));
    }

    @GetMapping("/getByPhoneNumber")
    public ResponseEntity<?> getByPhoneNumber(@RequestParam String phoneNumber) {
        return Utils.getResponseEntity(customerService.getByPhoneNumber(phoneNumber));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam Integer id) {
        return Utils.getResponseEntity(customerService.deleteById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CustomerAddDto customerAddDto) {
        return Utils.getResponseEntity(customerService.add(customerAddDto));
    }

    @PatchMapping("/updateBase")
    public ResponseEntity<?> updateBase(@RequestBody CustomerBaseUpdateDto customerBaseUpdateDto) {
        return Utils.getResponseEntity(customerService.updateBase(customerBaseUpdateDto));
    }

    @PatchMapping("/updateBalance")
    public ResponseEntity<?> updateBalance(@RequestBody CustomerBalanceUpdateDto customerBalanceUpdateDto) {
        return Utils.getResponseEntity(customerService.updateBalance(customerBalanceUpdateDto));
    }
}
