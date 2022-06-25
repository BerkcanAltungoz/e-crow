package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.CustomerService;
import ecrow.backend.core.utilities.Utils;
import ecrow.backend.entities.dtos.CustomerAddDto;
import ecrow.backend.entities.dtos.CustomerDepositBalanceDto;
import ecrow.backend.entities.dtos.CustomerBaseUpdateDto;
import ecrow.backend.entities.dtos.SignInDto;
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

    @PostMapping("/signIn")
    public ResponseEntity<?> getByEmailAndPassword(@RequestBody SignInDto signInDto) {
        return Utils.getResponseEntity(customerService.signIn(signInDto));
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

    @PatchMapping("/depositBalance")
    public ResponseEntity<?> depositBalance(@RequestBody CustomerDepositBalanceDto customerDepositBalanceDto) {
        return Utils.getResponseEntity(customerService.depositBalance(customerDepositBalanceDto));
    }
}
