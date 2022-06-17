package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.TransactionService;
import ecrow.backend.core.utilities.Utils;
import ecrow.backend.core.utilities.results.*;
import ecrow.backend.entities.concretes.Transaction;
import ecrow.backend.entities.dtos.TransactionAddDto;
import ecrow.backend.entities.dtos.TransactionStatusUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return Utils.getResponseEntity(transactionService.getAll());
    }

    @GetMapping("/getAllSortedByStatus")
    public ResponseEntity<?> getAllSortedByStatus() {
        return Utils.getResponseEntity(transactionService.getAllSortedByStatus());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return Utils.getResponseEntity(transactionService.getById(id));
    }

    @GetMapping("/getByFkEmployeeId")
    public ResponseEntity<?> getByFkEmployeeId(@RequestParam Integer employeeId) {
        return Utils.getResponseEntity(transactionService.getByFkEmployeeId(employeeId));
    }

    @GetMapping("/getByFkBuyerId")
    public ResponseEntity<?> getByFkBuyerId(@RequestParam Integer buyerId) {
        return Utils.getResponseEntity(transactionService.getByFkBuyerId(buyerId));
    }

    @GetMapping("/getByFkSellerId")
    public ResponseEntity<?> getByFkSellerId(@RequestParam Integer sellerId) {
        return Utils.getResponseEntity(transactionService.getByFkSellerId(sellerId));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam Integer id) {
        return Utils.getResponseEntity(transactionService.deleteById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody TransactionAddDto transactionAddDto) {
        return Utils.getResponseEntity(transactionService.add(transactionAddDto));
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody TransactionStatusUpdateDto transactionStatusUpdateDto) {
        return Utils.getResponseEntity(transactionService.updateStatus(transactionStatusUpdateDto));
    }
}
