package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.ItemTransactionService;
import ecrow.backend.core.utilities.Utils;
import ecrow.backend.entities.dtos.ItemTransactionAddDto;
import ecrow.backend.entities.dtos.ItemTransactionStatusUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/itemTransaction")
public class ItemTransactionController {
    private final ItemTransactionService itemTransactionService;

    @Autowired
    public ItemTransactionController(ItemTransactionService itemTransactionService) {
        this.itemTransactionService = itemTransactionService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return Utils.getResponseEntity(itemTransactionService.getAll());
    }

    @GetMapping("/getAllSortedByStatus")
    public ResponseEntity<?> getAllSortedByStatus() {
        return Utils.getResponseEntity(itemTransactionService.getAllSortedByStatus());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return Utils.getResponseEntity(itemTransactionService.getById(id));
    }

    @GetMapping("/getByFkEmployeeId")
    public ResponseEntity<?> getByFkEmployeeId(@RequestParam Integer employeeId) {
        return Utils.getResponseEntity(itemTransactionService.getByFkEmployeeId(employeeId));
    }

    @GetMapping("/getByFkBuyerId")
    public ResponseEntity<?> getByFkBuyerId(@RequestParam Integer buyerId) {
        return Utils.getResponseEntity(itemTransactionService.getByFkBuyerId(buyerId));
    }

    @GetMapping("/getByFkSellerId")
    public ResponseEntity<?> getByFkSellerId(@RequestParam Integer sellerId) {
        return Utils.getResponseEntity(itemTransactionService.getByFkSellerId(sellerId));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam Integer id) {
        return Utils.getResponseEntity(itemTransactionService.deleteById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ItemTransactionAddDto itemTransactionAddDto) {
        return Utils.getResponseEntity(itemTransactionService.add(itemTransactionAddDto));
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody ItemTransactionStatusUpdateDto itemTransactionStatusUpdateDto) {
        return Utils.getResponseEntity(itemTransactionService.updateStatus(itemTransactionStatusUpdateDto));
    }
}
