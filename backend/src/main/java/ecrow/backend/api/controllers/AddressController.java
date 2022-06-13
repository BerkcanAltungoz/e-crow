package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.AddressService;
import ecrow.backend.core.utilities.Utils;
import ecrow.backend.entities.dtos.AddressDto;
import ecrow.backend.entities.dtos.AddressUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return Utils.getResponseEntity(addressService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return Utils.getResponseEntity(addressService.getById(id));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam Integer id) {
        return Utils.getResponseEntity(addressService.deleteById(id));
    }
    @GetMapping("/getByFkCustomerId")
    public ResponseEntity<?> getByFkCustomerId(@RequestParam Integer customerId) {
        return Utils.getResponseEntity(addressService.getByFkCustomerId(customerId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AddressDto addressDto) {
       return Utils.getResponseEntity(addressService.add(addressDto));
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody AddressUpdateDto addressUpdateDto) {
        return Utils.getResponseEntity(addressService.update(addressUpdateDto));
    }
}
