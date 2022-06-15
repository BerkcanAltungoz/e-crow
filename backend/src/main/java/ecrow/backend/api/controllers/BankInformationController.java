package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.BankInformationService;
import ecrow.backend.core.utilities.Utils;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.entities.concretes.BankInformation;
import ecrow.backend.entities.dtos.BankInformationAddDto;
import ecrow.backend.entities.dtos.BankInformationUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/bankInformation")
public class BankInformationController {
    private BankInformationService bankInformationService;

    @Autowired
    public BankInformationController(BankInformationService bankInformationService) {
        this.bankInformationService = bankInformationService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return Utils.getResponseEntity(bankInformationService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return Utils.getResponseEntity(bankInformationService.getById(id));
    }

    @GetMapping("/getByFkUserId")
    public ResponseEntity<?> getByFkUserId(@RequestParam Integer userId) {
        return Utils.getResponseEntity(bankInformationService.getByFkUserId(userId));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam Integer id) {
        return Utils.getResponseEntity(bankInformationService.deleteById(id));
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody BankInformationAddDto bankInformationAddDto) {
        return Utils.getResponseEntity(bankInformationService.add(bankInformationAddDto));
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody BankInformationUpdateDto bankInformationUpdateDto) {
        return Utils.getResponseEntity(bankInformationService.update(bankInformationUpdateDto));
    }
}
