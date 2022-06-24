package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.RequirementService;
import ecrow.backend.core.utilities.Utils;
import ecrow.backend.entities.dtos.RequirementAddDto;
import ecrow.backend.entities.dtos.RequirementSatisfiedUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/requirement")
public class RequirementController {
    private final RequirementService requirementService;

    @Autowired
    public RequirementController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return Utils.getResponseEntity(requirementService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return Utils.getResponseEntity(requirementService.getById(id));
    }

    @GetMapping("/getByFkTransactionId")
    public ResponseEntity<?> getByFkTransactionId(@RequestParam Integer itemTransactionId) {
        return Utils.getResponseEntity(requirementService.getByFkTransactionId(itemTransactionId));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam Integer id) {
        return Utils.getResponseEntity(requirementService.deleteById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody RequirementAddDto requirementAddDto) {
        return Utils.getResponseEntity(requirementService.add(requirementAddDto));
    }

    @PatchMapping("/updateSatisfied")
    public ResponseEntity<?> updateSatisfied(@RequestBody RequirementSatisfiedUpdateDto requirementSatisfiedUpdateDto) {
        return Utils.getResponseEntity(requirementService.updateSatisfied(requirementSatisfiedUpdateDto));
    }

    @PatchMapping("/updateSatisfiedTrue")
    public ResponseEntity<?> updateSatisfiedTrue(@RequestParam Integer id) {
        return Utils.getResponseEntity(requirementService.updateSatisfiedTrue(id));
    }
}
