package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.ChatMessageService;
import ecrow.backend.core.utilities.Utils;
import ecrow.backend.entities.dtos.ChatMessageAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/chatMessage")
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    @Autowired
    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return Utils.getResponseEntity(chatMessageService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return Utils.getResponseEntity(chatMessageService.getById(id));
    }

    @GetMapping("/getByFkEmployeeId")
    public ResponseEntity<?> getByFkEmployeeId(@RequestParam Integer employeeId) {
        return Utils.getResponseEntity(chatMessageService.getByFkEmployeeId(employeeId));
    }

    @GetMapping("/getByFkCustomerId")
    public ResponseEntity<?> getByFkCustomerId(@RequestParam Integer customerId) {
        return Utils.getResponseEntity(chatMessageService.getByFkEmployeeId(customerId));
    }

    @GetMapping("/getByFkCustomerIdAndFkEmployeeId")
    public ResponseEntity<?> getByFkCustomerIdAndFkEmployeeId(@RequestParam Integer customerId, @RequestParam Integer employeeId) {
        return Utils.getResponseEntity(chatMessageService.getByFkCustomerIdAndFkEmployeeId(customerId,employeeId));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam Integer id) {
        return Utils.getResponseEntity(chatMessageService.deleteById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ChatMessageAddDto chatMessageAddDto) {
        return Utils.getResponseEntity(chatMessageService.add(chatMessageAddDto));
    }
}
