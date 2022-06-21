package ecrow.backend.api.controllers;

import ecrow.backend.business.abstracts.StatusService;
import ecrow.backend.core.utilities.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/status")

public class StatusController {
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return Utils.getResponseEntity(statusService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        return Utils.getResponseEntity(statusService.getById(id));
    }

    @GetMapping("/getByName")
    public ResponseEntity<?> getByName(@RequestParam String name) {
        return Utils.getResponseEntity(statusService.getByName(name));
    }
}
