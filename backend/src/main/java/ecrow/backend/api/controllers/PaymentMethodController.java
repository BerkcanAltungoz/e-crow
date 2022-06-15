package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.PaymentMethodService;
import ecrow.backend.core.utilities.Utils;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.entities.concretes.PaymentMethod;
import ecrow.backend.entities.dtos.PaymentMethodAddDto;
import ecrow.backend.entities.dtos.PaymentMethodUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/paymentMethod")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return Utils.getResponseEntity(paymentMethodService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return Utils.getResponseEntity(paymentMethodService.getById(id));
    }

    @GetMapping("/getByFkCustomerId")
    public ResponseEntity<?> getByFkCustomerId(@RequestParam Integer customerId) {
        return Utils.getResponseEntity(paymentMethodService.getByFkCustomerId(customerId));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam Integer id) {
        return Utils.getResponseEntity(paymentMethodService.deleteById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody PaymentMethodAddDto paymentMethodAddDto) {
        return Utils.getResponseEntity(paymentMethodService.add(paymentMethodAddDto));
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody PaymentMethodUpdateDto paymentMethodUpdateDto) {
        return Utils.getResponseEntity(paymentMethodService.update(paymentMethodUpdateDto));
    }
}
